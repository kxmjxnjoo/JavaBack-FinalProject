import React from 'react'
import { Link } from 'react-router-dom'
// Resources
import profile from '../../images/tmpUserIcon.png'

// React Icon
import { BsArrowRightShort as ArrowIcon } from 'react-icons/bs'


const SearchBox = () => {
    return (
        <div>
            <div className="row">
                <div className="col-4">
                    <img src={profile} alt="" className="img-thumbnail rounded" />
                </div>
                <div className="col-8">
                    <div className="h4">USERNAME</div>
                    <div className="h4 text-muted">NAME</div>
                </div>
            </div>
        </div>
    )
}

export default SearchBox