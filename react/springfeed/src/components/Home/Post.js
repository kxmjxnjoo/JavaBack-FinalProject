import React, { useState } from 'react'
import toast from 'react-hot-toast'

// Icon
import {
    BsHeart as LikeIcon, BsFillHeartFill as LikeFillIcon, BsChatLeft as ReplyIcon, BsChatDots as MessageIcon,
    BsBookmark as SaveIcon, BsFillBookmarkFill as SaveFillIcon
} from 'react-icons/bs'
import { MdOutlineMoreHoriz as DetailIcon } from 'react-icons/md'

import defaultProfile from '../../images/tmpUserIcon.png'

const Post = ({ post }) => {
    const { user_img: userprofile, userid, address: location, post_img: postImage, likeCount: likes, isLiked, isSaved, content, post_num: postNum } = post

    const profileStyle = {
        width: '50px'
    }
    const iconStyle = {
        fontSize: '30px'
    }
    const postImageStyle = {
        width: '100%'
    }

    const [isLikedData, setIsLikedData] = useState(isLiked)

    const handleLike = () => {

        const postLike =
            fetch("/api/post/like?num=" + postNum)
                .then((res) => {
                    return res.json()
                })
                .then((data) => {
                    setIsLikedData(!isLikedData)
                })
                .catch((err) => {
                    return err
                })

        if (!isLikedData) {
            toast.promise(postLike,
                {
                    loading: '잠시만 기다려 주세요...',
                    success: userid + "님의 포스트를 좋아요 했어요",
                    error: "좋아요 하는데 에러가 났어요 다시 시도해 주세요"
                }
            )
        } else {
            toast.promise(postLike,
                {
                    loading: '잠시만 기다려 주세요...',
                    success: userid + "님의 포스트의 좋아요를 취소했어요",
                    error: "좋아요 취소하는데 에러가 났어요 다시 시도해 주세요"
                }
            )
        }
    }

    return (
        <div className='card mb-5'>
            <div className="card-header">
                <div className="row">
                    <div className="col-10">

                        <div className="row">
                            <div className="col-2">
                                <img src={userprofile == '' || userprofile == null ? defaultProfile : userprofile} alt="Profile" className="rounded-circle" style={profileStyle} />
                            </div>
                            <div className="col text-left">
                                <h6>{userid == '' || userid == null ? 'ERROR' : userid}</h6>
                                <h6 className='text-muted '>{location == '' ? 'ERROR' : location}</h6>
                            </div>
                        </div>
                    </div>

                    <DetailIcon className='col-2 mt-2' style={iconStyle} />
                </div>
            </div>

            <div className="card-body">
                <img src={postImage == '' || postImage == null ? "http://picsum.photos/200/200" : postImage} alt="" style={postImageStyle} />
            </div>

            <div className="card-footer h2">
                <div className="row">
                    <div className="col-10">
                        {
                            isLikedData ? <LikeFillIcon className='m-2 text-danger' onClick={handleLike} /> : <LikeIcon className='m-2' onClick={handleLike} />
                        }
                        <ReplyIcon className='m-2' />
                        <MessageIcon className='m-2' />
                    </div>
                    <div className="col-2">
                        {
                            isSaved ? <SaveFillIcon /> : <SaveIcon />
                        }

                    </div>
                </div>
                <div className="h4">{likes} likes</div>
                <p className='h5'>{content}</p>
            </div>

            <div className="card-footer">
                <form action="/reply/add">
                    <div className="row form-group">
                        <div className="col-10">
                            <input type="text" placeholder='댓글을 추가해 주세요...' className='form-control' />
                        </div>
                        <div className="col-2">
                            <input type="submit" value="추가" className='btn btn-outline-primary' />
                        </div>
                    </div>
                </form>
            </div>

        </div>
    )
}

export default Post