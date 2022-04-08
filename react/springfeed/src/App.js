import { BrowserRouter as Router, Routes, Route, useParams } from 'react-router-dom'
import React, { useState, useEffect, useHistory } from 'react'
import axios from 'axios'
import toast, { Toaster } from 'react-hot-toast'

// Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'
import {Modal} from 'react-bootstrap'

// Components
import Home from './components/Home'
import Topnav from './components/common/Topnav'
import Loading from './components/common/Loading'
import Search from './components/Search'
import Message from './components/Message'
import Error from './components/common/Error'
import Explore from './components/Explore'
import NoLogin from './components/common/NoLogin'
import PostDetail from './components/Home/PostDetail'

// Common
import './common.css'
import UserPage from './components/common/UserPage'

function App() {
	const [isLoggedIn, setIsLoggedIn] = useState(false)
	const [user, setUser] = useState(null)
	const [isLoading, setIsLoading] = useState(false)
	const [isError, setIsError] = useState(false)
	const [errorMessage, setErrorMessage] = useState(null)
	const [page, setPage] = useState(0)
	const [searchKey, setSearchKey] = useState('')

	const [isPostDetailOpen, setIsPostDetailOpen] = useState(false)
	const [selectedPost, setSelectedPost] = useState(null)

	const closePostDetail = () => {
		setIsPostDetailOpen(false)
	}

	useEffect(() => {
		fetch("/api/user/login")
			.then((res) => {
				return res.json()
			})
			.then((result) => {
				setUser(result)

				if (result !== '' || result == null) {
					setIsLoggedIn(true)
					toast.success("안녕하세요 " + result.name + "님!")
				} else {
					setIsLoggedIn(false)
					toast.promise(
						{
							loading: 'loading...',
							success: <b>Success!</b>,
							error: <b>ERROR!</b>
						}
					)
				}
			})
			.catch((err) => {
				toast.error("에러가 났어요! " + err.message)
			})
	}, [])

	return (
		<div className="App">
			<div>
				<Toaster
					position='bottom-right'
					reverseOrder='false'
				/>
			</div>

			<Topnav
				page={page}
				isLoggedIn={isLoggedIn}
				user={user}
				searchKey={searchKey}
				setSearchKey={setSearchKey}
			/>

			{
				searchKey !== "" ?
					<div className="card w-50 ms-5 mt-5 overflow-auto shadow"
						style={{
							height: '300px',
							position: 'fixed',
							zIndex: '100',
							top: '50px',
							left: '50px'
						}}>
						<div className="card-header">
							<div className="card-title h5 text-center">검색 결과</div>
						</div>
						<div className="card-body pt-0">
							<Search
								searchKey={searchKey}
							/>
						</div>
					</div>
					:
					<></>
			}


			<div className='container'>
				<Router>
					{
						isLoading ? <Loading message='로딩중이에요'/> :
							isError ? <Error errorMessage={errorMessage} /> :

								!isLoggedIn ? <NoLogin/>
									:
									<Routes>
										<Route path="/" element={
											<Home
												user={user}
												setPage={setPage}
												toast={toast}
												selectedPost={selectedPost}
												setSelectedPost={setSelectedPost}
												setIsPostDetailOpen={setIsPostDetailOpen}
											/>} />
										<Route path="/search" element={<Search />} />
										<Route path="/message" element={
											<Message
												setPage={setPage}
												user={user}
											/>} />
										<Route path="/explore" element={<Explore
											setPage={setPage}
										/>} />

										<Route path="/user/:id" element={<UserPage/>}/>
									</Routes>
					}
				</Router>
			</div>

			<Modal
				show={isPostDetailOpen}
				onHide={closePostDetail}
				dialogClassName="w-90"
				>
				<PostDetail
					post={selectedPost}
				/>
			</Modal>
		</div>
	);
}

export default App;