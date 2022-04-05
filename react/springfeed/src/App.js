import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import React, { useState, useEffect, useHistory } from 'react'
import axios from 'axios'

// Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'

// Components
import Home from './components/Home'
import Topnav from './components/common/Topnav'
import Loading from './components/common/Loading'
import Search from './components/Search'
import Message from './components/Message'
import Error from './components/common/Error'

// Common
import './common.css'

function App() {
	const [isLoggedIn, setIsLoggedIn] = useState(false)
	const [user, setUser] = useState(null)
	const [isLoading, setIsLoading] = useState(false)
	const [isError, setIsError] = useState(false)
	const [errorMessage, setErrorMessage] = useState(null)
	const [page, setPage] = useState(0)
	const [searchKey, setSearchKey] = useState('')
	const [searchResult, setSearchResult] = useState('')

	useEffect(() => {
		axios.post('/api/search', {
			key: searchKey
		})
			.then((res) => {
				return res.json()
			})
			.then((data) => {
				setSearchResult(data)
			})
	}, [searchKey])


	/*
	useEffect(() => {
		fetch("http://localhost:8070/api/user/login")
			.then((res) => {
				return res.json()
			})
			.then((result) => {
				setUser(result)
				if(result != null) {
					setIsLoggedIn(true)
				} else {
					setIsLoggedIn(false)
				}
			})
	}, [])
	*/

	return (
		<div className="App">
			<Topnav
				page={page}
				isLoggedIn={isLoggedIn}
				user={user}
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
								data={searchResult}
							/>
						</div>
					</div>
					:
					<></>
			}


			<div className='container'>
				<Router>
					{
						isLoading ? <Loading /> :
							isError ? <Error errorMessage={errorMessage} /> :
								<Routes>
									<Route path="/" element={
										<Home
											user={user}
										/>} />
									<Route path="/search" element={<Search />} />
									<Route path="/message" element={
										<Message
											setPage={setPage}
										/>} />
								</Routes>
					}
				</Router>
			</div>
		</div>
	);
}

export default App;