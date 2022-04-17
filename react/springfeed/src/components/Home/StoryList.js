import React, { useEffect, useState } from 'react'
import {Carousel} from 'react-bootstrap';
import Error from '../common/Error';
import Loading from '../common/Loading';

// Resources
import defaultUserIcon from '../../images/tmpUserIcon.png'

const StoryList = ({setIsStoryOpen, setStoryNum}) => {
    const [storyList, setStoryList] = useState(null)
    const [isLoading, setIsLoading] = useState(false)

    useEffect(() => {
        setIsLoading(true)
        fetch('/api/story/list')
            .then((res) => {
                return res.json()
            })
            .then((data) => {
                setStoryList([data])
            })
            .finally(() => {
                setIsLoading(false)
            })
    }, [])

  return (
    <div className='mb-3 border'>
        {
            isLoading ?
            <Loading message='스토리를 불러오고 있어요'/>
            :
            <Carousel variant='dark' indicators={false} wrap={false}>
                {
                    (storyList != null && storyList.length != 0) ?
                        storyList.map((arr) => {
                            return(
                                <Carousel.Item>
                                    {
                                        arr.map((user) => {
                                            return(
                                                <div className="row justify-content-center">
                                                    <div className="col-2 text-center" onClick={() => {
                                                        setIsStoryOpen(true)
                                                        setStoryNum(0)
                                                    }}>
                                                        <img src={ user.img == null ? defaultUserIcon : ('/images/' + user.img) } alt="PROFILE" className='d-block rounded-circle' style={{width: '75px'}}/>
                                                        <div className="h5">
                                                            {user.userid}
                                                        </div>
                                                    </div>
                                                </div>
                                            )
                                        })
                                    }
                                </Carousel.Item>
                            )
                        })
                    :
                    <Error errorMessage='스토리를 불러올 수 없었어요'/>
                }
            </Carousel>
        }
        
    </div>
  )
}

export default StoryList