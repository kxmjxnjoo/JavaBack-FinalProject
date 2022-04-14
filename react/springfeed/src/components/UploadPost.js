import React, { useEffect } from 'react'

const UploadPost = ({setIsSelectOpen}) => {
  useEffect(() => {
    setIsSelectOpen(false)
  }, [])

  return (
    <div>UploadPost</div>
  )
}

export default UploadPost