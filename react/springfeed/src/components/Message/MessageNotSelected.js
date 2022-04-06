import React from 'react'

// Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'

// Icons
import { BsChatDotsFill as ChatIcon } from 'react-icons/bs'

const MessageNotSelected = ({ setIsShowingFollowingList }) => {
  const chatIconStyle = {
    fontSize: '100px',
    color: "var(--mainColor)"
  }

  return (
    <div className='container text-center mt-5 mb-5'>

      <ChatIcon style={chatIconStyle} />
      <div className="h1 mt-5 mb-5">팔로우한 친구들에게 메세지를 보내 보세요!</div>
      <div className="btn text-white btn-lg" style={{ backgroundColor: "var(--mainColor)" }}
        onClick={() => {
          setIsShowingFollowingList(true)
        }}

      >메세지 보내기</div>

    </div>
  )
}

export default MessageNotSelected