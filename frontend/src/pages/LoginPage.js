import styled, {keyframes} from 'styled-components';
import {useEffect, useState} from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';
import {useDispatch, useSelector} from "react-redux";
import IdFindModal from "../components/login/IdFindModal";
import {idFindModalOn} from "../store/store";

let LeftDiv = styled.div`
  position: absolute;
  width: 23%;
  height: 75%;
  left: 38%;
  top: 50%;

  transform: translate(-50%, -50%);
  background: url("/assets/img/${props => props.num}.jpg") no-repeat center center fixed;
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

let PageName = styled.span`
  position: absolute;
  left: 28%;
  top: 5%;

  min-width: 140px;
  
  font-family: 'PyeongChangPeace-Bold', sans-serif;
  font-weight: 700;
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

  font-family: 'Pretendard', sans-serif;

  border: 1px solid #D4D4D4;
  border-radius: 3px;
  outline: none;

  ::placeholder {
    color: #A0A0A0;
    font-family: 'Pretendard', sans-serif;
    font-size: 14px;
  }
  
  // 드래그 방지
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
`

let InputPw = styled.input.attrs({type: "password"})`
  position: absolute;
  width: 79%;
  height: 6%;
  left: 10%;
  top: 33%;

  background: #F4F4F4;

  font-family: 'Pretendard', sans-serif;

  border: 1px solid #D4D4D4;
  border-radius: 3px;
  outline: none;

  ::placeholder {
    color: #A0A0A0;
    font-family: 'Pretendard', sans-serif;
    font-size: 14px;
  }

  // 드래그 방지
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
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
  top: 45%;

  color: white;
  background: #7946FF;

  font-family: 'Pretendard', sans-serif;

  border: 1px solid #D4D4D4;
  border-radius: 5px;
  outline: none;

  //버튼에 마우스를 올리면 밝아지는 효과
  &:hover {
    opacity: 0.8;
    animation: ${fadeIn} 0.2s ease-in-out;
  }

  // 드래그 방지
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
`

let FindId = styled.button`
  position: absolute;
  width: 20%;
  height: 6%;
  left: 24.5%;
  top: 52.5%;

  color: #000000;
  background: #ffffff;

  font-family: 'Pretendard', sans-serif;

  font-size: 0.5vw;

  border: none;

  //버튼에 마우스를 올리면 커지는 효과
  &:hover {
    transform: scale(1.2);
  }

  // 드래그 방지
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
`

let FindPw = styled.button`
  position: absolute;
  width: 20%;
  height: 6%;
  left: 55.5%;
  top: 52.5%;

  color: #000000;
  background: #ffffff;

  font-family: 'Pretendard', sans-serif;

  font-size: 0.5vw;

  border: none;
  outline: none;

  //버튼에 마우스를 올리면 커지는 효과
  &:hover {
    transform: scale(1.2);
  }

  // 드래그 방지
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
`

let SignUp = styled.button`
  position: absolute;
  width: 81%;
  height: 6%;
  left: 9.5%;
  top: 60%;

  color: #000000;
  background-color: #ffffff;

  font-family: 'Pretendard', sans-serif;

  font-size: 1.0vw;

  border: none;
  outline: none;

  //버튼에 마우스를 올리면 커지는 효과
  &:hover {
    transform: scale(1.2);
  }

  // 드래그 방지
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
`

// 이미지 전환 애니메이션
function LoginImage() {
    const [imageNum, setImageNum] = useState(1);
    let cnt = 1;

    useEffect(() => {
        const interval = setInterval(() => {
            cnt = cnt + 1;
            console.log(imageNum);
            if (cnt === 6) {
                setImageNum(1);
                cnt = 1;
            }
            setImageNum(cnt);
        }, 5000);
        return () => clearInterval(interval);
    }, []);

    return (
        <LeftDiv num={imageNum}/>
    );
}

function LoginPage() {
    const [inputId, setInputId] = useState("");
    const [inputPw, setInputPw] = useState("");

    //const [isIdFindModalOpen, setIsIdFindModalOpen] = useState(false);
    let state =  useSelector((state) => {return state});
    let dispatch = useDispatch();

    const [isPwFindModalOpen, setIsPwFindModalOpen] = useState(false);
    const [isSignUpModalOpen, setIsSignUpModalOpen] = useState(false);

    const handleInputId = (e) => {
        setInputId(e.target.value);
    };

    const handleInputPw = (e) => {
        setInputPw(e.target.value);
    };

    const navigate = useNavigate();

    // 로그인 검사
    const loginCheck = async () => {
        await axios.post('/login/check', {
            id: inputId,
            pw: inputPw
        }).then((response) => {
            if(response.data == false){
                alert('회원정보가 다릅니다')
            }else {
                localStorage.setItem('logincheck', true);
                navigate('/');
            }
        }).catch(function () {
                console.log('실패함')
        })
    }


    return (
        <>
            <LoginImage/>
            <RightDiv>
                <PageName>URSTAR</PageName>
                {/*id와 pw를 저장*/}
                <InputId
                    placeholder="아이디"
                    onChange={handleInputId}/>
                <InputPw
                    placeholder="비밀번호"
                    onChange={handleInputPw}/>
                <LoginButton
                    onClick={loginCheck}
                >로그인</LoginButton>
                <FindId onClick={() => {
                    dispatch(idFindModalOn())
                }}>
                    아이디 찾기</FindId>
                <FindPw>비밀번호 찾기</FindPw>
                <SignUp>회원이 아니신가요?</SignUp>
            </RightDiv>

            {/*아이디 찾기 모달창*/}
            {state.isIdFindModalOpen === true ?  <IdFindModal/> : null}
        </>
    )
}

export default LoginPage;