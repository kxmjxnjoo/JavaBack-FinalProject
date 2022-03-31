import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'

// Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'

// Components
import Home from './route/Home'
import Topnav from './components/Topnav'

function App() {
  return (
    <div className="App">
		<Topnav/>

		<Router>

			<Routes>
				<Route path="/" element={<Home/>}/>
			</Routes>

		</Router>

    </div>
  );
}

export default App;
