import React from 'react'
import {Link} from 'react-router-dom'

const Select = () => {
  return (
    <div className='justify-content-center'>
        <div className="h1 text-center mt-3 mb-3">
            무엇을 올리고 싶으세요?
        </div>

        <div className="row p-5">
            <div className="col-6">
                <Link to='/upload/post'>
                    <div className="btn w-100" style={{backgroundColor: 'var(--mainColor)', color: 'white'}}>
                        포스트 올리기
                    </div>
                </Link>
            </div>

            <div className="col-6">
                <Link to='/upload/story'>
                    <div className="btn w-100" style={{backgroundColor: 'var(--mainColor)', color: 'white'}}>
                        스토리 올리기
                    </div>
                </Link>
            </div>
        </div>

    </div>
  )
}

export default Select