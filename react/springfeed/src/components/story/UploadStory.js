import React, { useEffect, useState } from 'react'

const UploadStory = ({setIsSelectOpen}) => {
    const [jspElement, setJspElement] = useState(null);

    const createMarkup = (data) => {
        return {__html: data}
    }

    useEffect(() => {
      setIsSelectOpen(false)

      fetch('/story/add/form')
          .then((res) => {
              return res.text()
          })
          .then((html) => {
              setJspElement(html)
          })
    }, [])

  return (
    <div dangerouslySetInnerHTML={{__html: jspElement}} style={{marginTop: '70px', marginBottom: '30px'}}/>
  )
}

export default UploadStory