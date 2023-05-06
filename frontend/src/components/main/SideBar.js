import styled, {keyframes} from 'styled-components';
import {Route, Routes, NavLink} from "react-router-dom";


// SideBar 컴포넌트 설정
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

// 링크 밝아지는 애니메이션
const fadeOut = keyframes`
  from {
    opacity: 1;
  }
  to {
    opacity: 1;
  }
`;

// Nav의 칸 크기 설정
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

// Nav의 빈공간이 필요해서 만듬
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

// Nav위에 메인 이미지
let NavMainImg = styled.div`
  position: absolute;
  
  width: 70px;
  height: 70px;
  
  
  background-image: url("/img/urstar.png");
  background-size: cover;
  
`

// Nav내부에 이미지 삽입
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

// SideBar 텍스트
let NavTag = styled.span`
  position: absolute;
  
  left: 65px;
  
  font-family: Pretendard-Regular;
  
  text-align: left;
`

// 오른쪽의 컨텍츠
let Content = styled.div`
  position: absolute;
  left: 200px;
  width: 90%;
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

const NavStyle ={
    color : 'black',
}

function SideBar(){

    return(
        <>
            <Nav>
                <NavSectionBlank>
                    <NavMainImg/>
                </NavSectionBlank>

                <NavSectionBlank/>

                <NavSection>
                    <NavLink exact to={"/main"}>
                        <NavImg img={"home"} />
                        <NavTag>홈</NavTag>
                    </NavLink >
                </NavSection>

                <NavSection>
                    <NavLink to={"/main/profile"}>
                        <NavImg img={"profile"} />
                        <NavTag>프로필</NavTag>
                    </NavLink>
                </NavSection>

                <NavSection>
                    <NavImg img={"email"} />
                    <NavTag>메시지</NavTag>
                </NavSection>

                <NavSection>
                    <NavImg img={"music"} />
                    <NavTag>플레이리스트</NavTag>
                </NavSection>

                <NavSection>
                    <NavImg img={"setting"} />
                    <NavTag>설정</NavTag>
                </NavSection>
            </Nav>

            <Content>
                <Routes>
                    <Route exact path="/main">
                    </Route>
                    <Route path="/main/profile">
                    </Route>
                </Routes>
            </Content>
        </>
    )
}

export default SideBar;