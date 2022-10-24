import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import React, { useState } from "react";
import { Toaster } from "react-hot-toast";
import { _ } from "lodash";

// Bootstrap
import { Modal } from "react-bootstrap";

// Components
import Home from "./components/home/Home";
import Topnav from "./components/common/Topnav";
import Loading from "./components/common/Loading";
import Search from "./components/search/Search";
import Message from "./components/message/Message";
import Error from "./components/common/Error";
import Explore from "./components/explore/Explore";
import PostDetail from "./components/post/PostDetail";
import Noti from "./components/noti/Noti";
import Login from "./components/user/Login";
import Logout from "./components/user/Logout";
import Join from "./components/user/Join";
import UserEdit from "./components/user/UserEdit";
import UploadStory from "./components/story/UploadStory";
import UploadPost from "./components/post/UploadPost";
import Find from "./components/user/Find";
import Admin from "./components/admin/Admin";
import Report from "./components/admin/Report";
import StoryNum from "./components/story/StoryNum";
import Post from "./components/post/Post";
import UserPage from "./components/user/UserPage";
import Footer from "./components/common/Footer";
import Qna from "./components/admin/Qna";
import Faq from "./components/admin/Faq";
import Story from "./components/story/Story";
import Select from "./components/post/Select";

function App() {
    const [page, setPage] = useState(0);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [loginUser, setLoginUser] = useState(null);

    const [isLoading, setIsLoading] = useState(false);
    const [isError, setIsError] = useState(false);
    const [errorMessage, setErrorMessage] = useState(null);
    const [searchKey, setSearchKey] = useState("");

    const [selectedPost, setSelectedPost] = useState(null);

    const [isSelectOpen, setIsSelectOpen] = useState(false);
    const [isReportOpen, setIsReportOpen] = useState(false);
    const [isPostDetailOpen, setIsPostDetailOpen] = useState(false);

    return (
        <div className="App d-flex flex-column min-vh-100">
            <Router>
                <div>
                    <Toaster position="bottom-right" reverseOrder="false" />
                </div>

                <Topnav
                    page={page}
                    isLoggedIn={isLoggedIn}
                    user={loginUser}
                    searchKey={searchKey}
                    setSearchKey={setSearchKey}
                    setIsSelectOpen={setIsSelectOpen}
                    setUser={setLoginUser}
                    setIsLoggedIn={setIsLoggedIn}
                />

                {searchKey !== "" && (
                    <div
                        className="card w-50 ms-5 mt-md-5 mt-6 overflow-auto shadow-lg"
                        style={{
                            height: "300px",
                            position: "fixed",
                            zIndex: "100",
                            top: "50px",
                            left: "50px",
                        }}
                    >
                        <div className="card-header">
                            <div className="card-title h5 text-center">
                                검색 결과
                            </div>
                        </div>
                        <div className="card-body pt-0">
                            <Search
                                searchKey={searchKey}
                                setIsPostDetailOpen={setIsPostDetailOpen}
                                setSelectedPost={setSelectedPost}
                            />
                        </div>
                    </div>
                )}

                <div className="container min-vh-100">
                    {isLoading ? (
                        <Loading message="로딩중이에요" />
                    ) : isError ? (
                        <Error errorMessage={errorMessage} />
                    ) : !isLoggedIn ? (
                        <Login
                            setIsLoggedIn={setIsLoggedIn}
                            user={loginUser}
                            setUser={setLoginUser}
                            setPage={setPage}
                            setSelectedPost={setSelectedPost}
                            setIsPostDetailOpen={setIsPostDetailOpen}
                        />
                    ) : (
                        <Routes>
                            <Route
                                path="/"
                                element={
                                    <Home
                                        loginUser={loginUser}
                                        setPage={setPage}
                                        setSelectedPost={setSelectedPost}
                                        setIsPostDetailOpen={
                                            setIsPostDetailOpen
                                        }
                                    />
                                }
                            />
                            <Route path="/search" element={<Search />} />
                            <Route
                                path="/message"
                                element={
                                    <Message
                                        setPage={setPage}
                                        user={loginUser}
                                    />
                                }
                            />
                            <Route
                                path="/explore"
                                element={
                                    <Explore
                                        setPage={setPage}
                                        setIsPostDetailOpen={
                                            setIsPostDetailOpen
                                        }
                                        setSelectedPost={setSelectedPost}
                                    />
                                }
                            />
                            <Route path="/noti" element={<Noti />} />
                            <Route
                                path="/user/page/:id"
                                element={
                                    <UserPage
                                        setSearchKey={setSearchKey}
                                        isLoggedIn={isLoggedIn}
                                        loginUser={loginUser}
                                        setIsPostDetailOpen={
                                            setIsPostDetailOpen
                                        }
                                        openPostDetail={(postNum) => {
                                            setIsPostDetailOpen(true);
                                            setSelectedPost(postNum);
                                        }}
                                    />
                                }
                            />
                            <Route
                                path="/login"
                                element={
                                    <Login
                                        isLoggedIn={isLoggedIn}
                                        setIsLoggedIn={setIsLoggedIn}
                                    />
                                }
                            />
                            <Route
                                path="/logout"
                                element={
                                    <Logout setIsLoggedIn={setIsLoggedIn} />
                                }
                            />
                            <Route path="/join" element={<Join />} />
                            <Route
                                path="/user/edit"
                                element={
                                    !isLoggedIn ? (
                                        <Login isLoggedIn={isLoggedIn} />
                                    ) : (
                                        <UserEdit />
                                    )
                                }
                            />
                            <Route path="/faq" element={<Faq />} />
                            <Route path="/qna" element={<Qna />} />
                            <Route
                                path="/upload/story"
                                element={<UploadStory />}
                            />
                            <Route
                                path="/upload/post"
                                element={<UploadPost />}
                            />
                            <Route path="/find/account" element={<Find />} />
                            <Route path="/admin" element={<Admin />} />
                            <Route path="/story/:id" element={<Story />} />
                            <Route
                                path="/storynum/:num"
                                element={<StoryNum />}
                            />
                            <Route path="/post/:num" element={<Post />} />
                        </Routes>
                    )}
                </div>

                <Footer />

                <Modal
                    show={isPostDetailOpen}
                    onHide={() => {
                        setIsPostDetailOpen(false);
                    }}
                    dialogClassName="postDetailModal"
                    className="mt-5"
                >
                    <PostDetail selectedPost={selectedPost} />
                </Modal>

                <Modal
                    show={isSelectOpen}
                    onHide={() => {
                        setIsSelectOpen(false);
                    }}
                    dialogClassName="selectModal"
                    className="mt-5"
                >
                    <Select />
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
            </Router>
        </div>
    );
}

export default App;
