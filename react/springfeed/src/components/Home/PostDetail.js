import React, { useEffect, useState } from "react";
import { MdOutlineMoreHoriz as DetailIcon } from "react-icons/md";
import {
    BsHeart as LikeIcon,
    BsFillHeartFill as LikeFillIcon,
    BsChatLeft as ReplyIcon,
    BsChatDots as MessageIcon,
    BsBookmark as SaveIcon,
    BsFillBookmarkFill as SaveFillIcon,
} from "react-icons/bs";

import defaultProfile from "../../images/tmpUserIcon.png";
import Loading from "../common/Loading";
import Error from "../common/Error";
import NoContent from "../common/NoContent";

import { Link } from "react-router-dom";

const PostDetail = ({ selectedPost }) => {
    const [isLoading, setIsLoading] = useState(true);
    const [post, setPost] = useState(null);
    const [replies, setReplies] = useState(null);

    useEffect(() => {
        Promise.all([
            fetch("/post/detail/" + selectedPost)
                .then((res) => {
                    return res.json();
                })
                .then((data) => {
                    setPost(data);
                })
                .catch((err) => {
                    return err;
                }),
            fetch("/post/detail/reply/" + selectedPost)
                .then((res) => {
                    return res.json();
                })
                .then((data) => {
                    setReplies(data);
                })
                .catch((err) => {
                    return err;
                }),
        ]).finally(() => {
            setIsLoading(false);
        });
    }, []);

    return (
        <div className="p-5">
            {isLoading ? (
                <Loading />
            ) : post == null ? (
                <Error className="m-5" />
            ) : (
                <div className="row ms-0 me-0" style={{ minHeight: "600px" }}>
                    <div className="col-6 ps-0">
                        <img
                            src={
                                post.post_img != null &&
                                post.post_img != undefined
                                    ? "/images/" + post.post_img
                                    : "http://picsum.photos/id/100/400/400"
                            }
                            alt=""
                            style={{ width: "100%", height: "100%" }}
                        />
                    </div>

                    <div className="col-6">
                        <div className="row mt-3" style={{ height: "20%" }}>
                            <div className="col-2 align-self-center">
                                <img
                                    src={
                                        post.userProfile != null
                                            ? "/images/" + post.userProfile
                                            : defaultProfile
                                    }
                                    alt=""
                                    style={{ width: "75px", height: "75px" }}
                                    className="rounded-circle"
                                />
                            </div>
                            <div className="col-8 align-self-center">
                                <div className="h3">
                                    {post.userid == "" ||
                                    post.userid == null ? (
                                        "USERID"
                                    ) : (
                                        <Link
                                            to={"/user/page/" + post.userid}
                                            className="text-decoration-none text-dark"
                                        >
                                            {post.userid}
                                        </Link>
                                    )}
                                </div>
                            </div>
                            {/* <div className="col-2 align-self-center">
                                <DetailIcon className="h1" />
                            </div> */}

                            <hr className="mt-3" />
                        </div>

                        <div
                            className="row"
                            style={{ height: "50%", overflow: "scroll" }}
                        >
                            <div className="col-12">
                                {replies == null || replies.length == 0 ? (
                                    <NoContent message="댓글이 없어요" />
                                ) : (
                                    replies.map((comment) => {
                                        return <Comment comment={comment} />;
                                    })
                                )}
                            </div>
                        </div>

                        <div
                            className="row h2 justify-content-center w-100 mt-5"
                            style={{ height: "20%" }}
                        >
                            <div className="col-10">
                                <LikeFillIcon className="m-2 text-danger" />
                                <MessageIcon className="m-2" />
                            </div>
                            <div className="col-2">
                                <SaveFillIcon />
                            </div>
                            <div className="h4">
                                {post.likes == null ? 0 : post.likes} likes
                            </div>
                            <p className="h5">{post.content}</p>

                            <form action="/reply/add">
                                <div className="row form-group">
                                    <div className="col-10">
                                        <input
                                            type="text"
                                            placeholder="댓글을 추가해 주세요..."
                                            className="form-control w-100 h-100"
                                        />
                                    </div>
                                    <div className="col-2 self-align-center">
                                        <input
                                            type="submit"
                                            value="추가"
                                            className="btn btn-outline-primary w-100 h-100"
                                        />
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default PostDetail;

const Comment = ({ comment }) => {
    const { userImg, userid, content, like, createdDate } = comment;

    return (
        <div className="row mb-2">
            <div className="col-2">
                <img
                    src={"/images/" + userImg}
                    alt="PROFILE"
                    className="img-fluid rounded-circle"
                    style={{ width: "100px" }}
                />
            </div>

            <div className="col-8">
                <div className="row">
                    <div className="col-2 me-3">
                        <b>{userid}</b>
                    </div>

                    <div className="col-9">{content}</div>
                </div>
                <div className="row text-muted">
                    {createdDate
                        .substring(2, createdDate.indexOf("T"))
                        .replace("-", "년 ")
                        .replace("-", "월 ")
                        .concat("일")}
                </div>
            </div>

            <div className="col-2">
                <LikeIcon />
            </div>
        </div>
    );
};
