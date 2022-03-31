import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'

// Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'

// Components
import Home from './route/Home'

function App() {
  return (
    <div className="App container">

		<Router>

			<Routes>
				<Route path="/" element={<Home/>}/>
			</Routes>

		</Router>

    </div>
  );
}

export default App;
