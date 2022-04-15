import React, { useEffect } from 'react'

// Components
import MessageNotSelected from './Message/MessageNotSelected'

// Bootstrap
import { Modal } from 'react-bootstrap'
import FollowingList from './Home/MainFollowingList'
import { useState } from 'react'
import MessageDetail from './Message/MessageDetail'
import MessageList from './Message/MessageList'

const Message = ({ setPage, user, setIsSelectOpen }) => {
	useEffect(() => {
		setPage(1)
		setIsSelectOpen(false)
	}, [])

	const [isShowingFollowingList, setIsShowingFollowingList] = useState(false)
	const hideFollowingList = () => {
		setIsShowingFollowingList(false)
	}

	const [messageUser, setMessageUser] = useState(null)

	return (
		<div className='container-fluid border mb-5'>
			<div className="row vh-60 overflow-hidden">
				<div className="col-4 border">
					<MessageList
						setMessageUser={setMessageUser}
					/>
				</div>

				<div className="col-8">
					{
						messageUser == null ?
							<MessageNotSelected
								setIsShowingFollowingList={setIsShowingFollowingList}
							/> :
							<MessageDetail />
					}
				</div>
			</div>

			<Modal show={isShowingFollowingList} onHide={hideFollowingList}>
				<Modal.Body>
					<FollowingList
						user={user}
					/>
					<div className="btn btn-danger w-100 mt-2" onClick={hideFollowingList}>닫기</div>
				</Modal.Body>
			</Modal>
		</div>
	)
}

export default Message