import React from "react";

import { BiErrorCircle as ErrorIcon } from "react-icons/bi";

const NoContent = ({ message }) => {
    return (
        <div>
            <div className="row">
                <ErrorIcon
                    style={{ fontSize: "200px" }}
                    className="text-danger"
                />
            </div>
            <div className="row">
                <div className="h1 text-center mt-3">
                    {message == null ? "내용이 없어요" : message}
                </div>
            </div>
        </div>
    );
};

export default NoContent;
