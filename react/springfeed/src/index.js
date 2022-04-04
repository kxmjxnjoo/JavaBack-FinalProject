import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

import defaultProfile from './images/tmpUserIcon.png'

// Import commonly used library here
import 'bootstrap/dist/css/bootstrap.min.css'
import './common.css'

ReactDOM.render(
	<React.StrictMode>
		<App />
	</React.StrictMode>,
	document.getElementById('root')
);