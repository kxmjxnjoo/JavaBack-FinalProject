import React, { useState, useEffect, useContext } from 'react'
import axios from 'axios'
import InifniteScroll from 'react-infinite-scroller'

// Components
import Post from './Home/Post'
import FollowingList from './Home/FollowingList'
import Loading from './common/Loading'
import Error from './common/Error'
import NoPost from './Home/NoPost'
import toast from 'react-hot-toast'

import { Modal } from 'react-bootstrap'

const Home = ({ user, setPage, setIsPostDetailOpen, selectedPost, setSelectedPost }) => {
    const [posts, setPosts] = useState(null)
    const [isLoading, setIsLoading] = useState(true)
    const [isPostFeedError, setIsPostFeedError] = useState(false)
    const [errorMessage, setErrorMessage] = useState(null)
    const [postPage, setPostPage] = useState(1)

    const [isDetailMenuOpen, setIsDetailMenuOpen] = useState(false)
    const closeDetailMenu = () => {
        setIsDetailMenuOpen(false)
    }

    const openPostDetail = ({post}) => {
        setIsPostDetailOpen(true)
        setSelectedPost({post})
    }
    useEffect(() => {
        setPage(0)
        const head = new Headers({
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        });

        fetch("/api/post/feed", head)
            .then((res) => {
                return res.json()
            })
            .then((data) => {
                setPosts(data)
            })
            .catch((err) => {
                toast.error('에러가 났아요 : ' + err)
            })
            .finally(() => {
                setIsLoading(false)
            })

    }, [])

    const [hasMore, setHasMore] = useState(true)
    const loadFeed = () => {
        fetch('/api/post/feed?page=' + postPage)
            .then((res) => {
                setPostPage(postPage + 1)
                return res.json()
            })
            .then((data) => {
                if(data == null || data == '') {
                    setHasMore(false)
                    toast.error('포스트가 더 이상 없는거 같아요')
                } else {
                    setPosts([...posts, ...data])
                }
            })
            .catch((err) => {
                setHasMore(false)
                toast.error('포스트가 더 이상 없는거 같아요')
            })
    }



    return (
        <div className='container row mt-5'>

            <div className='col-12 col-md-9'>
                <div>
                    {
                        isLoading ? <Loading message='포스트를 불러오고 있어요'/> :
                            isPostFeedError ? <Error errorMessage={errorMessage} /> :
                                posts != null ?

                                    <InifniteScroll
                                        pageStart={postPage}
                                        loadMore={loadFeed}
                                        hasMore={hasMore}
                                        loader={<Loading message='포스트를 더 불러오고 있어요' className='mb-5'/>}
                                    >
                                        {
                                            posts.map((post) => {
                                                return (
                                                    <Post post={post}
                                                        openPostDetail={openPostDetail}
                                                        setIsDetailMenuOpen={setIsDetailMenuOpen}
                                                        setSelectedPost={setSelectedPost}
                                                    />
                                                )
                                            }) 
                                        }
                                    </InifniteScroll>
                                    
                                    : <NoPost />
                    }
                </div>
            </div>

            <div className='col-3 col-md-0 d-none d-md-block sticky-top'>
                <FollowingList
                    user={user}
                />
            </div>

            <Modal
                show={isDetailMenuOpen}
                onHide={closeDetailMenu}
                className='mt-5'
            >
                <div className="card">
                    <div className="row">
                        <div className="btn text-danger p-3">
                            언팔로우
                        </div>
                    </div>
                    <div className="row">
                        <div className="btn text-danger p-3">
                            신고하기
                        </div>
                    </div>
                    <div className="row">
                        <div className="btn p-3">
                            포스트로 이동
                        </div>
                    </div>
                </div>
            </Modal>

        </div>
    )
}

export default Home