import React, { useState, useEffect, useContext } from "react";
import InifniteScroll from "react-infinite-scroller";

import { Link } from "react-router-dom";

// Components
import Post from "../post/Post";
import FollowingList from "./MainFollowingList";
import Loading from "../common/Loading";
import Error from "../common/Error";
import NoPost from "../post/NoPost";
import StoryList from "../story/StoryList";

import toast from "react-hot-toast";

import { Modal } from "react-bootstrap";
import Report from "../admin/Report";

const Home = ({ loginUser, setPage, setIsPostDetailOpen, setSelectedPost }) => {
    const [posts, setPosts] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const [isPostFeedError, setIsPostFeedError] = useState(false);
    const [errorMessage, setErrorMessage] = useState(null);

    const [isReportOpen, setIsReportOpen] = useState(false);
    const [selectedUser, setSelectedUser] = useState(null);
    const [isFollowingError, setIsFollowingError] = useState(false);

    const [followingList, setFollowingList] = useState(null);
    const [postPage, setPostPage] = useState(0);
    const [hasMore, setHasMore] = useState(true);

    const [isDetailMenuOpen, setIsDetailMenuOpen] = useState(false);
    const closeDetailMenu = () => {
        setIsDetailMenuOpen(false);
    };

    const openPostDetail = ({ post }) => {
        setIsPostDetailOpen(true);
        setSelectedPost(post.postNum);
    };

    const loadFeed = () => {
        setPostPage(postPage + 1);
        fetch("/api/post/feed?page=" + postPage)
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                if (data == null || data.length == 0) {
                    toast.error("포스트가 더 이상 없는거 같아요");
                    setHasMore(false);
                } else {
                    setPosts([...posts, ...data]);
                }
            })
            .catch((err) => {
                setHasMore(false);
                toast.error("에러가 났어요");
            });
    };

    useEffect(() => {
        setPage(0);

        Promise.all([
            fetch("/api/post/feed")
                .then((res) => {
                    return res.json();
                })
                .then((data) => {
                    setPosts(data);
                })
                .catch((err) => {
                    toast.error("에러가 났아요 : " + err);
                }),
            fetch("/api/user/following?id=" + loginUser.userid)
                .then((res) => {
                    return res.json();
                })
                .then((data) => {
                    setFollowingList(
                        data.filter((u) => {
                            return !(u.userid === loginUser.userid);
                        })
                    );
                })
                .catch((err) => {
                    setIsFollowingError(true);
                }),
        ]).finally(() => {
            setIsLoading(false);
        });
    }, []);

    return (
        <div className="container row">
            {isLoading ? (
                <Loading message="메인 화면을 불러오고 있어요" />
            ) : isPostFeedError ? (
                <Error errorMessage={errorMessage} />
            ) : (
                <>
                    <div className="col-12 col-md-9">
                        <div>
                            <StoryList user={loginUser} />
                        </div>

                        <div>
                            {posts != null || posts.length == 0 ? (
                                <InifniteScroll
                                    pageStart={postPage}
                                    loadMore={loadFeed}
                                    hasMore={
                                        posts.length < 10 ? false : hasMore
                                    }
                                    loader={
                                        <Loading
                                            message="포스트를 더 불러오고 있어요"
                                            className="mb-5 mt-5"
                                        />
                                    }
                                >
                                    {posts.map((post) => {
                                        return (
                                            <Post
                                                post={post}
                                                openPostDetail={openPostDetail}
                                                setIsDetailMenuOpen={
                                                    setIsDetailMenuOpen
                                                }
                                                setSelectedPost={
                                                    setSelectedPost
                                                }
                                                setSelectedUser={
                                                    setSelectedUser
                                                }
                                            />
                                        );
                                    })}
                                </InifniteScroll>
                            ) : (
                                <NoPost />
                            )}
                        </div>
                    </div>

                    <div
                        className="col-3 col-md-0 d-none d-md-block"
                        style={{ position: "fixed", left: "70%" }}
                    >
                        <FollowingList
                            user={loginUser}
                            followingList={followingList}
                            isFollowingError={isFollowingError}
                        />
                    </div>
                </>
            )}

            <Modal
                show={isDetailMenuOpen}
                onHide={closeDetailMenu}
                className="mt-5"
            >
                <div className="card">
                    <div className="row">
                        <div
                            className="btn text-danger p-3"
                            onClick={() => {
                                setIsReportOpen(true);
                            }}
                        >
                            신고하기
                        </div>
                    </div>
                    <div className="row">
                        <div
                            className="btn p-3"
                            onClick={() => {
                                setIsPostDetailOpen(true);
                            }}
                        >
                            포스트로 이동
                        </div>
                    </div>

                    <div className="row">
                        <Link
                            to={"/user/page/" + selectedUser}
                            className="text-center"
                        >
                            <div className="btn p-3">유저 페이지로 이동</div>
                        </Link>
                    </div>
                </div>
            </Modal>

            <Modal
                show={isReportOpen}
                onHide={() => {
                    setIsReportOpen(false);
                }}
                className="mt-5"
            >
                <Report />
            </Modal>
        </div>
    );
};

export default Home;
