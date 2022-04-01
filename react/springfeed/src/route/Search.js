import React from 'react'

// Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'

// Components
import SearchBox from '../components/Search/SearchBox'

const Search = () => {
  return (
    <div className='mt-5'>
        <div className="row">

			<div className="col-12 col-md-6">
				<SearchBox
					type='유저'
				/>
			</div>

			<div className="col-12 col-md-6">
				<SearchBox
					type='포스트'
				/>
			</div>

        </div>
    </div>
  )
}

export default Search