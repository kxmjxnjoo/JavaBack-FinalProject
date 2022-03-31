import React from 'react'

// Components
import Post from '../components/Post'

const Home = () => {
    const posts = [
        {
            userprofile: '',
            userid: 'JINKPARK',
            location: '서울시 영등포구',
            postImage:  'http://picsum.photos/id/100/200/200',
            likes: 4,
            isLiked: true,
            isSaved: true
        },
        {
            userprofile: '',
            userid: 'JINKPARK',
            location: '서울시 영등포구',
            postImage:  'http://picsum.photos/id/200/200/200',
            likes: 4,
            isLiked: true,
            isSaved: false
        },
        {
            userprofile: '',
            userid: 'JINKPARK',
            location: '서울시 강남구',
            postImage:  'http://picsum.photos/id/110/200/200',
            likes: 1,
            isLiked: false,
            isSaved: true
        }
    ]

  return (
    <div>
        {
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