import React from "react";
import {
    BsFillHeartFill as LikeIcon,
    BsChatLeftFill as ReplyIcon,
} from "react-icons/bs";

import "./postThumbnail.css";

const PostThumbnail = ({
    postNum,
    postImg,
    likeCount,
    replyCount,
    openPostDetail,
}) => {
    return (
        <div
            onClick={() => {
                openPostDetail(postNum);
            }}
            className="post-thumbnail p-3"
        >
            <img
                src={"/images/" + postImg}
                alt="POST IMAGE"
                style={{ width: "350px", height: "350px" }}
            />

            <div className="row h2 text-center justify-content-center h-100 mh-100">
                <div className="col-2 text-dark">
                    <div className="align-self-center">
                        <LikeIcon className="text-danger" />
                    </div>
                </div>
                <div className="col-3 text-dark me-3 align-items-center">
                    {likeCount}
                </div>
                <div className="col-2 text-primary align-items-center">
                    <ReplyIcon />
                </div>
                <div className="col-3 text-dark align-items-center">
                    {replyCount}
                </div>
            </div>
        </div>
    );
};

export default PostThumbnail;
