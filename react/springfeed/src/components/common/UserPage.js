import React, { useEffect } from 'react'
import {useParams} from 'react-router-dom'

const UserPage = () => {
    const {id} = useParams()

    useEffect(() => {
    }, [])

    const userPostData = [
        {
            img: 'http://picsum.photos/300/300'
        },
        {
            img: 'http://picsum.photos/300/300/'
        },
        {
            img: 'http://picsum.photos/300/300//'
        },
        {
            img: 'http://picsum.photos/300/300///'
        },
        {
            img: 'http://picsum.photos/300/300////'
        },
    ]

  return (
    <div className='mt-5'>
        <div className="row border-bottom">
            <div className="col-4">
                <div className="row justify-content-center">
                    <img src="http://picsum.photos/100/100" alt="" className="rounded-circle" 
                        style={{width: '150px'}}
                    />
                </div>
            </div>

            <div className="col-8">
                <div className="row p-3">
                    <div className="col-6">
                        <div className="h3">
                            {id}
                        </div>
                    </div>
                    
                    <div className="col-3">
                        <div className="btn btn-danger w-100">
                            신고하기
                        </div>
                    </div>

                    <div className="col-3">
                        <div className="btn btn-success w-100">
                            팔로우
                        </div>
                    </div>
                    
                </div>

                <div className="row p-3">
                    <div className="col-4">3 posts</div>
                    <div className="col-4">45 followers</div>
                    <div className="col-4">63 following</div>
                </div>

                <div className="row p-4">
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Neque odio harum quisquam assumenda, ipsam iste voluptates quidem dolorum dignissimos consectetur et ad necessitatibus, perspiciatis eos, similique laborum nisi eligendi magnam!
                </div>
            </div>
        </div>

        <div className="row justify-content-center mt-3">
            <div className="col-3">
                <div className="btn btn-outline-primary w-100 active">POSTS</div>
            </div>
            <div className="col-3">
                <div className="btn btn-outline-primary w-100">SAVED</div>
            </div>
        </div>

        <div className="row mt-4 mb-5">
            {
                userPostData.map((post) => {
                    return(
                        <div className="col-4 mb-3">
                            <img src={post.img} alt="POST IMAGE"/>
                        </div>
                    )
                })
            }
        </div>
    </div>
  )
}

export default UserPage