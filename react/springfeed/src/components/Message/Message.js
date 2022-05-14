import React, { useEffect } from "react";

// Components
import MessageNotSelected from "./MessageNotSelected";

// Bootstrap
import { Modal } from "react-bootstrap";
import MainFollowingList from "../home/MainFollowingList";
import { useState } from "react";
import MessageDetail from "./MessageDetail";
import MessageList from "./MessageList";

import { BiErrorCircle as ErrorIcon } from "react-icons/bi";

const Message = ({ setPage, user }) => {
    useEffect(() => {
        setPage(1);
    }, []);

    const [isShowingFollowingList, setIsShowingFollowingList] = useState(false);
    const hideFollowingList = () => {
        setIsShowingFollowingList(false);
    };

    const [messageUser, setMessageUser] = useState(null);

    return (
        <>
            <div className="row">
                <ErrorIcon
                    className="text-danger mb-5"
                    style={{ fontSize: "200px" }}
                />
            </div>
            <div className="row">
                <div className="col-12 text-center">
                    <div className="h1">메세지 기능은 아직 준비중이에요</div>
                </div>
            </div>
        </>
        // <div className='container-fluid border mb-5'>
        // 	<div className="row vh-60 overflow-hidden">
        // 		<div className="col-4 border">
        // 			<MessageList
        // 				setMessageUser={setMessageUser}
        // 			/>
        // 		</div>

        // 		<div className="col-8">
        // 			{
        // 				messageUser == null ?
        // 					<MessageNotSelected
        // 						setIsShowingFollowingList={setIsShowingFollowingList}
        // 					/> :
        // 					<MessageDetail />
        // 			}
        // 		</div>
        // 	</div>

        // 	<Modal show={isShowingFollowingList} onHide={hideFollowingList}>
        // 		<Modal.Body>
        // 			<FollowingList
        // 				user={user}
        // 			/>
        // 			<div className="btn btn-danger w-100 mt-2" onClick={hideFollowingList}>닫기</div>
        // 		</Modal.Body>
        // 	</Modal>
        // </div>
    );
};

export default Message;
