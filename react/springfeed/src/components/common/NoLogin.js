import React from 'react'

const NoLogin = () => {
  return (
    <div className='mt-5 text-center'>
        <div className="h1">
            이 기능을 사용하려면 로그인 하셔야 해요.
        </div>

        <div className="btn btn-success btn-lg mt-5">
            로그인 하러 가기
        </div>
    </div>
  )
}

export default NoLogin