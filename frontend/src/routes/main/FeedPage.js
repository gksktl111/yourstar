import styled, {keyframes} from 'styled-components';
import {useEffect, useState} from "react";

let Nav = styled.div`
  position: fixed;
  width: 200px;
  height: 90%;
  background-color: white;
  border-right: 1px solid black;

  // 휴대폰 크기에 맞춰서 적용하기 
  //@media (max-width: 480px){
  //  display: none;
  //}
`

// 휴대폰 컴포넌트
let PhoneNav = styled.div`
  
`

const fadeOut = keyframes`
  from {
    opacity: 1;
  }
  to {
    opacity: 1;
  }
`;

let NavSection = styled.div`
  width: 88.5%;
  height: 6%;
  
  display: flex;

  background-color: white;
  
  //border-bottom: 1px solid black;
  //border: 1px solid black;
  
  border-radius: 5px  ;
  
  justify-content: center;
  align-items: center;
  background-size: cover;
  
  margin: 20px;
  margin-left: 10px;

  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;

  //버튼에 마우스를 올리면 밝아지는 효과
  &:hover {
    animation: ${fadeOut} 0.2s ease-in-out;
    filter: brightness(90%);
  }
`

let NavSectionBlank = styled.div`
  width: 88.5%;
  height: 6%;
  
  display: flex;
  justify-content: center;
  align-items: center;
  background-size: cover;
  
  margin: 20px;
  margin-left: 10px;
`


let NavMainImg = styled.div`
  position: absolute;
  
  width: 70px;
  height: 70px;
  
  
  background-image: url("/img/urstar.png");
  background-size: cover;
  
`

let NavImg = styled.div`
  position: absolute;
  
  width: 25px;
  height: 25px;
  
  left: 10px;
  
  background-image: url("/img/${props => props.img}.png");
  background-size: cover;
  
  border-radius: 5px;
  
  margin-left: 10px;
  
`

let NavTag = styled.span`
  position: absolute;
  
  left: 65px;
  
  font-family: Pretendard-Regular;
  
  text-align: left;
`

let Content = styled.div`
  position: absolute;
  width: 100%;
  height: 90%;
  background-color: black;
`

let MusicPlayer = styled.div`
  position: fixed;
  width: 100%;
  height: 20%;
  top: 90%;
  background-color: #0854f6;
`

function FeedPage(){
    return(
        <>
            <Content></Content>
            <Nav>
                <NavSectionBlank>
                    <NavMainImg/>
                </NavSectionBlank>

                <NavSectionBlank/>

                <NavSection>
                    <NavImg
                        img={"home"}/>
                    <NavTag>홈</NavTag>
                </NavSection>

                <NavSection>
                    <NavImg
                        img={"user"}/>
                    <NavTag>프로필</NavTag>
                </NavSection>

                <NavSection>
                    <NavImg
                        img={"email"}/>
                    <NavTag>메시지</NavTag>
                </NavSection>

                <NavSection>
                    <NavImg
                        img={"music"}/>
                    <NavTag>플레이리스트</NavTag>
                </NavSection>

                <NavSection>
                    <NavImg
                        img={"setting"}/>
                    <NavTag>설정</NavTag>
                </NavSection>
            </Nav>
            <MusicPlayer></MusicPlayer>
        </>
    )
}

export default FeedPage;