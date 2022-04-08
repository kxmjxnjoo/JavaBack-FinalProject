import React from 'react'

import logo from '../../images/logo.png'

const Loading = ({message}) => {
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

			<div className="h1 mt-3">{message}</div>

		</div>
	)
}

export default Loading