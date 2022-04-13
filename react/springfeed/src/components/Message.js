import React, { useEffect } from 'react'

// Components
import MessageNotSelected from './Message/MessageNotSelected'

// Bootstrap
import { Modal } from 'react-bootstrap'
import FollowingList from './Home/MainFollowingList'
import { useState } from 'react'
import MessageDetail from './Message/MessageDetail'

const Message = ({ setPage, user }) => {
	useEffect(() => {
		setPage(1)
	}, [])

	const [isShowingFollowingList, setIsShowingFollowingList] = useState(false)
	const hideFollowingList = () => {
		setIsShowingFollowingList(false)
	}

	const [messageUser, setMessageUser] = useState(true)

	return (
		<div className='container-fluid mt-5 border'>
			<div className="row">
				<div className="col-4">
					USERLIST
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