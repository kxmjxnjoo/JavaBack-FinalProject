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

const Story = () => {
    const { id } = useParams();

    const [story, setStory] = useState(null);

    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        setIsLoading(true);

        // check if id is num or string
        fetch(
            "/story?" +
                (typeof id == "string" ? "userid=" + id : "story_num=" + id)
        )
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
    }, []);

    return (
        <div>
            {isLoading ? (
                <Loading message="스토리를 가져오고 있어요" />
            ) : story == null ? (
                <Error />
            ) : typeof id != "number" ? (
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
                            <div style={{ fontSize: "50px" }}>
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
                                to={"/story/" + story.StoryDto.next}
                                className={"text-dark"}
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
                            <div style={{ fontSize: "50px" }}>
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
        </div>
    );
};

export default Story;
