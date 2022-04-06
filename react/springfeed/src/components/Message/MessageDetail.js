import React from 'react'
import toast from 'react-hot-toast'
import defaultProfile from '../../images/tmpUserIcon.png'

const MessageDetail = () => {
    const messageData = [
        {
            send: 'user1',
            content: 'love you!'
        },
        {
            send: 'user1',
            content: 'care to join?'
        },
        {
            send: 'user2',
            content: 'nah im alright'
        }
    ]

    const sendMessage = () => {
        toast.success("메세지를 보냈어요")
    }

    return (
        <div className='card'>
            <div className="card-header">
                <div className="row">
                    <div className="col-2">
                        <img src={defaultProfile} alt="" style={{ width: '75px' }} />
                    </div>
                    <div className="col-10 mt-1">
                        <div className="h4">
                            USERID
                        </div>
                        <div className="h4 text-muted">
                            NAME
                        </div>
                    </div>
                </div>
            </div>

            <div className="card-body" style={{ height: '450px' }}>

            </div>

            <div className="card-footer">


                <div className="form">
                    <div className="row">
                        <div className="col-10">
                            <input type="text" name="" id="" placeholder='보낼 메세지를 입력해 주세요' className='form-control' />
                        </div>
                        <div className="col-2">
                            <div className="btn btn-success w-100" onClick={sendMessage}>보내기</div>
                        </div>
                    </div>
                </div>


            </div>

        </div>
    )
}

export default MessageDetail