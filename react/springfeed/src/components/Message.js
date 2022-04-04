import React, { useEffect } from 'react'

// Components
import MessageNotSelected from './Message/MessageNotSelected'

const Message = ({ setPage }) => {
  useEffect(() => {
    setPage(1)
  })

  return (
    <div className='container-fluid mt-5 vh-90 border'>
      <div className="row">
        <div className="col-4">
          USERLIST
        </div>

        <div className="col-8">
          <MessageNotSelected />
        </div>
      </div>
    </div>
  )
}

export default Message