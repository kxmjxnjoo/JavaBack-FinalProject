import React from 'react'
import {BsFillHeartFill as LikeIcon, BsChatLeftFill as ReplyIcon} from 'react-icons/bs'

import './postThumbnail.css'

const PostThumbnail = ({postNum, postImg, likeCount, replyCount, openPostDetail}) => {
    const imgStyle = {
        objectFit: 'cover',
        backgroundImage: 'url("http://picsum.photos/500/500")',
        width: '100%',
        height: '400px'
    }
    
    return (
        <div
            onClick={
                () => {
                    openPostDetail(postNum)
                }
            }
            style={imgStyle}
            className='post-thumbnail'
        >
            <div className="row h2 m-0 text-center justify-content-center h-100 mh-100">
                <div className="col-2 text-dark">
                    <div className="align-self-center">
                        <LikeIcon/>
                    </div>
                </div>
                <div className="col-3 text-dark me-3 align-items-center">
                    {likeCount}
                </div>
                <div className="col-2 text-dark align-items-center">
                    <ReplyIcon/>
                </div>
                <div className="col-3 text-dark align-items-center">
                    {replyCount}
                </div>
            </div>
        </div>
)
}

export default PostThumbnail