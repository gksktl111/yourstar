import styled, {keyframes} from 'styled-components';
import {useEffect, useState} from "react";

let LeftDiv = styled.div`
  position: absolute;
  width: 23%;
  height: 75%;
  left: 38%;
  top: 50%;

  transform: translate(-50%, -50%);
  background: url("/img/${props => props.num}.jpg") no-repeat center center fixed;
  background-size: cover;
  border-radius: 5px;

  // 일정 크기 이하로 줄어들면 사라짐
  @media (max-width: 1050px), (max-height: 520px) {
    display: none;
  }
`

let RightDiv = styled.div`
  position: absolute;
  width: 23%;
  min-width: 250px;
  height: 75%;
  min-height: 400px;
  left: 63%;
  top: 50%;
  transform: translate(-50%, -50%);
  
  background-image: ${props => props.mainPhoto};
  border: 1px solid #D4D4D4;
  border-radius: 5px;

  @media (max-width: 1050px), (max-height: 520px) {
    left: 50%;
  }
`

let PageName = styled.div`
  position: absolute;
  left: 26%;
  top: 5%;
  
  min-width: 250px;

  font-family: PyeongChangPeace-Bold;
  font-style: normal;
  font-size: 2.5vw;
  line-height: 5rem;

  color: #7946FF;

  // 드래그 방지
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  
  // 일정 크기로 줄어들면 폰트 고정
  @media (max-width: 1050px), (max-height: 520px) {
    font-size: 28px;
  }
`

let InputId = styled.input`
  position: absolute;
  width: 79%;
  height: 6%;
  left: 10%;
  top: 25%;

  background: #F4F4F4;

  font-family: Pretendard-Regular;

  border: 1px solid #D4D4D4;
  border-radius: 3px;
  outline: none;
`
let InputPw = styled.input`
  position: absolute;
  width: 79%;
  height: 6%;
  left: 10%;
  top: 33%;

  background: #F4F4F4;

  font-family: Pretendard-Regular;

  border: 1px solid #D4D4D4;
  border-radius: 3px;
  outline: none;
`

const fadeIn = keyframes`
  from {
    opacity: 1;
  }
  to {
    opacity: 0.8;
  }
`;

let LoginButton = styled.button`
  position: absolute;
  width: 81%;
  height: 6%;
  left: 9.5%;
  top: 55%;

  color: white;
  background: #7946FF;
  
  font-family: Pretendard-Regular;

  border: 1px solid #D4D4D4;
  border-radius: 5px;
  outline: none;
  
  //버튼에 마우스를 올리면 밝아지는 효과
  &:hover {
    opacity: 0.8;
    animation: ${fadeIn} 0.2s ease-in-out;
  }
`

let LoginCheck = styled.input.attrs({type : "checkbox"})`
  position: absolute;
  width: 60%;
  height: 3.5%;
  left: -17%;
  top: 45%;
`

let LoginCheckMessage = styled.p`
  position: absolute;
  left: 20%;
  top: 43%;
  
  font-family: Pretendard-Regular;
  font-size: 12px;
  
  // 드래그 방지 
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
`

// 이미지 전환 애니메이션
function LoginImage(){
    const [imageNum, setImageNum] = useState(1);
    let cnt = imageNum;

    useEffect(() => {
        const interval = setInterval(() => {
            cnt = cnt +1;
            if(cnt === 6) {
                setImageNum(1);
                cnt = imageNum;
            }
            setImageNum(cnt);
        }, 5000);
        return () => clearInterval(interval);
    }, []);

    return (
        <LeftDiv num={imageNum} />
    );
}

function LoginPage(){
    let [id,setId] = useState("");
    let [pw,setPw] = useState("");

    return(
        <>
        <LoginImage/>
        <RightDiv>
        <PageName>URSTAR</PageName>
        {/*id와 pw를 저장*/}
        <InputId
            onChange={(e) => {
            setId(e.target.value)
        }}/>
        <InputPw onChange={(e)=>{
            setPw(e.target.value)
        }}/>
        <LoginCheck/>
        <LoginCheckMessage>로그인 정보 저장하기</LoginCheckMessage>
        <LoginButton onClick={() =>{
            console.log(id);
            console.log(pw);
        }
        }>LOGIN</LoginButton>
        </RightDiv>
        </>
    )
}

export default LoginPage;