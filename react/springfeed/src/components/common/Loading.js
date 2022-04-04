import React from 'react'

import logo from '../../images/logo.png'

const Loading = () => {
	const spinnerStyle = {
		color: 'var(--mainColor)',
		width: '5rem',
		height: '5rem'
	}

	return (
		<div className='container text-center mt-5 align-middle'>

			<div class="spinner-border mb-5" role="status" style={spinnerStyle}>
				<span class="sr-only"></span>
			</div>

			<div className="h1 mt-3">페이지를 불러오고 있어요</div>

		</div>
	)
}

export default Loading