import React, { useState, useEffect } from 'react'

// Components
import Post from '../components/Post'

const Home = () => {
    const [posts, setPosts] = useState(null)
    const [error, setError] = useState(false)
    const [isLoading, setIsLoading] = useState(true)

    useEffect(() => {
        const headers = new Headers({
        'Content-Type': 'application/json',
        });

        fetch('http://localhost:8070/api/post?userid=jinkpark', headers)
            .then((response) => {
                return response.json()
            })
            .then((data) => {
                setPosts(data)
            })
            .catch((err) => {
                    setError(true)
                    alert(err)
            })
            .finally(() => {
                setIsLoading(false)
            })
    }, [])
    
  return (
    <div className='container'>
        {
            isLoading ?
                "LOADING..."
            :

            error ? 
                "ERROR"
            : 
            posts.map((post) => {
                return(
                    <Post post={post}/>
                )
            })
        }
    </div>
  )
}

export default Home