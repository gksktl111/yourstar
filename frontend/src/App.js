import './App.css';
import "./fonts/font.css";
import {
    InputId,
    InputPw,
    Login,
    LoginCheck,
    LoginCheckMessage,
    LoginImage,
    PageName,
    RightDiv
} from "./routes/main/login";
import {useState} from "react";

function App () {
    let [id,setId] = useState("");
    let [pw,setPw] = useState("");

    return (
        <>
            <LoginImage/>
            <RightDiv>
                <PageName>URSTAR</PageName>
                {/*id와 pw를 저장*/}
                <InputId onChange={(e) => {
                    setId(e.target.value)
                }}/>
                <InputPw onChange={(e)=>{
                    setPw(e.target.value)
                }}/>
                <LoginCheck/>
                <LoginCheckMessage>로그인 정보 저장하기</LoginCheckMessage>
                <Login onClick={() =>{
                    console.log(id);
                    console.log(pw);
                }
                }>LOGIN</Login>
            </RightDiv>
        </>
    )
}

export default App;