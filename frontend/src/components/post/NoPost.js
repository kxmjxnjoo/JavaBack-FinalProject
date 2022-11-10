import React from 'react'
import { Link } from 'react-router-dom'

const NoPost = () => {
    return (
        <div className='text-center mt-5'>
            <div className="h1 text-black">박진겸님, 팔로워 분들이 포스트가 없어요.</div>
            <Link to="/select" className='text-decoration-none'>
                <div className="btn btn-lg text-white mt-5" style={{ backgroundColor: 'var(--mainColor)' }}>지금 올리기 </div>
            </Link>
        </div>
    )
}

export default NoPost