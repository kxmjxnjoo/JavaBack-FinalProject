import React, { useEffect, useState, useCallback } from 'react'
import toast from 'react-hot-toast';
import Loading from '../common/Loading';
import { useNavigate } from 'react-router-dom';

const Logout = () => {
    const [isLoading, setIsLoading] = useState(false)
    const navigate = useNavigate()

    const logout = () => {
        setIsLoading(true)
        fetch('/logout')
            .then((res) => {
                toast.success('로그아웃 했어요!')
            })
            .catch((err) => {
                toast.error('로그아웃 할 수 없어요')

            })
            .finally(() => {
                setIsLoading(false)
                navigate('/login')
            })
    }

    return (
        <div>
            {
                isLoading ? 
                    <Loading message='로그아웃 하고 있어요'/>
                :
                    <div className='text-center'>
                        <div className="h1">정말로 로그아웃 할까요?</div>
                        <div className="btn btn-danger mt-4" onClick={() => {
                            logout()
                        }}>로그아웃 하기</div>
                    </div>
            }
            

        </div>
    )
}

export default Logout