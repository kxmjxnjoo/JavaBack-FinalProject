import React, { useEffect, useState } from 'react'
import Loading from '../common/Loading';

const Story = ({storyNum}) => {
    const [jspElement, setJspElement] = useState(null);

    const createMarkup = (data) => {
        return {__html: data}
    }

    const [isLoading, setIsLoading] = useState(false)

    useEffect(() => {
        setIsLoading(true)
        fetch('/story?story_num=' + storyNum)
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
            <div dangerouslySetInnerHTML={{__html: jspElement}} className='mt-5' style={{margin: '500px'}}/>
        }  
    </>
  )
}

export default Story