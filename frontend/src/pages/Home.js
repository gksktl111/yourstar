import React, {useEffect, useRef, useState} from 'react';
import {useInView} from "react-intersection-observer";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import PeedCard from "../components/Home/PeedCard";
import {AiOutlineLoading3Quarters, AiOutlinePlusCircle} from "react-icons/ai";
import styled, {keyframes} from "styled-components";

const Home = () => {
    // 피드관련
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(0); // 현재 페이지 번호 (페이지네이션)
    const [ref, inView] = useInView();

    // 무한 스크롤
    // 지정한 타겟 div가 화면에 보일 때 마다 서버에 요청을 보냄
    const productFetch = () => {
        axios.get(`https://localhost:8080/products/main?pageNo=${page}&pageSize=5`)
            .then((res) => {
                console.log(res.data);
                // 리스트 뒤로 붙여주기
                setProducts([...products, ...(res.data)])
                // 요청 성공 시에 페이지에 1 카운트 해주기
                setPage((page) => page + 1)
            })
            .catch((err) => {
                console.log(err)
            });
    };

    useEffect(() => {
        // inView가 true 일때만 실행한다.
        if (inView) {
            console.log(inView, '무한 스크롤 요청 🎃')

            for (let i = 1; i <= 5; i++) {
                setProducts([...products, <div>
                    <div style={{height: '20%', backgroundColor: 'black'}}>

                    </div>
                    <div style={{height: '10%'}}></div>
                </div>])
            }

            // productFetch();
        }
    }, [inView]);

    //로딩 아이콘
    // @keyframe spin
    const spin = keyframes`
      0% {
        transform: rotate(0deg);
      }
      100% {
        transform: rotate(360deg);
      }
    `;
    // Styled Icon
    const RotatingLoaderIcon = styled(AiOutlineLoading3Quarters)`
      font-size: 30px;
      margin-left: 35%;
      margin-top: 5%;
      animation: ${spin} 1s linear infinite;
      transform-origin: center center;
    `;

    return (
        <>
            <div>
                <div></div>
                {/*    일단 예시로 만든거임 나중에는 서버에서*/}
                {/*    데이터 가져와서 map으로*/}
                {/*    돌려서 출력할꺼임*/}
                <PeedCard/>
                <PeedCard/>
                <PeedCard/>
                <PeedCard/>
                <PeedCard/>
                <PeedCard/>
                {/*여기서 내용을 추가함 프로덕트 뒤에 리스트 형태로 추가됨*/}
                {products?.map((product, index) => {
                    <div key={index}></div>
                })}
                {/*이부분 관찰시 데이터 불러옴*/}
            </div>
            <div ref={ref} style={{height: '30%'}}>
                <RotatingLoaderIcon/>
            </div>
        </>
    );
};


export default Home;