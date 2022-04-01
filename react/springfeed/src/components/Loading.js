import React from 'react'

import logo from '../images/logo.png'

const Loading = () => {
  return (
    <div className='container text-center'>

        <div className="h1">😪</div>
        <div className="h1 mt-3">페이지를 불러오고 있어요</div>

        <img src={logo} alt="SPRING FEED LOGO"  className='mt-5'/>
        <div className="h1 mt-5">Springfeed, 혁신적인 SNS</div>
    </div>
  )
}

export default Loading