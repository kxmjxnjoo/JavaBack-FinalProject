import React, { useEffect, useState } from 'react'
import toast from 'react-hot-toast'
import Loading from './common/Loading'
import Error from './common/Error'

import './explore.css'

import {BsFillHeartFill as LikeIcon, BsChatLeftFill as ReplyIcon} from 'react-icons/bs'

const Explore = ({ setPage, setIsPostDetailOpen, setSelectedPost }) => {
    const [isLoading, setIsLoading] = useState(true)
    const [isError, setIsError] = useState(false)
    const [errorMessage, setErrorMessage] = useState(null)
    const [exploreData, setExploreData] = useState(null)

    useEffect(() => {
        setPage(3)
        
        fetch('/api/explore/feed')
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                if(data == null || data == '' || typeof data == 'undefined') {
                    setIsError(true)
                } else {
                    setExploreData(data);
                    console.log("DATA : " + data);
                }
                setIsLoading(false)
            })
            .catch((err) => {
                toast.error('에러가 났어요 : ' + err);
                setErrorMessage(err)
                setIsError(true)
            })
    }, [])


    const imgStyle = {
        width: '100%',
        objectFit: 'cover'
    }

    const openPostDetail = (postNum) => {
        setIsPostDetailOpen(true)
        setSelectedPost(postNum)
    }

    return (

        <div className='mt-5 mb-5'>
            {
                isLoading ? <Loading message='인기 포스트를 불러오고 있어요' className='mt-5' /> :
                    <>
                        {   isError ? 
                                <Error message='로딩하는데 오류가 났거나 인기 포스트가 없어요' errorMessage={errorMessage}/>
                            :
                            <div className="row">
                                {
                                    exploreData.map((data, index) => {
                                        return(
                                            <div className={'p-0 ' + ((Math.floor(index + 1) / 3 % 2) == 0 ? 'col-4' : (index + 1) % 3 == 0 ? 'col-4' : 'col-4')}
                                                onClick={
                                                    () => {
                                                        openPostDetail(data.postNum)
                                                    }
                                                }
                                            >


                                                <div className="row justify-content-center h2 position-absolute text-white m-0">
                                                    <div className="col-2 text-dark">
                                                        <LikeIcon/>
                                                    </div>
                                                    <div className="col-2 text-dark me-3">
                                                        {data.likeCount}
                                                    </div>
                                                    <div className="col-2 text-dark">
                                                        <ReplyIcon/>
                                                    </div>
                                                    <div className="col-2 text-dark">
                                                        {data.replyCount}
                                                    </div>
                                                </div>

                                                <img src="http://picsum.photos/500/500" alt="" style={imgStyle} className='w-100 img-responsive exploreImg' />


                                            </div>
                                        )
                                    })
                                }
                            </div>
                        }
                    </>
            }
        </div>

    )
}

export default Explore