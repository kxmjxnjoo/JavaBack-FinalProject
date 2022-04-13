import React, { useContext, useState, useRef } from 'react'
import { Link } from 'react-router-dom'

// Bootstrap
import { Overlay, Modal, Navbar, Dropdown } from 'react-bootstrap'

// Icons
import {
	BsFillHouseDoorFill as HomeIcon, BsMessenger as MessageIcon,
	BsFillBellFill as NotiIcon, BsFillPlusSquareFill as AddIcon,
	BsSearch as SearchIcon
} from 'react-icons/bs'
import { MdExplore as ExploreIcon } from 'react-icons/md'

// Resources
import logo from '../../images/logo.png'
import defaultProfile from '../../images/tmpUserIcon.png'
import './topnav.css'

const Topnav = ({ page, isLoggedIn, user, searchKey, setSearchKey }) => {
	const logoStyle = {
		width: '30px',
		filter: 'opacity(0.5) drop-shadow(0 0 0 blue)'
	}
	const topnavStyle = {
		backgroundColor: 'var(--mainColor)',
		color: 'white',
		zIndex: '2000'
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
		<Navbar style={topnavStyle} expand='lg' className='p-lg-0 position-fixed top-0 left-0 w-100'>
			<div className="container">
				<Link to="/" className='text-decoration-none navbar-brand'>
					<Navbar.Brand className='text-light'>
						<img src={logo} alt="" className="d-inline-block align-top me-1" style={logoStyle} />
						Springfeed
					</Navbar.Brand>
				</Link>

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
									<Link to="/">
										<a className={(page === 0 ? "nav-link active" : "nav-link")}>
											<HomeIcon style={{color: 'white'}}/>
										</a>
									</Link>

								</div>
								<div className="col-2 p-lg-0 align-self-center">
									<Link to="/message">
										<a className={(page === 1 ? "nav-link active" : "nav-link")}>
											<MessageIcon style={{color: 'white'}}/>
										</a>
									</Link>

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
									<Link to='/explore'>
										<a className={(page === 3 ? "nav-link active" : "nav-link")} href="/explore">
											<ExploreIcon style={{color: 'white'}}/>
										</a>
									</Link>
								</div>
								<div className="col-2 p-lg-0 align-self-center">
									<Link to="/noti">
										<a className={(page === 4 ? "nav-link active" : "nav-link")} href="/noti">
											<NotiIcon style={{color: 'white'}}/>
										</a>
									</Link>

								</div>
								<div className="col-2 p-lg-0 align-self-center">
									<Dropdown>
										<Dropdown.Toggle variant="none" id="dropdown-basic" className='p-0'>
											<a className="nav-link">
												<img src={
													isLoggedIn ?
														user.img :
														defaultProfile
												} alt="ERR" className="round-circle" style={profileStyle} />
											</a>
										</Dropdown.Toggle>

										<Dropdown.Menu>
											{
												isLoggedIn ? 
												<>
													<Link to={'/user/page/' + (user != null ? user.userid : '')} className='text-decoration-none'>
														<Dropdown.Item href='/user/page'>내 유저 페이지로 이동</Dropdown.Item>
													</Link>
													<Link to='/user/edit' className='text-decoration-none'>
														<Dropdown.Item href='/user/edit'>내 정보 수정</Dropdown.Item>
													</Link>
													<Link to='/logout' className='text-decoration-none'>
														<Dropdown.Item href='/logout' className='bg-danger text-white'>로그아웃</Dropdown.Item>
													</Link>												
												</>
												:
												<>
													<Link to={'/login'} className='text-decoration-none'>
														<Dropdown.Item>로그인</Dropdown.Item>
													</Link>												
												</>
											}
										</Dropdown.Menu>
									</Dropdown>
										
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