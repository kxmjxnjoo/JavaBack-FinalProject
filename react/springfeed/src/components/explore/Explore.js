import React, { useEffect, useState } from "react";
import toast from "react-hot-toast";
import Loading from "../common/Loading";
import Error from "../common/Error";

import "../../resources/explore.css";

import PostThumbnail from "../post/PostThumbnail";

import server from "../common/server";

const Explore = ({ setPage, setIsPostDetailOpen, setSelectedPost }) => {
    const [isLoading, setIsLoading] = useState(true);
    const [isError, setIsError] = useState(false);
    const [errorMessage, setErrorMessage] = useState(null);
    const [exploreData, setExploreData] = useState([]);

    useEffect(() => {
        setPage(3);

        fetch(server + "/api/explore/feed")
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                if (data == null || data == "" || typeof data == "undefined") {
                    setIsError(true);
                } else {
                    setExploreData(data);
                }
            })
            .catch((err) => {
                toast.error("에러가 났어요 : " + err);
                setErrorMessage(err);
                setIsError(true);
            })
            .finally(() => {
                setIsLoading(false);
            });
    }, []);

    const openPostDetail = (postNum) => {
        setIsPostDetailOpen(true);
        setSelectedPost(postNum);
    };

    return (
        <div className="mb-5">
            {isLoading ? (
                <Loading
                    message="인기 포스트를 불러오고 있어요"
                    className="mt-5"
                />
            ) : (
                <>
                    {isError ? (
                        <Error
                            message="로딩하는데 오류가 났거나 인기 포스트가 없어요"
                            errorMessage={errorMessage}
                        />
                    ) : (
                        <div className="row">
                            {exploreData == null || exploreData.length == 0 ? (
                                <Error
                                    message="로딩하는데 오류가 났거나 인기 포스트가 없어요"
                                    errorMessage=""
                                />
                            ) : (
                                <div className="row">
                                    {exploreData.map((data) => {
                                        return (
                                            <>
                                                <div className="col-12 col-md-4 p-0">
                                                    <PostThumbnail
                                                        postNum={data.postNum}
                                                        postImg={data.post_img}
                                                        likeCount={
                                                            data.likeCount
                                                        }
                                                        replyCount={
                                                            data.replyCount
                                                        }
                                                        openPostDetail={
                                                            openPostDetail
                                                        }
                                                        className=""
                                                    />
                                                </div>
                                            </>
                                        );
                                    })}
                                </div>
                            )}
                        </div>
                    )}
                </>
            )}
        </div>
    );
};

export default Explore;
