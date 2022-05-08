import React, { useEffect, useState } from "react";
import toast from "react-hot-toast";

const Login = ({ isLoggedIn, setIsLoggedIn }) => {
    const [jspElement, setJspElement] = useState(null);

    const [id, setId] = useState(null);
    const [pw, setPw] = useState(null);

    const createMarkup = (data) => {
        return { __html: data };
    };

    useEffect(() => {
        if (!isLoggedIn) {
            toast("안녕하세요! Spring Feed를 이용하기 위해 로그인 해 주세요", {
                icon: "👋",
            });
        }
    }, []);

    return (
        <div className="row">
            <div className="col-md-3"></div>
            <div className="col-md-6 border">
                <div className="container">
                    <div className="row justify-content-center mb-5 mt-5">
                        <div className="h1 text-center mb-5">Springfeed</div>
                        <div className="h2 text-center">로그인</div>
                    </div>

                    <div className="row">
                        <input
                            type="text"
                            placeholder="아이디"
                            className="p-4 m-4 border"
                            value={id}
                            onChange={(e) => setId(e.target.value)}
                        />
                    </div>
                    <div className="row">
                        <input
                            type="password"
                            name="pw"
                            placeholder="비밀번호"
                            className="p-4 m-4 border"
                            value={pw}
                            onChange={(e) => setPw(e.target.value)}
                        />
                    </div>

                    <div className="row">
                        <input
                            type="button"
                            value="로그인"
                            className="p-4 m-4 btn"
                            style={{ border: "1px solid var(--mainColor)" }}
                            onClick={() => {
                                fetch("/login", {
                                    method: "POST",
                                    headers: {
                                        "Content-Type":
                                            "application/json;charset=UTF-8",
                                    },
                                    contentType: false,
                                    processData: false,
                                    body: JSON.stringify({
                                        userid: id,
                                        password: pw,
                                    }),
                                })
                                    .then((res) => {
                                        return res.text();
                                    })
                                    .then((data) => {
                                        if (data == "loginComplete") {
                                            toast.success("반가워요!");
                                            setIsLoggedIn(true);
                                            this.props.history.push("/path");
                                        } else {
                                            toast.error(data);
                                        }
                                    });
                            }}
                        />
                    </div>

                    <div className="row mt-5 mb-5">
                        <div className="col-5 align-self-center">
                            <div className="h3 text-center">---------</div>
                        </div>
                        <div className="col-2 align-self-center">
                            <div className="h3 text-center">or</div>
                        </div>
                        <div className="col-5 align-self-center">
                            <div className="h3 text-center">---------</div>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-8">아직 계정이 없으신가요?</div>
                        <div className="col-4 text-primary">
                            <a href="/admin" className="text-decoration-none">
                                가입하기
                            </a>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-8">
                            아이디나 비밀번호를 잊으셨나요?
                        </div>
                        <div className="col-4 text-primary">
                            <a href="/admin" className="text-decoration-none">
                                아이디/비밀번호 찾기
                            </a>
                        </div>
                    </div>
                    <div className="row mb-5">
                        <div className="col-8">관리자신가요?</div>
                        <div className="col-4 text-primary">
                            <a href="/admin" className="text-decoration-none">
                                관리자 로그인
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div className="col-md-3"></div>
        </div>
    );
};

export default Login;
