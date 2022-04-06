import React, { useState, useEffect, useContext } from 'react'
import axios from 'axios'

// Components
import Post from './Home/Post'
import FollowingList from './Home/FollowingList'
import Loading from './common/Loading'
import Error from './common/Error'
import NoPost from './Home/NoPost'
import toast from 'react-hot-toast'

const Home = ({ user, setPage }) => {
    const [posts, setPosts] = useState(null)
    const [isLoading, setIsLoading] = useState(true)
    const [isPostFeedError, setIsPostFeedError] = useState(false)
    const [errorMessage, setErrorMessage] = useState(null)

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
                //setIsError(true)
                setErrorMessage(err)
            })
            .finally(() => {
                setIsLoading(false)
            })

    }, [])



    return (
        <div className='container row mt-5'>

            <div className='col-12 col-md-9'>
                <div>
                    {
                        isLoading ? <Loading /> :
                            isPostFeedError ? <Error errorMessage={errorMessage} /> :
                                posts != null ?
                                    posts.map((post) => {
                                        return (
                                            <Post post={post} />
                                        )
                                    }) : <NoPost />
                    }
                </div>
            </div>

            <div className='col-3 col-md-0 d-none d-md-block sticky-top'>
                <FollowingList
                    user={user}
                />
            </div>

        </div>
    )
}

export default Home