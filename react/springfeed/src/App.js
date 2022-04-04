import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import React, { useState, useEffect, useHistory } from 'react'

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
			/>

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