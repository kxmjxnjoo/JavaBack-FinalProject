import React, { useEffect } from "react";

const UploadPost = ({ setIsSelectOpen }) => {
    useEffect(() => {
        setIsSelectOpen(false);
    }, []);

    return (
        <div className="container border" style={{ height: "500px" }}>
            <div
                className="row"
                style={{ backgroundColor: "var(--mainColor)" }}
            >
                <div className="h2 text-center text-white p-2">새 포스트</div>
            </div>

            <form className="mt-5 ">
                <div className="form-group p-3">
                    <label htmlFor="">이미지</label>
                    <input
                        type="file"
                        name=""
                        id=""
                        accept="image/*"
                        className="form-control"
                    />
                </div>

                <div className="form-group p-3">
                    <label htmlFor="">위치</label>
                    <input
                        type="text"
                        name=""
                        id=""
                        className="form-control"
                        placeholder="이 곳에 위치를 입력해 주세요"
                    />
                </div>

                <div className="form-group p-3">
                    <label htmlFor="">내용</label>
                    <input
                        type="text"
                        name=""
                        id=""
                        className="form-control"
                        placeholder="포스트와 함께 올라갈 내용을 입력해 주세요"
                    />
                </div>

                <div className="form-group mt-5">
                    <div className="row">
                        <div className="col-6">
                            <input
                                type="button"
                                value="업로드"
                                className="btn btn-success w-100"
                            />
                        </div>
                        <div className="col-6">
                            <input
                                type="reset"
                                value="취소"
                                className="btn btn-danger w-100"
                            />
                        </div>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default UploadPost;
