import React, { useEffect, useState } from 'react'
import Loading from '../common/Loading';

const Story = ({storyUser}) => {
    const [jspElement, setJspElement] = useState(null);

    const createMarkup = (data) => {
        return {__html: data}
    }

    const [isLoading, setIsLoading] = useState(false)

    useEffect(() => {
        setIsLoading(true)
        fetch('/story?userid=' + storyUser)
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
            <Loading message='스토리를 가져오고 있어요'/>
            :
            <div dangerouslySetInnerHTML={{__html: jspElement}} className='mt-5 vh-100' style={{margin: ''}}/>
        }  
    </>
  )
}

export default Story