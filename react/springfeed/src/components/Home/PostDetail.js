import React from 'react'
import { MdOutlineMoreHoriz as DetailIcon } from 'react-icons/md'
import { BsHeart as LikeIcon, BsFillHeartFill as LikeFillIcon, BsChatLeft as ReplyIcon, BsChatDots as MessageIcon,
    BsBookmark as SaveIcon, BsFillBookmarkFill as SaveFillIcon } from 'react-icons/bs'

const PostDetail = ({post}) => {
    const { user_img: userprofile, userid, address: location, 
        post_img: postImage, likeCount: likes, isLiked, 
        isSaved, content, post_num: postNum } = post


    const commentDummyData = [
        {
            userImg: 'http://picsum.photos/400/400',
            userid: 'USERID1',
            content: 'HEYYYYYYYYYY',
            like: 1,
            createdDate: ''
        },
        {
            userImg: 'http://picsum.photos/400/400/',
            userid: 'USERID2',
            content: '',
            like: 5,
            createdDate: ''
        },
        {
            userImg: 'http://picsum.photos/400/400//',
            userid: 'USERID3',
            content: 'HEYYYYYYYYYY',
            like: 1000,
            createdDate: ''
        },
        {
            userImg: 'http://picsum.photos/400/400///',
            userid: 'USERID4',
            content: 'HEYYYYYYYYYY',
            like: 3,
            createdDate: ''
        },
    ]


    return (
        <div className="row ms-0 me-0">

            <div className="col-6 ps-0">
                <img src="http://picsum.photos/600/600" alt="" style={{width: '100%'}}/>
            </div>

            <div className="col-6">
                <div className="row mt-3" style={{height: '20%'}}>
                    <div className="col-2">
                        <img src="http://picsum.photos/200/200" alt="" style={{width: '75px'}} className='rounded-circle'/>
                    </div>
                    <div className="col-8 align-self-center">
                        <div className="h3">{userid == '' || userid == null ? 'USERID' : userid}</div>
                    </div>
                    <div className="col-2 align-self-center">
                        <DetailIcon className='h1'/>
                    </div>

                    <hr className='mt-3'/>
                </div>

                <div className="row" style={{height: '50%', overflow: 'scroll'}}>
                    <div className="col-12">
                    {
                        commentDummyData.map((comment) => {
                            return(
                                <Comment comment={comment}/>
                            )
                        })
                    }
                    </div>
                </div>



                <div className="row h2 justify-content-center w-100" style={{height: '20%'}}>

                    <div className="col-10">
                        <LikeFillIcon className='m-2 text-danger'/>
                        <MessageIcon className='m-2' />
                    </div>
                    <div className="col-2">
                        <SaveFillIcon/>
                    </div>
                    <div className="h4">{likes == null ? 0 : likes} likes</div>
                    <p className='h5'>{content}</p>

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
        </div>
    )
}

export default PostDetail

const Comment = ({comment}) => {
    const {userImg, userid, content, like, createdDate} = comment

    return(
        <div className="row mb-2">
            <div className="col-2">
                <img src={userImg} alt="PROFILE" className="img-fluid rounded-circle" style={{width: '100px'}}/>
            </div>

            <div className="col-8">
                <div className="row">
                    <div className="col-2 me-3">
                        <b>{userid}</b>
                    </div>

                    <div className="col-9">
                        {content}
                    </div>
                </div>
                <div className="row text-muted">
                    {createdDate}
                </div>
            </div>

            <div className="col-2">
                <LikeIcon/>
            </div>
        </div>
    )
}

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