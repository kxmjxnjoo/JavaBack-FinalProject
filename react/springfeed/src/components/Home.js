import React, { useState, useEffect, useContext } from "react";
import InifniteScroll from "react-infinite-scroller";

import { Link } from "react-router-dom";

// Components
import Post from "./Home/Post";
import FollowingList from "./Home/MainFollowingList";
import Loading from "./common/Loading";
import Error from "./common/Error";
import NoPost from "./Home/NoPost";
import StoryList from "./Home/StoryList";

import toast from "react-hot-toast";

import { Modal } from "react-bootstrap";
import Report from "./jsp-components/Report";

const Home = ({
    user,
    setPage,
    setIsPostDetailOpen,
    setSelectedPost,
    setIsSelectOpen,
}) => {
    const [posts, setPosts] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const [isPostFeedError, setIsPostFeedError] = useState(false);
    const [errorMessage, setErrorMessage] = useState(null);

    const [isReportOpen, setIsReportOpen] = useState(false);
    const [selectedUser, setSelectedUser] = useState(null);

    const [isDetailMenuOpen, setIsDetailMenuOpen] = useState(false);
    const closeDetailMenu = () => {
        setIsDetailMenuOpen(false);
    };

    const openPostDetail = ({ post }) => {
        setIsPostDetailOpen(true);
        setSelectedPost(post.postNum);
    };
    useEffect(() => {
        setPage(0);
        setIsSelectOpen(false);

        fetch("/api/post/feed")
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                setPosts(data);
            })
            .catch((err) => {
                toast.error("에러가 났아요 : " + err);
            })
            .finally(() => {
                setIsLoading(false);
            });
    }, []);

    const [postPage, setPostPage] = useState(0);
    const [hasMore, setHasMore] = useState(true);

    const loadFeed = () => {
        fetch("/api/post/feed?page=" + postPage)
            .then((res) => {
                setPostPage(postPage + 1);

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

    return (
        <div className="container row">
            <div className="col-12 col-md-9">
                <div>
                    <StoryList />
                </div>

                <div>
                    {isLoading ? (
                        <Loading message="포스트를 불러오고 있어요" />
                    ) : isPostFeedError ? (
                        <Error errorMessage={errorMessage} />
                    ) : posts != null || posts.length == 0 ? (
                        <InifniteScroll
                            pageStart={postPage}
                            loadMore={loadFeed}
                            hasMore={hasMore}
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
                                        setSelectedPost={setSelectedPost}
                                        setSelectedUser={setSelectedUser}
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
                <FollowingList user={user} loginUser={user} />
            </div>

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
