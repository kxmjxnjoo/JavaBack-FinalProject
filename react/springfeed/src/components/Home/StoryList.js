import React, { useEffect, useState } from "react";
import { Carousel } from "react-bootstrap";
import Error from "../common/Error";
import Loading from "../common/Loading";
import { Link } from "react-router-dom";

import {
    AiOutlineArrowRight as RightArrowIcon,
    AiOutlineArrowLeft as LeftArrowIcon,
} from "react-icons/ai";

// Resources
import defaultUserIcon from "../../images/tmpUserIcon.png";

const StoryList = ({ loginUser }) => {
    const [storyList, setStoryList] = useState(null);
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        setIsLoading(true);
        fetch("/api/story/list")
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                setStoryList(data);
            })
            .finally(() => {
                setIsLoading(false);
            });
    }, []);

    return (
        <div
            className="mb-3 border"
            style={{ height: "130px", overflowY: "hidden" }}
        >
            {isLoading ? (
                <Loading message="스토리를 불러오고 있어요" />
            ) : (
                <>
                    {storyList != null && storyList.length != 0 ? (
                        <div className="row ms-md-5 ms-0 mt-3">
                            {storyList
                                .filter(
                                    (data) => data.userid != loginUser.userid
                                )
                                .map((user, index) => {
                                    return index < 5 ? (
                                        <div className="col-3 col-md-2">
                                            <Link
                                                to={"/story/" + user.userid}
                                                className="text-decoration-none text-black"
                                            >
                                                <img
                                                    src={
                                                        user.img == null
                                                            ? defaultUserIcon
                                                            : "/images/" +
                                                              user.img
                                                    }
                                                    alt="PROFILE"
                                                    className="d-block rounded-circle"
                                                    style={{
                                                        width: "75px",
                                                        height: "75px",
                                                    }}
                                                />
                                                <div className="h5">
                                                    {user.userid}
                                                </div>
                                            </Link>
                                        </div>
                                    ) : (
                                        <></>
                                    );
                                })}
                        </div>
                    ) : (
                        <Error errorMessage="스토리를 불러올 수 없었어요" />
                    )}
                </>
            )}
        </div>
    );
};

/*
<div className="col-1 align-self-center me-3">
    <RightArrowIcon className='h1'/>
</div>

<div className="col-1 align-self-center ms-3">
    <LeftArrowIcon className='h1'/>
</div>

*/

export default StoryList;
