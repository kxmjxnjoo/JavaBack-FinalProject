import React, { useEffect } from 'react'

const UploadStory = ({setIsSelectOpen}) => {
  useEffect(() => {
    setIsSelectOpen(false)
  }, [])

  return (
    <div>UploadStory</div>
  )
}

export default UploadStory