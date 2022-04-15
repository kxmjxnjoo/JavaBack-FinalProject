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
import Noti from './components/jsp-components/Noti'
import Login from './components/jsp-components/Login'
import Logout from './components/jsp-components/Logout'
import Join from './components/jsp-components/Join'
import UserEdit from './components/jsp-components/UserEdit'
import UploadStory from './components/UploadStory'
import UploadPost from './components/UploadPost'

// Common
import './common.css'
import UserPage from './components/common/UserPage'
import Footer from './components/common/Footer'
import Qna from './components/jsp-components/Qna'
import Faq from './components/jsp-components/Faq'
import Story from './components/jsp-components/Story'
import Select from './components/Select'

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

	const [isStoryOpen, setIsStoryOpen] = useState(false)
	const [storyNum, setStoryNum] = useState(null)

	const [isSelectOpen, setIsSelectOpen] = useState(false) 

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
		<div className="App d-flex flex-column min-vh-100">
		<Router>

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
				setIsSelectOpen={setIsSelectOpen}
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
								setIsPostDetailOpen={setIsPostDetailOpen}
								setSelectedPost={setSelectedPost}
							/>
						</div>
					</div>
					:
					<></>
			}


			<div className='container min-vh-100'>
					{
						isLoading ? <Loading message='로딩중이에요'/> :
							isError ? <Error errorMessage={errorMessage} /> :
									<Routes>
										<Route path="/" element={
												!isLoggedIn ? <Login/>
												:
												<Home
												user={user}
												setPage={setPage}
												toast={toast}
												selectedPost={selectedPost}
												setSelectedPost={setSelectedPost}
												setIsPostDetailOpen={setIsPostDetailOpen}
												setIsStoryOpen={setIsStoryOpen}
												setStoryNum={setStoryNum}
												setIsSelectOpen={setIsSelectOpen}
												/>}
											 />
										<Route path="/search" element={<Search />} />
										<Route path="/message" element={
											!isLoggedIn ? <Login/>
											:
											<Message
												setPage={setPage}
												user={user}
												setIsSelectOpen={setIsSelectOpen}
											/>} />
										<Route path="/explore" element={										
											<Explore
												setPage={setPage}
												setIsPostDetailOpen={setIsPostDetailOpen}
												setSelectedPost={setSelectedPost}
												setIsSelectOpen={setIsSelectOpen}
										/>} />

										<Route path='/noti' element={
											!isLoggedIn ? <Login/>
											:
											<Noti setIsSelectOpen={setIsSelectOpen}/>}/>

										<Route path="/user/page/:id" element={<UserPage setSearchKey={setSearchKey} setIsSelectOpen={setIsSelectOpen}/>}/>
										<Route path='/logout' element={<Logout/>}/>
										<Route path='/join' element={<Join/>}/>

										<Route path='/user/edit' element={
											!isLoggedIn ? <Login/>
											:
											<UserEdit/>}/>
										<Route path='/faq' element={<Faq/>}/>
										<Route path='/qna' element={<Qna/>}/>

										<Route path='/upload/story' element={<UploadStory setIsSelectOpen={setIsSelectOpen}/>}/>
										<Route path='/upload/post' element={<UploadPost setIsSelectOpen={setIsSelectOpen}/>}/>
									</Routes>
					}
			</div>

			<Footer/>

			<Modal
				show={isPostDetailOpen}
				onHide={closePostDetail}
				dialogClassName='postDetailModal'
				className='mt-5'
				>
				<PostDetail
					post={selectedPost}
					/>
			</Modal>

			<Modal
				show={isStoryOpen}
				onHide={() => {
					setIsStoryOpen(false)
				}}
				dialogClassName='storyModal'
				className='mt-5 overflow-hidden'
			>
				<Story
					storyNum={storyNum}
				/>
			</Modal>

			<Modal
				show={isSelectOpen}
				onHide={() => {
					setIsSelectOpen(false)
				}}
				dialogClassName='selectModal'
				className='mt-5'
			>
				<Select/>
			</Modal>
			
		</Router>
		</div>
	);
}

export default App;