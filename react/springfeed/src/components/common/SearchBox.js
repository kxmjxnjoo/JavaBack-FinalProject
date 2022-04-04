import React from 'react'

// Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'

// Resources
import profile from '../../images/tmpUserIcon.png'

// React Icon
import { BsArrowRightShort as ArrowIcon } from 'react-icons/bs'

const SearchBox = ({type, searchResult}) => {
    const posts = [
        {
            num: 5
        }
    ]
    const users = [
        {
            userid: "jinkpark",
            name: "박진겸"
        }
    ]

  return (
    <div className='card mb-5'>

        <div className="h3 card-header">{type}</div>

        <div className="card-body">
            {
                type === 'post' ?
                    posts.map((post) => {
                        return(
                            <PostInfoBox/>
                        )
                    })
                :
                    users.map((user) => {
                        return(
                            <UserInfoBox user={user}/>
                        )
                    })

            }
        </div>
    </div>
  )
}

const UserInfoBox = (({user}) => {
    const profileStyle = {
        width: "75%"
    }

    return(
        <a className="row p-3 view overlay zoom rgba-stylish-strong text-decoration-none" href={'/post?num=' + user.num}>
                <div className="d-flex col-3 justify-content-center">
                    <img src={profile} alt="" style={profileStyle} />
                </div>
                <div className="col-8">
                    <div className="h3 text-black">USERNAME</div>
                    <div className="h3 text-muted">USERID</div>
                </div>
                <div className="col-1 h1 p-2 text-muted">
                    <ArrowIcon/>
                </div>
        </a>
    )
})

const PostInfoBox = (({post}) => {
    return(
        <div>

        </div>
    )
})

export default SearchBox