import React from 'react'

// Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'

// Components
import MessageNotSelected from '../components/Message/MessageNotSelected'

const Message = () => {
  return (
    <div className='container-fluid mt-5 vh-90 border'>
        <div className="row">
            <div className="col-4">
                USERLIST
            </div>
            
            <div className="col-8">
                <MessageNotSelected/>
            </div>
        </div>
    </div>
  )
}

export default Message