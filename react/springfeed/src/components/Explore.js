import React, { useEffect, useState } from 'react'
import toast from 'react-hot-toast'
import Loading from './common/Loading'
import Error from './common/Error'

import './explore.css'

import PostThumbnail from './common/PostThumbnail'

const Explore = ({ setPage, setIsPostDetailOpen, setSelectedPost, setIsSelectOpen }) => {
    const [isLoading, setIsLoading] = useState(true)
    const [isError, setIsError] = useState(false)
    const [errorMessage, setErrorMessage] = useState(null)
    const [exploreData, setExploreData] = useState([])

    useEffect(() => {
        setPage(3)
        setIsSelectOpen(false)
        
        fetch('/api/explore/feed')
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                if(data == null || data == '' || typeof data == 'undefined') {
                    setIsError(true)
                } else {
                    console.log(data)
                    setExploreData([...exploreData, [...data]]);
                    console.log(exploreData)
                }
                setIsLoading(false)
            })
            .catch((err) => {
                toast.error('에러가 났어요 : ' + err);
                setErrorMessage(err)
                setIsError(true)
            })
    }, [])


    const openPostDetail = (postNum) => {
        setIsPostDetailOpen(true)
        setSelectedPost(postNum)
    }

    return (

        <div className='mb-5'>
            {
                isLoading ? <Loading message='인기 포스트를 불러오고 있어요' className='mt-5' /> :
                    <>
                        {   isError ? 
                                <Error message='로딩하는데 오류가 났거나 인기 포스트가 없어요' errorMessage={errorMessage}/>
                            :
                            <div className="row">
                                {
                                    (exploreData == null || exploreData.length == 0) ?
                                    <Error message='로딩하는데 오류가 났거나 인기 포스트가 없어요' errorMessage=''/>
                                    :
                                    exploreData.map((data) => {
                                        return(
                                            <>
                                                <div className="row">
                                                    <div className="col-12 col-md-4 p-0">
                                                    <PostThumbnail
                                                        postNum={data[0].postNum}
                                                        postImg='http://picsum.photos/400/400/'
                                                        likeCount={data[0].likeCount}
                                                        replyCount={data[0].replyCount}
                                                        openPostDetail={openPostDetail}

                                                        className=''
                                                        />
                                                        <PostThumbnail
                                                        postNum={data[1].postNum}
                                                        postImg='http://picsum.photos/400/400//'
                                                        likeCount={data[1].likeCount}
                                                        replyCount={data[1].replyCount}
                                                        openPostDetail={openPostDetail}

                                                        className=''
                                                        />
                                                    </div>
                                                    <div className="col-12 col-md-8 p-0">
                                                        <PostThumbnail
                                                            postNum={data[2].postNum}
                                                            postImg='http://picsum.photos/400/400///'
                                                            likeCount={data[2].likeCount}
                                                            replyCount={data[2].replyCount}
                                                            openPostDetail={openPostDetail}

                                                            className=''
                                                            style={{objectFit: 'cover', height: '800px'}}
                                                        />
                                                    </div>
                                                </div>

                                                <div className="row p">
                                                    <div className="col-12 col-md-4 p-0">
                                                        <PostThumbnail
                                                            postNum={data[3].postNum}
                                                            postImg='http://picsum.photos/400/400////'
                                                            likeCount={data[3].likeCount}
                                                            replyCount={data[3].replyCount}
                                                            openPostDetail={openPostDetail}

                                                            className=''
                                                        />

                                                    </div>
                                                    <div className="col-12 col-md-4 p-0">
                                                        <PostThumbnail
                                                            postNum={data[4].postNum}
                                                            postImg='http://picsum.photos/400/400/////'
                                                            likeCount={data[4].likeCount}
                                                            replyCount={data[4].replyCount}
                                                            openPostDetail={openPostDetail}

                                                            className='p-2'
                                                        />

                                                    </div>
                                                    <div className="col-12 col-md-4 p-0">
                                                        <PostThumbnail
                                                            postNum={data[5].postNum}
                                                            postImg='http://picsum.photos/400/400//////'
                                                            likeCount={data[5].likeCount}
                                                            replyCount={data[5].replyCount}
                                                            openPostDetail={openPostDetail}

                                                            className=''
                                                        />
                                                    </div>
                                                </div>
                                            </>
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