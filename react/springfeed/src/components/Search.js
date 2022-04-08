import React, { useEffect, useState } from 'react'

import defaultProfile from '../images/tmpUserIcon.png'

// Components
import SearchBox from './common/SearchBox'
import NoSearchResult from './common/NoSearchResult'
import { BiEraser } from 'react-icons/bi'

import toast from 'react-hot-toast'

const Search = ({ searchKey }) => {
	const [userResult, setUserResult] = useState(null)
	const [postResult, setPostResult] = useState(null)

	useEffect(() => {
		fetch("/api/search/member?key=" + searchKey)
			.then((res) => {
				return res.json()
			})
			.then((data) => {
				setUserResult(data)
			})
			.catch((err) => {
				//toast.error('검색하는 도중에 에러가 났어요 : ' + err)
			})

		fetch('/api/search/post?key=' + searchKey)
			.then((res) => {
				return res.json()
			})
			.then((data) => {
				console.log(data)
				setPostResult(data)
			})
			.catch((err) => {
				//toast.error('검색하는 도중에 에러가 났어요 : ' + err)
			})

	}, [searchKey])

	return (
		<div className='mt-3'>
			<div className="row">

				<div className="col-12 col-md-6">
					<div className="h3">유저</div>
					{
						userResult == null ?
							<NoSearchResult />
							:
							userResult.map((user) => {
								return (
									<div className="row">
										<div className="col-2">
											<img src={
												user.img === '' || user.img == null ?
													defaultProfile :
													user.img
											} alt="" style={{ width: '50px' }} />
										</div>
										<div className="col-10">
											<div className="h5">{user.userid}</div>
											<div className="h5 text-muted">{user.name}</div>
										</div>
									</div>
								)
							})

					}
				</div>

				<div className="col-12 col-md-6">
					<div className="h3">포스트</div>
					{
						postResult == null || postResult === '' ?
							<NoSearchResult />
							:
							postResult.map((post) => {
								return (
									<div className="row">
										<div className="col-2">
											<img src={
												post.img === '' || post.img == null ?
													defaultProfile :
													post.img
											} alt="" style={{ width: '50px' }} />
										</div>
										<div className="col-10">
											<div className="h5">{post.content}</div>
											<div className="h5 text-muted">{
												post.create_date.slice(0, post.create_date.indexOf('T')).replace('-', '년 ').replace('-', '월 ').concat('일')
											}</div>
										</div>
									</div>
								)
							})

					}
				</div>

			</div>
		</div>
	)
}

export default Search
