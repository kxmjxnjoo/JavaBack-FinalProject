import React, { useContext } from 'react'

// Bootstrap
import 'bootstrap/dist/js/bootstrap.js'

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

const Topnav = ({ page, isLoggedIn, user }) => {
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

					<form action="/search" className="form-inline my-2 my-lg-0 col-5">
						<div className="row">
							<div className="col-10">
								<input type="text" className="form-control mr-sm-2 col-5" placeholder='검색' aria-label="Search" />
							</div>

							<div className="col-2">
								<button class="btn my-2 my-sm-0 col-2 ps-0" type="submit">
									<SearchIcon className='text-white text-weight-bold' />
								</button>
							</div>
						</div>

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
							<li className="nav-item">
								<a className={(page === 2 ? "nav-link active" : "nav-link")} href="/select">
									<AddIcon />
								</a>
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
								<a href="/user" className="nav-link">
									<img src={
										isLoggedIn ?
											user.img :
											defaultProfile
									} alt="" className="round-circle" style={profileStyle} />
								</a>
							</li>

						</ul>
					</div>


				</div>

			</div>

		</nav>
	)
}

export default Topnav