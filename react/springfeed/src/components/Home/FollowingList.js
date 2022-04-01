import React from 'react'

// Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'

// Resources
import profile from '../../images/tmpUserIcon.png'

const FollowingList = ({user, followingList}) => {
    const imageStyle = {
        width: '100px'
    }

  return (
    <div>
        <div>
            <img src={ user.img == null || user.img === '' ?  profile : user.img } alt="" style={imageStyle}/>
        </div>

        <div className="h3 mt-3">내가 팔로우한 사람</div>
        <div>
            {
                followingList.map((following) => {
                    return(
                        <div>
                            
                        </div>
                    )
                })
            }
        </div>
    </div>
  )
}

export default FollowingList