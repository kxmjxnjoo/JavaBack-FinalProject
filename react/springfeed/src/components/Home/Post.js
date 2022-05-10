import React, { useState } from "react";
import toast from "react-hot-toast";

import { Link } from "react-router-dom";

// Icon
import {
    BsHeart as LikeIcon,
    BsFillHeartFill as LikeFillIcon,
    BsChatLeft as ReplyIcon,
    BsChatDots as MessageIcon,
    BsBookmark as SaveIcon,
    BsFillBookmarkFill as SaveFillIcon,
} from "react-icons/bs";
import { MdOutlineMoreHoriz as DetailIcon } from "react-icons/md";

import defaultProfile from "../../images/tmpUserIcon.png";

const Post = ({
    post,
    openPostDetail,
    setIsDetailMenuOpen,
    setSelectedPost,
    setSelectedUser,
}) => {
    const {
        user_img: userprofile,
        userid,
        address: location,
        post_img: postImage,
        likeCount: likes,
        isLiked,
        isSaved,
        content,
        postNum,
    } = post;

    const [reply, setReply] = useState(null);

    const profileStyle = {
        width: "50px",
    };
    const iconStyle = {
        fontSize: "30px",
    };
    const postImageStyle = {
        width: "100%",
    };

    const [isLikedData, setIsLikedData] = useState(isLiked);
    const [isSavedData, setIsSavedData] = useState(isSaved);

    const handleLike = () => {
        const postLike = fetch("/api/post/like?num=" + postNum)
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                setIsLikedData(!isLikedData);
            })
            .catch((err) => {
                return err;
            });

        if (!isLikedData) {
            toast.promise(postLike, {
                loading: "잠시만 기다려 주세요...",
                success: userid + "님의 포스트를 좋아요 했어요",
                error: "좋아요 하는데 에러가 났어요 다시 시도해 주세요",
            });
        } else {
            toast.promise(postLike, {
                loading: "잠시만 기다려 주세요...",
                success: userid + "님의 포스트의 좋아요를 취소했어요",
                error: "좋아요 취소하는데 에러가 났어요 다시 시도해 주세요",
            });
        }
    };

    const handleSave = () => {
        if (isSaved) {
            unSavePost(postNum);
        }
        {
            savePost(postNum);
        }
    };

    const savePost = (postnum) => {
        fetch("/api/post/save/insert", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=UTF-8",
            },
            body: JSON.stringify({
                postNum: postNum,
            }),
        }).then((res) => {
            if (res.text() == 0) {
                toast.error("포스트를 저장하지 못 했어요. 다시 시도해 주세요");
            } else {
                toast.success("포스트를 저장했어요");
                setIsSavedData(true);
            }
        });
    };

    const unSavePost = (postnum) => {
        fetch("/api/post/save/delete?num=" + postnum).then((res) => {
            if (res.text() == 0) {
                toast.error(
                    "저장한 포스트를 지우지 못 했어요. 다시 시도해 주세요"
                );
            } else {
                toast.success("포스트를 저장 목록에서 지웠어요");
                setIsSavedData(false);
            }
        });
    };

    return (
        <div className="card mb-5">
            <div className="card-header">
                <div className="row">
                    <div className="col-10">
                        <Link
                            to={"/user/page/" + userid}
                            style={{ textDecoration: "none", color: "black" }}
                        >
                            <div className="row">
                                <div className="col-2">
                                    <img
                                        src={
                                            userprofile == "" ||
                                            userprofile == null
                                                ? defaultProfile
                                                : "/images/" + userprofile
                                        }
                                        alt="Profile"
                                        className="rounded-circle"
                                        style={profileStyle}
                                    />
                                </div>
                                <div className="col text-left">
                                    <h6>
                                        {userid == "" || userid == null
                                            ? "ERROR"
                                            : userid}
                                    </h6>
                                    <h6 className="text-muted ">
                                        {location == "" || location == null
                                            ? "위치 정보 없음"
                                            : location}
                                    </h6>
                                </div>
                            </div>
                        </Link>
                    </div>

                    <DetailIcon
                        className="col-2 mt-2"
                        style={iconStyle}
                        onClick={() => {
                            setIsDetailMenuOpen(true);
                            setSelectedPost(post.postNum);
                            setSelectedUser(userid);
                        }}
                    />
                </div>
            </div>

            <div className="card-body">
                <img
                    src={
                        postImage == "" || postImage == null
                            ? "http://picsum.photos/100/100"
                            : "/images/" + postImage
                    }
                    alt="이미지를 불러올 수 없어요"
                    style={postImageStyle}
                />
            </div>

            <div className="card-footer h2">
                <div className="row">
                    <div className="col-11">
                        {isLikedData ? (
                            <LikeFillIcon
                                className="m-2 text-danger"
                                onClick={handleLike}
                            />
                        ) : (
                            <LikeIcon className="m-2" onClick={handleLike} />
                        )}
                        <ReplyIcon
                            className="m-2"
                            onClick={() => {
                                openPostDetail({ post });
                            }}
                        />
                        <MessageIcon className="m-2" />
                    </div>
                    <div className="col-1">
                        {isSavedData ? (
                            <SaveFillIcon onClick={handleSave} />
                        ) : (
                            <SaveIcon onClick={handleSave} />
                        )}
                    </div>
                </div>
                <div className="h4">{likes} likes</div>
                <p className="h5">{content}</p>
            </div>

            <div className="card-footer">
                <form>
                    <div className="row form-group">
                        <div className="col-10">
                            <input
                                type="text"
                                placeholder="댓글을 추가해 주세요..."
                                className="form-control"
                                value={reply}
                                onChange={(e) => setReply(e.target.value)}
                            />
                        </div>
                        <div className="col-2">
                            <input
                                type="submit"
                                value="추가"
                                className="btn btn-outline-primary w-100"
                                onClick={(e) => {
                                    e.preventDefault();

                                    if (reply == null || reply == "") {
                                        toast.error("댓글을 입력해 주세요");
                                        return;
                                    } else {
                                        let noti =
                                            toast.loading(
                                                "댓글을 추가하고 있어요"
                                            );

                                        fetch("/api/reply/add", {
                                            method: "POST",
                                            headers: {
                                                "Content-Type":
                                                    "application/json;charset=UTF-8",
                                            },
                                            body: JSON.stringify({
                                                postNum: postNum,
                                                content: reply,
                                            }),
                                        })
                                            .then((res) => {
                                                return res.json();
                                            })
                                            .then((data) => {
                                                if (data == 1) {
                                                    noti.success(
                                                        "댓글을 추가했어요",
                                                        {
                                                            id: noti,
                                                        }
                                                    );
                                                } else {
                                                    noti.error(
                                                        "댓글을 추가하지 못했어요. 다시 시도해 주세요",
                                                        {
                                                            id: noti,
                                                        }
                                                    );
                                                }
                                            })
                                            .finally(() => {
                                                noti.success(
                                                    "댓글을 추가했어요",
                                                    {
                                                        id: noti,
                                                    }
                                                );
                                            });
                                    }
                                }}
                            />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Post;
