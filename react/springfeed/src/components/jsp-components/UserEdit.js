import React, { useEffect, useState } from "react";

import Loading from "../common/Loading";

const UserEdit = () => {
    const [user, setUser] = useState(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        fetch("/user/edit/form")
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                setUser(data);
            })
            .finally(() => {
                setIsLoading(false);
            });
    }, []);

    return (
        <>
            {isLoading ? (
                <Loading />
            ) : (
                <div className="container ms-md-5 me-md-5">
                    <div className="row">
                        <div className="col-4">
                            <img
                                src={"/images/" + user.img}
                                alt=""
                                className="rounded-circle"
                                style={{ width: "150px", height: "150px" }}
                            />
                        </div>

                        <div className="col-8 align-self-center">
                            <div className="h1">{user.userid}</div>
                            <div className="h3 text-muted">
                                아이디는 바꿀 수 없어요
                            </div>
                        </div>
                    </div>

                    <form className="mt-5">
                        <div className="form-group p-3">
                            <label htmlFor="">비밀번호</label>
                            <input
                                type="password"
                                className="form-control"
                                placeholder={"*".repeat(user.password.length)}
                            ></input>
                        </div>
                        <div className="form-group p-3">
                            <label htmlFor="">이름</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder={user.name}
                            ></input>
                        </div>
                        <div className="form-group p-3">
                            <label htmlFor="">이메일</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder={user.email}
                            ></input>
                        </div>
                        <div className="form-group p-3">
                            <label htmlFor="">전화번호</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder={user.phone}
                            ></input>
                        </div>
                        <div className="form-group p-3">
                            <label htmlFor="">자기소개</label>
                            <textarea
                                type="text"
                                className="form-control"
                                value={user.introduce}
                            ></textarea>
                        </div>
                        <div className="form-group p-3">
                            <div className="row">
                                <div className="col-4">
                                    <input
                                        type="submit"
                                        value="수정하기"
                                        className="w-100 btn btn-success"
                                        style={{ height: "50px" }}
                                    />
                                </div>
                                <div className="col-4">
                                    <input
                                        type="reset"
                                        value="다시 입력"
                                        className="w-100 btn btn-warning"
                                        style={{ height: "50px" }}
                                    />
                                </div>
                                <div className="col-4">
                                    <input
                                        type="button"
                                        value="취소"
                                        className="w-100 btn btn-danger"
                                        style={{ height: "50px" }}
                                    />
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            )}
        </>
    );
};

export default UserEdit;

// userid

// pw
// name
// email
// phone
// introduce
// img
