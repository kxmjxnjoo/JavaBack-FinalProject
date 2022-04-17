import React, { useEffect, useState } from 'react'
import $ from 'jquery'
import toast from 'react-hot-toast';

const Login = ({isLoggedIn}) => {
    const [jspElement, setJspElement] = useState(null);

    const createMarkup = (data) => {
        return {__html: data}
    }

    useEffect(() => {
        if(!isLoggedIn) {
            toast('안녕하세요! Spring Feed를 이용하기 위해 로그인 해 주세요', {
                icon: '👋'
            })
        }

        fetch('/login/form')
            .then((res) => {
                return res.text()
            })
            .then((html) => {
                setJspElement(html)
            })
    }, [])

  return (
    <div dangerouslySetInnerHTML={{__html: jspElement}} className='' style={{marginTop: '20px'}}/>
  )
}

export default Login