import React from 'react'

import { BiErrorCircle as ErrorIcon } from 'react-icons/bi'

const NoSearchResult = () => {
    return (
        <div className='text-center mt-5'>
            <div className="row">
                <ErrorIcon className='text-danger h1' />
            </div>
            <div className="row">
                <div className="h5">
                    검색 결과가 없어요
                </div>
            </div>
        </div>
    )
}

export default NoSearchResult