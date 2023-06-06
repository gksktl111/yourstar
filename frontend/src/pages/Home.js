import React, { useEffect, useState } from 'react';
import { useInView } from 'react-intersection-observer';
import axios from 'axios';
import PeedCard from '../components/Home/PeedCard';
import { AiOutlineLoading3Quarters } from 'react-icons/ai';
import styled, { keyframes } from 'styled-components';

const spin = keyframes`
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
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

    // 여기에 url 주소
    const productFetch = () => {
        axios.get(`https://localhost:8080/products/main?pageNo=${page}&pageSize=5`)
            .then((res) => {
                setProducts([...products, ...res.data]);
                setPage((page) => page + 1);
            })
            .catch((err) => {
                console.log(err);
            });
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
                <PeedCard />
                <PeedCard />
                <PeedCard />
                <PeedCard />
                <PeedCard />

                {/* 서버에서 가져온 데이터를 출력 */}
                {products.map((product, index) => (
                    <div key={index}>{product}</div>
                ))}
            </div>
            <div ref={ref} style={{ height: '30%' }}>
                <RotatingLoaderIcon />
            </div>
        </>
    );
};

export default Home;
