import React, { useState, useEffect, useContext } from 'react'
import axios from 'axios'

// Components
import Post from './Home/Post'
import FollowingList from './Home/FollowingList'
import Loading from './common/Loading'
import Error from './common/Error'
import NoPost from './Home/NoPost'

const Home = ({ user }) => {
    const [posts, setPosts] = useState(null)
    const [followingList, setFollowingList] = useState(null)

    const [isLoading, setIsLoading] = useState(true)
    const [isError, setIsError] = useState(null)
    const [errorMessage, setErrorMessage] = useState(null)

    useEffect(() => {
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
                console.log(err)
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
                            isError ? <Error errorMessage={errorMessage} /> :
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

            </div>

        </div>
    )
}

export default Home