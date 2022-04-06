import React from 'react'
import { useEffect, useState } from 'react'
import toast from 'react-hot-toast'

import defaultProfile from '../../images/tmpUserIcon.png'

const FollowingList = ({ user }) => {
    const [followingListIndex, setFollowingListIndex] = useState(0)
    const [followingList, setFollowingList] = useState(null)
    const [isFollowingError, setIsFollowingError] = useState(false)
    const [isError, setIsError] = useState(false)

    useEffect(() => {
        const head = new Headers({
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        });

        fetch("/api/user/following?id=" + user.userid, head)
            .then((res) => {
                return res.json()
            })
            .then((data) => {
                setFollowingList(data)
            })
            .catch((err) => {
                setIsFollowingError(true)
            })
    }, [])

    const loadFollowingList = () => {
        fetch("/api/user/following?id=" + user.userid + "&page=" + followingListIndex)
            .then((res) => {
                return res.json()
            })
            .then((data) => {
                if (data === null || data === '') {
                    toast.error("더 이상 팔로잉하고 있는 유저가 없어요")
                }
                setFollowingList(arr => [...arr, data])
                setFollowingListIndex(followingListIndex + 1)
            })
            .catch((err) => {
                setIsFollowingError(true)
            })
    }

    const imageStyle = {
        width: '100px'
    }

    const imageUserStyle = {
        width: '75px'
    }

    return (
        <div>
            <div>

                <div className="row">
                    <div className="col-6">
                        <img src={defaultProfile} alt="" style={imageStyle} />
                    </div>
                    <div className="col-6 mt-2">
                        <div className="h3">{user.userid}</div>
                        <div className="h3 text-muted">{user.name}</div>
                    </div>
                </div>
            </div>

            <div className="h3 mt-3">내가 팔로우한 사람</div>

            <div>
                {
                    !isError && followingList != null ?
                        followingList.map((data) => {
                            return (
                                <div className="row">
                                    <div className="col-4">
                                        <img src={defaultProfile} alt="" style={imageUserStyle} />
                                    </div>

                                    <div className="col-8">
                                        <div className="h5">{data.userid}</div>
                                        <div className="h5 text-muted">{data.name}</div>
                                    </div>
                                </div>
                            )
                        })
                        :
                        <>
                            Error
                        </>
                }

                <div className="btn btn-success w-100 mt-3" onClick={loadFollowingList}>더 보기</div>
            </div>
        </div>
    )
}

export default FollowingList