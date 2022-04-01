import React, { useState, useEffect } from 'react'

// Components
import Post from '../components/Post'
import FollowingList from '../components/Home/FollowingList'
import Loading from '../components/Loading'
import Error from '../components/Error'

const Home = ({user}) => {
    const [posts, setPosts] = useState(null)
    const [error, setError] = useState(false)
    const [errorMessage, setErrorMessage] = useState(null)
    const [isLoading, setIsLoading] = useState(true)
    const [followingList, setFollowingList] = useState(null)

    useEffect(() => {
        const headers = new Headers({
        'Content-Type': 'application/json',
        });

        fetch(`http://localhost:8070/api/post?userid=jinkpark`, headers)
            .then((response) => {
                return response.json()
            })
            .then((data) => {
                setPosts(data)
            })
            .catch((err) => {
                    setError(true)
                    setErrorMessage(err.message)
            })
            .finally(() => {
                setIsLoading(false)
            })
    }, [])
    
  return (
    <div className='container row mt-5'>

        <div className='col-12 col-md-9'>
            <div></div>

            <div>
            {
                isLoading ? <Loading/> :
                    error ?  <Error message={errorMessage}/> : 
                        posts.map((post) => {
                            return(
                                <Post post={post}/>
                            )
                        })
            }
            </div>
        </div>

        <div className='col-3 col-md-0 d-none d-md-block sticky-top'>
  
        </div>

    </div>
  )
}

export default Home