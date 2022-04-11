import React from 'react'
import { MdOutlineMoreHoriz as DetailIcon } from 'react-icons/md'

const PostDetail = ({post}) => {
    const { user_img: userprofile, userid, address: location, 
        post_img: postImage, likeCount: likes, isLiked, 
        isSaved, content, post_num: postNum } = post


    return (
        <div className='container'>
            <div className="row">

                <div className="col-6">
                    <img src="http://picsum.photos/600/600" alt="" style={{width: '500px', height: '500px'}}/>
                </div>

                <div className="col-6">

                </div>
            </div>
        </div>
    )
}

export default PostDetail

/*
                    <div className="row">
                            <div className="col-10">
                                <div className="row">
                                    <div className="col-2">
                                        <img src={userprofile} alt="" />
                                    </div>

                                    <div className="col-8">
                                        <div className="row">
                                            {userid}
                                        </div>
                                        <div className="row text-muted">
                                            {location}
                                        </div>
                                    </div>

                                    <div className="col-2">
                                        <div className="btn btn-success">
                                            팔로우
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="col-2">
                                <DetailIcon/>
                            </div>
                        </div>
                </div>

*/