import React, { useContext, useState, useRef } from 'react'
import { Link } from 'react-router-dom'

// Bootstrap
import { Overlay } from 'react-bootstrap'

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
		backgroundColor: 'var(--mainColor)'
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
		<nav className='navbar navbar-nav fixed-top navbar-expand-lg navbar-dark' style={topnavStyle}>
			<div className='container-fluid'>

				<a href="/" className="navbar-brand ms-3">
					<img src={logo} alt="" className="d-inline-block align-top me-1" style={logoStyle} />
					Springfeed
				</a>
				<button className="navbar-toggler me-4" data-toggle="collapse"
					data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02"
					aria-expanded="false" aria-label="Toggle navigation">
					<span className="navbar-toggler-icon"></span>
				</button>

				<div className="collapse navbar-collapse row" id="navbarTogglerDemo02">

					<form className="form-inline my-2 my-lg-0 col-5">
						<input type="text" className="form-control mr-sm-2 col-5" placeholder="🔎 검색" aria-label="Sesarch" name="key" onChange={handleSearch} />
					</form>

					<div className="col-6">
						<ul className="nav navbar-nav mr-auto mt-2 mt-lg-0 h3 float-end">

							<li className="nav-item">
								<a className={(page === 0 ? "nav-link active" : "nav-link")} href="/">
									<HomeIcon />
								</a>
							</li>
							<li className="nav-item">
								<a className={(page === 1 ? "nav-link active" : "nav-link")} href="/message">
									<MessageIcon />
								</a>
							</li>

							<li className="nav-item" ref={ref}>
								<button className={(page === 2 ? "nav-link active" : "nav-link")} style={{ border: 'none', backgroundColor: 'transparent' }} onClick={showSelect}>
									<AddIcon />
								</button>

								<Overlay
									show={isShowSelect}
									placement="bottom"
								>

								</Overlay>
							</li>


							<li className="nav-item">
								<a className={(page === 3 ? "nav-link active" : "nav-link")} href="/explore">
									<ExploreIcon />
								</a>
							</li>
							<li className="nav-item">
								<a className={(page === 4 ? "nav-link active" : "nav-link")} href="/noti">
									<NotiIcon />
								</a>
							</li>

							<li className="nav-item">
								<a href={"/user"} className="nav-link">
									<img src={
										isLoggedIn ?
											user.img :
											defaultProfile
									} alt="ERR" className="round-circle" style={profileStyle} />
								</a>
							</li>

						</ul>
					</div>


				</div>

			</div>

		</nav >
	)
}

export default Topnav