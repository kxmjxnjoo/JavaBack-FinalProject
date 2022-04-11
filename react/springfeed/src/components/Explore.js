import React, { useEffect, useState } from 'react'
import Loading from './common/Loading'

const Explore = ({ setPage }) => {
    const [isLoading, setIsLoading] = useState(true)

    useEffect(() => {
        setPage(3)
        setTimeout(() => {
            setIsLoading(false)
        }, 2000)
    }, [])

    const exploreData = [
        {
            img: 'http://picsum.photos/500/600/'
        },
        {
            img: 'http://picsum.photos/500/600//'
        },
        {
            img: 'http://picsum.photos/500/600///'
        },
        {
            img: 'http://picsum.photos/500/600////'
        },
        {
            img: 'http://picsum.photos/500/600/////'
        },
        {
            img: 'http://picsum.photos/500/600//////'
        },
        {
            img: 'http://picsum.photos/500/600///////'
        },
        {
            img: 'http://picsum.photos/500/600////////'
        },
        {
            img: 'http://picsum.photos/500/600/////////'
        },
        {
            img: 'http://picsum.photos/500/600//////////'
        },
        {
            img: 'http://picsum.photos/500/600///////////'
        },
        {
            img: 'http://picsum.photos/500/600////////////'
        }
    ]

    const imgStyle = {
        width: '100%',
        objectFit: 'cover'
    }

    return (

        <div className='mt-5 mb-5'>
            {
                isLoading ? <Loading message='인기 포스트를 불러오고 있어요' className='mt-5' /> :
                    <>
                        <div className="row">
                            <div className="col-4 p-0">
                                <img src="http://picsum.photos/500/500" alt="" style={imgStyle} className='w-100 img-responsive' />
                                <img src="http://picsum.photos/500/500/" alt="" style={imgStyle} className='w-100 img-responsive' />
                            </div>
                            <div className="col-8 p-0">
                                <img src="http://picsum.photos/500/500//" alt="" style={imgStyle} className='w-100 img-responsive' />
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-4 p-0">
                                <img src="http://picsum.photos/500/500///" alt="" style={imgStyle} className='w-100 img-responsive' />
                            </div>
                            <div className="col-4 p-0">
                                <img src="http://picsum.photos/500/500////" alt="" style={imgStyle} className='w-100 img-responsive' />
                            </div>
                            <div className="col-4 p-0">
                                <img src="http://picsum.photos/500/500/////" alt="" style={imgStyle} className='w-100 img-responsive' />
                            </div>
                        </div>
                    </>
            }
        </div>

    )
}

export default Explore