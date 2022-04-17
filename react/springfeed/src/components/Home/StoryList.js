import React, { useEffect, useState } from 'react'
import {Carousel} from 'react-bootstrap';
import Error from '../common/Error';

// Resources
import defaultUserIcon from '../../images/tmpUserIcon.png'

const StoryList = ({setIsStoryOpen, setStoryNum}) => {
    const [storyList, setStoryList] = useState(null)

    useEffect(() => {
        fetch('/api/story/list')
            .then((res) => {
                return res.json()
            })
            .then((data) => {
                setStoryList(data)
            })
    })


    /*
    const storyList = [
        [
            {
                num: 1,
                img: '',
                userid: 'userid1'
            },
            {
                num: 2,
                img: '',
                userid: 'userid2'
            },
            {
                num: 3,
                img: '',
                userid: 'userid3'
            },    
            {
                num: 4,
                img: '',
                userid: 'userid4'
            },  
            {
                num: 5,
                img: '',
                userid: 'userid5'
            }
        ]
    ]
    */


  return (
    <div className='mb-3 border'>

        <Carousel variant='dark' indicators={false} wrap={false}>
            {
                (storyList != null && storyList.length != null)?
                    storyList.map((stories) => {
                        return(
                            <Carousel.Item>
                                <div className="row justify-content-center">
                                    {
                                        stories.map((story) => {
                                            return(
                                                <div className="col-2 text-center" onClick={() => {
                                                    setIsStoryOpen(true)
                                                    setStoryNum(story.num)
                                                }}>
                                                    <img src={defaultUserIcon} alt="" className='d-block' style={{width: '75px'}}/>
                                                    <div className="h5">
                                                        {story.userid}
                                                    </div>
                                                </div>
                                            )
                                        })
                                    }
                                </div>
                            </Carousel.Item>
                        )
                    })
                :
                <Error errorMessage='스토리를 불러올 수 없었어요'/>
            }
        </Carousel>
        
    </div>
  )
}

export default StoryList