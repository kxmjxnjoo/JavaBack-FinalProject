import React from 'react'
import './message.css'

const MessageList = ({setMessageUser}) => {
    const messageData = [
        {
            id: 'jinkpark',
            img: 'visapic.jpg',
            name: '박진겸',
            message: '뭐해 지금 나올 수 있음? 빨리 나와 제발 뭐 하는지는 모르겠지만 맨날 늦어'
        },
        {
            id: 'jinkpark',
            img: 'visapic.jpg',
            name: '박진겸',
            message: '뭐해 지금 나올 수 있음? 빨리 나와 제발 뭐 하는지는 모르겠지만 맨날 늦어'
        },
        {
            id: 'jinkpark',
            img: 'visapic.jpg',
            name: '박진겸',
            message: '뭐해 지금 나올 수 있음? 빨리 나와 제발 뭐 하는지는 모르겠지만 맨날 늦어'
        },
        {
            id: 'jinkpark',
            img: 'visapic.jpg',
            name: '박진겸',
            message: '뭐해 지금 나올 수 있음? 빨리 나와 제발 뭐 하는지는 모르겠지만 맨날 늦어'
        },
        {
            id: 'jinkpark',
            img: 'visapic.jpg',
            name: '박진겸',
            message: '뭐해 지금 나올 수 있음? 빨리 나와 제발 뭐 하는지는 모르겠지만 맨날 늦어'
        },
        {
            id: 'jinkpark',
            img: 'visapic.jpg',
            name: '박진겸',
            message: '뭐해 지금 나올 수 있음? 빨리 나와 제발 뭐 하는지는 모르겠지만 맨날 늦어'
        },
        {
            id: 'jinkpark',
            img: 'visapic.jpg',
            name: '박진겸',
            message: '뭐해 지금 나올 수 있음? 빨리 나와 제발 뭐 하는지는 모르겠지만 맨날 늦어'
        },
        {
            id: 'jinkpark',
            img: 'visapic.jpg',
            name: '박진겸',
            message: '뭐해 지금 나올 수 있음? 빨리 나와 제발 뭐 하는지는 모르겠지만 맨날 늦어'
        },
        {
            id: 'jinkpark',
            img: 'visapic.jpg',
            name: '박진겸',
            message: '뭐해 지금 나올 수 있음? 빨리 나와 제발 뭐 하는지는 모르겠지만 맨날 늦어'
        },
        {
            id: 'jinkpark',
            img: 'visapic.jpg',
            name: '박진겸',
            message: '뭐해 지금 나올 수 있음? 빨리 나와 제발 뭐 하는지는 모르겠지만 맨날 늦어'
        },
        {
            id: 'jinkpark',
            img: 'visapic.jpg',
            name: '박진겸',
            message: '뭐해 지금 나올 수 있음? 빨리 나와 제발 뭐 하는지는 모르겠지만 맨날 늦어'
        },
        {
            id: 'jinkpark',
            img: 'visapic.jpg',
            name: '박진겸',
            message: '뭐해 지금 나올 수 있음? 빨리 나와 제발 뭐 하는지는 모르겠지만 맨날 늦어'
        }
    ]

  return (
    <div style={{overflow: 'scroll', height: '100%'}}>
        {
            messageData.map((user) => {
                return(
                    <MessageThumbnail
                        img={user.img}
                        id={user.id}
                        name={user.name}
                        message={user.message}

                        onClick={() => {
                            setMessageUser(user.id)
                        }}
                    />
                )
            })
        }
    </div>
  )
}

const MessageThumbnail = ({img, id, name, message}) => {
    return(
        <div className='messageThmb'>
            <div className="row p-3 overflow-hidden" style={{height: '85px'}}>
                <div className="col-2 me-3">
                    <img src={'/images/' + img} alt="" style={{width: '50px', height: '50px'}} className='rounded-circle'/>
                </div>

                <div className="col-9">
                    <div className="h5">
                        {id} - {name}
                    </div>

                    <div className="h6 text-muted overflow-hidden">
                        {message}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default MessageList