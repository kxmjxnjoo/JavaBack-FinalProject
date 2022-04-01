import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import { useState, useEffect } from 'react'

// Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'

// Components
import Home from './route/Home'
import Topnav from './components/Topnav'
import Loading from './components/Loading'
import Search from './route/Search'
import Message from './route/Message'

// Common
import './common.css'

function App() {
	const [isLoggedIn, setIsLoggedIn] = useState(false)
	const [user, setUser] = useState(null)

	useEffect(() => {
		fetch("http://localhost:8070/api/user")
			.then((res) => {
				return res.json()
			})
			.then((result) => {
				setUser(result)
				if(result != null) {
					setIsLoggedIn(true)
				}
			})
	})

	return (
		<div className="App">
			<Topnav
				isLoggedIn={isLoggedIn}
				user={user}
			/>
			<div className='container'>
				<Router>

					<Routes>
						<Route path="/" element={<Home user={user}/>}/>
						<Route path="/search" element={<Search/>}/>			
						<Route path="/message" element={<Message/>}/>			
					</Routes>

				</Router>
			</div>

		</div>
	);
}

export default App;