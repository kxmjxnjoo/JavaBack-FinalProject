import React from 'react'
import Loading from './Loading'
import { Link } from 'react-router-dom'
import defaultProfile from '../../images/tmpUserIcon.png'

const FollowList = ({isError, followList}) => {
    const imageUserStyle = {
        width: '75px'
    }

  return (
      <>
        {
            !isError && followList != null ?
            followList.map((data) => {
                return (
                    <a href={'/user/page/' + data.userid} style={{textDecoration: 'none', color: 'black'}}>
                        <div className="row">
                            <div className="col-4">
                                <img src={defaultProfile} alt="" style={imageUserStyle} />
                            </div>
        
                            <div className="col-7 ms-2">
                                <div className="h5">{data.userid}</div>
                                <div className="h5 text-muted">{data.name}</div>
                            </div>
                        </div>
                    </a>
                )
            })
            :
            <Loading message='로딩중'/>
        }      
      </>
    )
}

export default FollowList