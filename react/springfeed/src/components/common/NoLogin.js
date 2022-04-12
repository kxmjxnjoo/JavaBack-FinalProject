import React, {useState} from 'react'
import { Modal } from 'react-bootstrap'

import Login from '../jsp-components/Login'

const NoLogin = () => {
  const [isLoginShowing, setIsLoginShowing] = useState(false)
  
  const handleHide = () => {
    setIsLoginShowing(false)
  }

  return (
    <div className='mt-5 text-center'>
        <div className="h1">
            이 기능을 사용하려면 로그인 하셔야 해요.
        </div>

        <div className="btn btn-success btn-lg mt-5" onClick={() => {
          setIsLoginShowing(true)
        }}>
            로그인 하러 가기
        </div>

        <Modal show={isLoginShowing}
          onHide={handleHide}
        >
          <Login/>
        </Modal>
    </div>
  )
}

export default NoLogin