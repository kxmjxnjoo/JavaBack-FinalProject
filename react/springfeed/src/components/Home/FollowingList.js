import React from 'react'

import defaultProfile from '../../images/tmpUserIcon.png'

const FollowingList = ({ user, followingList }) => {
    const imageStyle = {
        width: '100px'
    }

    return (
        <div>
            <div>
                <div className="row">
                    <div className="col-6">
                        <img src={defaultProfile} alt="" style={imageStyle} />
                    </div>
                    <div className="col-6 mt-2">
                        <div className="h3">USERID</div>
                        <div className="h3 text-muted">NAME</div>
                    </div>
                </div>
            </div>

            <div className="h3 mt-3">내가 팔로우한 사람</div>
            <div>
            </div>
        </div>
    )
}

export default FollowingList