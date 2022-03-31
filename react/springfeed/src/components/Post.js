import React from 'react'

import 'bootstrap/dist/css/bootstrap.min.css'
import defultProfile from '../images/tmpUserIcon.png'

// Icon
import { BiAlignRight as DetailIcon } from 'react-icons/bi'
import {BsHeart as LikeIcon, BsFillHeartFill as LikeFillIcon, BsChatLeft as ReplyIcon, BsChatDots as MessageIcon,
                BsBookmark as SaveIcon, BsFillBookmarkFill as SaveFillIcon} from 'react-icons/bs'

const Post = ({post}) => {

    const {user_img:userprofile, userid, address:location, post_img:postImage, likeCount:likes, isLiked, isSaved, content} = post

    const profileStyle = {
        width: '50px'
    }
    const iconStyle = {
        fontSize: '30px'
    }
    const postImageStyle = {
        width: '100%'
    }

    return (
        <div className='card'>
            <div className="card-header">
                <div className="row">
                    <div className="col-10">

                        <div className="row">
                            <div className="col-2">
                                <img src={userprofile == '' || userprofile == null ? defultProfile : userprofile} alt="Profile" className="rounded-circle" style={profileStyle}/>
                            </div>
                            <div className="col text-left">
                                <h6>{userid == '' || userid == null ? 'ERROR' : userid}</h6>
                                <h6 className='text-muted '>{location == '' ? 'ERROR' : location}</h6>
                            </div>
                        </div>
                    </div>

                    <DetailIcon className='col-2' style={ iconStyle }/>
                </div>
            </div>

            <div className="card-body">
                <img src={postImage == '' || postImage == null ? "http://picsum.photos/200/200" : postImage} alt="" style={postImageStyle}/>
            </div>
            
            <div className="card-footer h2">
                <div className="row">
                    <div className="col-10">
                        {
                            isLiked ? <LikeFillIcon className='m-2 text-danger'/> : <LikeIcon className='m-2'/>
                        }
                        <ReplyIcon className='m-2'/>
                        <MessageIcon className='m-2'/>
                    </div>
                    <div className="col-2">
                        {
                            isSaved ? <SaveFillIcon/> : <SaveIcon/>
                        }
                        
                    </div>
                </div>
                <div className="h2">{ likes } likes</div>
                <p>{content}</p>
            </div>

             <div className="card-footer">
                <form action="/reply/add">
                    <div className="row form-group">
                        <div className="col-10">
                            <input type="text" placeholder='댓글을 추가해 주세요...' className='form-control'/> 
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