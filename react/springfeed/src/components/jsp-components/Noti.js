import React, { useEffect, useState } from 'react'

const Noti = ({setIsSelectOpen}) => {
    const [jspElement, setJspElement] = useState(null);

    const createMarkup = (data) => {
        return {__html: data}
    }

    useEffect(() => {
        setIsSelectOpen(false)
        
        fetch('/user/notification')
            .then((res) => {
                return res.text()
            })
            .then((html) => {
                setJspElement(html)
            })
    }, [])

  return (
    <div dangerouslySetInnerHTML={{__html: jspElement}}/>
  )
}

export default Noti