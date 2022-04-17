import React, { useEffect, useState } from 'react'

import Loading from '../common/Loading';

const Noti = ({setIsSelectOpen}) => {
    const [jspElement, setJspElement] = useState(null);

    const createMarkup = (data) => {
        return {__html: data}
    }

    const [isLoading, setIsLoading] = useState(false)

    useEffect(() => {
        setIsLoading(true)
        setIsSelectOpen(false)

        fetch('/user/notification')
            .then((res) => {
                return res.text()
            })
            .then((html) => {
                setJspElement(html)
            })
            .finally(() => {
                setIsLoading(false)
            })
    }, [])

  return (
    <>
        {
            isLoading ?
            <Loading message='알림을 불러오고 있어요'/>
            :
            <div dangerouslySetInnerHTML={{__html: jspElement}}/>
        }
    </>
  )
}

export default Noti