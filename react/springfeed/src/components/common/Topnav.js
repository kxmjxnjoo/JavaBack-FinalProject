import React, { useContext, useState, useRef } from 'react'
import { Link } from 'react-router-dom'

// Bootstrap
import { Overlay, Modal, Navbar } from 'react-bootstrap'

// Icons
import {
	BsFillHouseDoorFill as HomeIcon, BsMessenger as MessageIcon,
	BsFillHeartFill as NotiIcon, BsFillPlusSquareFill as AddIcon,
	BsSearch as SearchIcon
} from 'react-icons/bs'
import { MdExplore as ExploreIcon } from 'react-icons/md'

// Resources
import logo from '../../images/logo.png'
import defaultProfile from '../../images/tmpUserIcon.png'

const Topnav = ({ page, isLoggedIn, user, searchKey, setSearchKey }) => {
	const logoStyle = {
		width: '30px',
		filter: 'opacity(0.5) drop-shadow(0 0 0 blue)'
	}
	const topnavStyle = {
		backgroundColor: 'var(--mainColor)',
		color: 'white'
	}
	const profileStyle = {
		width: '40px'
	}
	const handleSearch = (e) => {
		setSearchKey(e.target.value)
	}

	const [isShowSelect, setIsShowSelect] = useState(false)

	const showSelect = () => {
		setIsShowSelect(true)
	}

	const hideSelect = () => {
		setIsShowSelect(false)
	}

	const ref = useRef(null)

	return (
		<Navbar style={topnavStyle} expand='lg' className='p-lg-0'>
			<div className="container">
				<Navbar.Brand>
				<a href="/" className="navbar-brand ms-3 text-light">
					<img src={logo} alt="" className="d-inline-block align-top me-1" style={logoStyle} />
					Springfeed
				</a>
				</Navbar.Brand>

				<Navbar.Toggle aria-controls="topnav-toggle"></Navbar.Toggle>

				<Navbar.Collapse id="navbar-toggle">
					<form className="form-inline my-2 my-lg-0 col-12 col-lg-5">
						<input type="text" className="form-control mr-sm-2 col-lg-5" 
						placeholder="🔎 검색" aria-label="Sesarch" name="key" 
						onChange={handleSearch} />
					</form>

					<div className="col-12 col-lg-6">
						<div className="nav navbar-nav mr-auto mt-2 mt-lg-0 h3 float-end">
							<div className="row justify-content-center">
								<div className="col-2 p-lg-0 align-self-center">
									<a className={(page === 0 ? "nav-link active" : "nav-link")} href="/">
										<HomeIcon style={{color: 'white'}}/>
									</a>

								</div>
								<div className="col-2 p-lg-0 align-self-center">
									<a className={(page === 1 ? "nav-link active" : "nav-link")} href="/message">
										<MessageIcon style={{color: 'white'}}/>
									</a>

								</div>
								<div className="col-2 p-lg-0 align-self-center">
									<button className={(page === 2 ? "nav-link active" : "nav-link")} style={{ border: 'none', backgroundColor: 'transparent' }} onClick={showSelect}>
										<AddIcon style={{color: 'white'}}/>
									</button>

									<Overlay
										show={isShowSelect}
										placement="bottom"
									>
									</Overlay>

								</div>
								<div className="col-2 p-lg-0 align-self-center">
									<a className={(page === 3 ? "nav-link active" : "nav-link")} href="/explore">
										<ExploreIcon style={{color: 'white'}}/>
									</a>

								</div>
								<div className="col-2 p-lg-0 align-self-center">
									<a className={(page === 4 ? "nav-link active" : "nav-link")} href="/noti">
										<NotiIcon style={{color: 'white'}}/>
									</a>

								</div>
								<div className="col-2 p-lg-0 align-self-center">
									<a href={"/user/" + (user != null ? user.userid : '')} className="nav-link">
										<img src={
											isLoggedIn ?
												user.img :
												defaultProfile
										} alt="ERR" className="round-circle" style={profileStyle} />
									</a>

								</div>

							</div>

						</div>
					</div>

				</Navbar.Collapse>
			</div>
		</Navbar>

	)
}

export default Topnav

/*
		<nav className='navbar navbar-nav fixed-top navbar-expand-lg navbar-dark' >
			<div className='container-fluid'>

				<button className="navbar-toggler me-4" data-toggle="collapse"
					data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02"
					aria-expanded="false" aria-label="Toggle navigation">
					<span className="navbar-toggler-icon"></span>
				</button>

				<div className="collapse navbar-collapse row" id="navbarTogglerDemo02">



				</div>

			</div>

		</nav >




*/