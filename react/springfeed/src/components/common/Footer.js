import React from 'react'
import {Link} from 'react-router-dom'

import {BsFillQuestionCircleFill as QnaIcon} from 'react-icons/bs'
import {MdOutlineSupportAgent as FaqIcon} from 'react-icons/md'

const Footer = () => {
  return (
    <footer className='container text-center mt-3 mb-3'>
        <div>
            @2022 SpringFeed Inc. All Rights Reserved
        </div>
        <div>
            Contact Us | E-mail: support@springfeed.com
        </div>
        <div>
            Developer | 김진주 | 박진겸 | 표세인 |
        </div>
        <div>
            <Link to='/faq' className='text-decoration-none text-dark'>
                <FaqIcon className='me-1'/>
                자주 묻는 질문
            </Link>
            <Link to='/qna' className='text-decoration-none text-dark'>
                <QnaIcon className='ms-3 me-1'/>
                직접 문의하기
            </Link>
        </div>
    </footer>
  )
}

export default Footer