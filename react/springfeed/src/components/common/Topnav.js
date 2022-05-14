import React, { useState, useRef, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import { _ } from "lodash";

// Bootstrap
import { Navbar, Dropdown } from "react-bootstrap";

// Icons
import {
    BsFillHouseDoorFill as HomeIcon,
    BsMessenger as MessageIcon,
    BsFillBellFill as NotiIcon,
    BsFillPlusSquareFill as AddIcon,
    BsSearch as SearchIcon,
} from "react-icons/bs";
import { MdExplore as ExploreIcon } from "react-icons/md";

// Resources
import logo from "../../images/logo.png";
import defaultProfile from "../../images/tmpUserIcon.png";
import "../../resources/topnav.css";
import toast from "react-hot-toast";

const Topnav = ({
    page,
    isLoggedIn,
    user,
    setSearchKey,
    setIsSelectOpen,
    setUser,
    setIsLoggedIn,
}) => {
    const [notiCount, setNotiCount] = useState(0);

    const logoStyle = {
        width: "30px",
        filter: "opacity(0.5) drop-shadow(0 0 0 blue)",
    };
    const topnavStyle = {
        backgroundColor: "var(--mainColor)",
        color: "white",
        zIndex: "2000",
    };
    const profileStyle = {
        width: "40px",
    };
    const handleSearch = (e) => {
        setSearchKey(e.target.value);
    };

    const [isShowSelect, setIsShowSelect] = useState(false);

    const showSelect = () => {
        setIsShowSelect(true);
    };

    const hideSelect = () => {
        setIsShowSelect(false);
    };

    useEffect(() => {
        if (user != null) {
            fetch("/api/noti/count?userid=" + user.userid)
                .then((res) => {
                    return res.text();
                })
                .then((noti) => {
                    setNotiCount(noti);
                });
        }
    }, []);

    const location = useLocation();

    useEffect(() => {
        setIsSelectOpen(false);

        fetch("/api/user/login")
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                if (data.length == 0) {
                    setUser({});

                    setIsLoggedIn(false);
                    toast(
                        "안녕하세요! Spring Feed를 이용하기 위해 로그인 해 주세요",
                        {
                            icon: "👋",
                        }
                    );
                } else {
                    setUser(data[0]);

                    setIsLoggedIn(true);
                    toast.success("안녕하세요 " + data.name + "님!");
                }
            })
            .catch((err) => {
                return err;
            });
    }, [location]);

    return (
        <Navbar
            style={topnavStyle}
            expand="lg"
            className="p-lg-0 position-fixed top-0 left-0 w-100"
        >
            <div className="container">
                <Link to="/" className="text-decoration-none navbar-brand">
                    <Navbar.Brand className="text-light">
                        <img
                            src={logo}
                            alt=""
                            className="d-inline-block align-top me-1"
                            style={logoStyle}
                        />
                        Springfeed
                    </Navbar.Brand>
                </Link>

                <Navbar.Toggle aria-controls="topnav-toggle"></Navbar.Toggle>

                <Navbar.Collapse id="navbar-toggle">
                    <form
                        className="form-inline my-2 my-lg-0 col-12 col-lg-5"
                        autocomplete="off"
                    >
                        <input
                            type="text"
                            className="form-control mr-sm-2 col-lg-5"
                            placeholder="🔎 검색"
                            aria-label="Sesarch"
                            name="key"
                            onChange={handleSearch}
                        />
                    </form>

                    <div className="col-12 col-lg-6">
                        <div className="nav navbar-nav mr-auto mt-2 mt-lg-0 h3 float-end">
                            <div className="row justify-content-center">
                                <div className="col-2 p-lg-0 align-self-center">
                                    <Link to="/">
                                        <a
                                            className={
                                                page === 0
                                                    ? "nav-link active"
                                                    : "nav-link"
                                            }
                                        >
                                            <HomeIcon
                                                style={{ color: "white" }}
                                            />
                                        </a>
                                    </Link>
                                </div>
                                <div className="col-2 p-lg-0 align-self-center">
                                    <Link to="/message">
                                        <a
                                            className={
                                                page === 1
                                                    ? "nav-link active"
                                                    : "nav-link"
                                            }
                                        >
                                            <MessageIcon
                                                style={{ color: "white" }}
                                            />
                                        </a>
                                    </Link>
                                </div>
                                <div className="col-2 p-lg-0 align-self-center">
                                    <button
                                        className={
                                            page === 2
                                                ? "nav-link active"
                                                : "nav-link"
                                        }
                                        style={{
                                            border: "none",
                                            backgroundColor: "transparent",
                                        }}
                                        onClick={() => {
                                            setIsSelectOpen(true);
                                        }}
                                    >
                                        <AddIcon style={{ color: "white" }} />
                                    </button>
                                </div>
                                <div className="col-2 p-lg-0 align-self-center">
                                    <Link to="/explore">
                                        <a
                                            className={
                                                page === 3
                                                    ? "nav-link active"
                                                    : "nav-link"
                                            }
                                            href="/explore"
                                        >
                                            <ExploreIcon
                                                style={{ color: "white" }}
                                            />
                                        </a>
                                    </Link>
                                </div>
                                <div className="col-2 p-lg-0 align-self-center">
                                    <Link to="/noti">
                                        <a
                                            className={
                                                page === 4
                                                    ? "position-relative nav-link active"
                                                    : "position-relative nav-link"
                                            }
                                            href="/noti"
                                        >
                                            <NotiIcon
                                                style={{ color: "white" }}
                                            />
                                            <span
                                                className={
                                                    "position-absolute top-45 start-90 translate-middle badge rounded-pill p-1 " +
                                                    (notiCount == 0
                                                        ? "bg-primary"
                                                        : "bg-danger")
                                                }
                                                style={{
                                                    width: "30px",
                                                    height: "30px",
                                                }}
                                            >
                                                {notiCount}
                                            </span>
                                        </a>
                                    </Link>
                                </div>
                                <div className="col-2 p-lg-0 align-self-center">
                                    <Dropdown align="end">
                                        <Dropdown.Toggle
                                            variant="none"
                                            id="dropdown-basic"
                                            className="p-0"
                                        >
                                            <a className="nav-link">
                                                <img
                                                    src={
                                                        // isLoggedIn
                                                        //     ? user.img != null
                                                        //         ? "/images/" +
                                                        //           user.img
                                                        //         : defaultProfile
                                                        //     : defaultProfile
                                                        defaultProfile
                                                    }
                                                    alt="ERR"
                                                    className="rounded-circle"
                                                    style={profileStyle}
                                                />
                                            </a>
                                        </Dropdown.Toggle>

                                        <Dropdown.Menu>
                                            {isLoggedIn ? (
                                                <>
                                                    <Link
                                                        to={
                                                            "/user/page/" +
                                                            (user != null
                                                                ? user.userid
                                                                : "")
                                                        }
                                                        className="text-decoration-none"
                                                    >
                                                        <Dropdown.Item href="/user/page">
                                                            내 유저 페이지로
                                                            이동
                                                        </Dropdown.Item>
                                                    </Link>
                                                    <Link
                                                        to="/user/edit"
                                                        className="text-decoration-none"
                                                    >
                                                        <Dropdown.Item href="/user/edit">
                                                            내 정보 수정
                                                        </Dropdown.Item>
                                                    </Link>
                                                    <Link
                                                        to="/logout"
                                                        className="text-decoration-none"
                                                    >
                                                        <Dropdown.Item
                                                            href="/logout"
                                                            className="bg-danger text-white"
                                                        >
                                                            로그아웃
                                                        </Dropdown.Item>
                                                    </Link>
                                                </>
                                            ) : (
                                                <>
                                                    <Link
                                                        to="/login"
                                                        className="text-decoration-none"
                                                    >
                                                        <Dropdown.Item href="/login">
                                                            로그인
                                                        </Dropdown.Item>
                                                    </Link>
                                                </>
                                            )}
                                        </Dropdown.Menu>
                                    </Dropdown>
                                </div>
                            </div>
                        </div>
                    </div>
                </Navbar.Collapse>
            </div>
        </Navbar>
    );
};

export default Topnav;
