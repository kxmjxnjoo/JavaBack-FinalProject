import React, { useEffect, useState } from "react";
import Loading from "../common/Loading";
import Error from "../common/Error";
import { useParams, Link } from "react-router-dom";

import defaultUserIcon from "../../images/tmpUserIcon.png";

import { BiDotsHorizontal as DetailIcon } from "react-icons/bi";
import {
    AiOutlineArrowRight as NextIcon,
    AiOutlineArrowLeft as PrevIcon,
} from "react-icons/ai";
import toast from "react-hot-toast";
import { Modal } from "react-bootstrap";

const Story = () => {
    const { id } = useParams();

    const [val, setVal] = useState(id);

    const [story, setStory] = useState(null);

    const [isLoading, setIsLoading] = useState(false);

    const [isModalOpen, setIsModalOpen] = useState(false);

    useEffect(() => {
        setIsLoading(true);

        // check if id is num or string
        fetch("/story?" + (isNaN(val) ? "userid=" + val : "story_num=" + val))
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                setStory(data);
            })
            .catch((err) => {
                return err;
            })
            .finally(() => {
                setIsLoading(false);
            });
    }, [val]);

    return (
        <div>
            {isLoading ? (
                <Loading message="스토리를 가져오고 있어요" />
            ) : story == null ? (
                <Error />
            ) : typeof id != "number" ? (
                <div className="container">
                    <div className="row mb-5">
                        <div className="col-2">
                            <img
                                src={
                                    story.StoryDto.USER_IMG == null
                                        ? defaultUserIcon
                                        : "/images/" + story.StoryDto.USER_IMG
                                }
                                alt=""
                                className="rounded-circle img-fluid"
                                style={{ width: "100px" }}
                            />
                        </div>

                        <div className="col-8 align-self-center">
                            <div className="h2">{story.StoryDto.USERID}</div>
                        </div>

                        <div className="col-2 aling-self-center">
                            <div style={{ fontSize: "50px" }}>
                                <DetailIcon
                                    onClick={() => {
                                        setIsModalOpen(true);
                                    }}
                                />
                            </div>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1 align-self-center">
                            <PrevIcon
                                className="h1"
                                onClick={() => {
                                    if (story.prev != null && story.prev != 0) {
                                        setVal(story.prev);
                                    } else {
                                        toast.error("그 전의 스토리가 없어요");
                                    }
                                }}
                            />
                        </div>
                        <div className="col-10">
                            <img
                                src={"/images/" + story.StoryDto.STORY_IMG}
                                alt=""
                                className="img-fluid"
                            />
                        </div>
                        <div className="col-1 align-self-center">
                            <NextIcon
                                className="h1"
                                onClick={() => {
                                    if (story.next != null && story.next != 0) {
                                        setVal(story.next);
                                    } else {
                                        toast.error("더 이상 스토리가 없어요");
                                    }
                                }}
                            />
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-12">
                            <div
                                className="h1 text-center w-100"
                                style={{
                                    color: story.fontcolor,
                                    background: "rgba(0, 0, 0, 0.3)",
                                }}
                            >
                                {story.StoryDto.STORY_CONTENT}
                            </div>
                        </div>
                    </div>
                </div>
            ) : (
                <div className="container">
                    <div className="row">
                        <div className="col-2">
                            <img
                                src={
                                    story.StoryDto.USER_IMG == null
                                        ? defaultUserIcon
                                        : "/images/" + story.StoryDto.USER_IMG
                                }
                                alt=""
                                className="rounded-circle img-fluid"
                            />
                        </div>

                        <div className="col-8 align-self-center">
                            <div className="h2">{story.StoryDto.USERID}</div>
                        </div>

                        <div className="col-2 aling-self-center">
                            <div
                                style={{ fontSize: "50px" }}
                                className="hoverEffect"
                            >
                                <DetailIcon />
                            </div>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1 align-self-center">
                            <Link
                                to={"/story/" + story.prev}
                                className="text-dark"
                            >
                                <PrevIcon className="h1" />
                            </Link>
                        </div>
                        <div className="col-10">
                            <img
                                src={"/images/" + story.StoryDto.STORY_IMG}
                                alt=""
                                className="img-fluid"
                            />
                        </div>
                        <div className="col-1 align-self-center">
                            <Link
                                to={"/story/" + story.next}
                                className="text-dark"
                            >
                                <NextIcon className="h1" />
                            </Link>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-12">
                            <div
                                className="h1 text-center w-100"
                                style={{
                                    color: story.fontcolor,
                                    background: "rgba(0, 0, 0, 0.3)",
                                }}
                            >
                                {story.StoryDto.STORY_CONTENT}
                            </div>
                        </div>
                    </div>
                </div>
            )}

            <Modal
                show={isModalOpen}
                onHide={() => {
                    setIsModalOpen(false);
                }}
                className="mt-5"
            >
                <Modal.Body>
                    <div className="card">
                        <div className="row">
                            <div className="btn text-danger w-100 p-3">
                                신고하기
                            </div>
                        </div>
                        <div className="row">
                            <Link
                                to={"/user/page/" + id}
                                className="btn text-decoration-none text-dark w-100 p-3"
                            >
                                유저 페이지로 이동
                            </Link>
                        </div>
                    </div>
                </Modal.Body>
            </Modal>
        </div>
    );
};

export default Story;
