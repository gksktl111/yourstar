import React, {useEffect, useState} from 'react';
import {useInView} from 'react-intersection-observer';
import axios from 'axios';
import PeedCard from '../components/Home/PeedCard';
import {AiOutlineLoading3Quarters} from 'react-icons/ai';
import styled, {keyframes} from 'styled-components';

// 로딩 애니메이션
const spin = keyframes`
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
`;
const RotatingLoaderIcon = styled(AiOutlineLoading3Quarters)`
  font-size: 30px;
  margin-left: 35%;
  margin-top: 5%;
  animation: ${spin} 1s linear infinite;
  transform-origin: center center;
`;

const Home = () => {
        const [products, setProducts] = useState([]);
        const [page, setPage] = useState(0);
        const [ref, inView] = useInView();
        
        const productFetch = async () => {
            await axios.post('/post/get_feed_view', {
                page : page,
            }, {
                headers: {authorization: localStorage.getItem("token")},
            }).then((response) => {
                console.log(response.data);

                // 포스트 목록 가져오기
                const newPost = response.data.map((post) => ({
                    userId: post.userId,
                    name: post.userName,
                    userProFileImg: post.userProFileImg,
                    postId: post.postId,
                    meta: post.meta,
                    contents: post.contents,
                    likeCount: post.likeCount,
                    likeStatus: post.likeStatus,
                }));

                console.log(newPost)

                setPage(page+1);
                setProducts([...products, ...newPost]);
            }).catch(function (error) {
                console.log("실패함", error);
            })
        };

        useEffect(() => {
            if (inView) {
                productFetch();
            }
        }, [inView]);

        return (
            <>
                <div>
                    {/* 기존 예시 PeedCard */}

                    {/* 서버에서 가져온 데이터를 출력 */}
                    {products.map((product, index) => (
                        <div key={index}>
                            <PeedCard product={product} />
                        </div>
                    ))}
                </div>
                <div ref={ref} style={{height: '30%'}}>
                    <RotatingLoaderIcon/>
                </div>
            </>
        );
    }
;

export default Home;
