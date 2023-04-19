import './App.css';
import "./fonts/font.css";
import {InputId, InputPw, LeftDiv, Login, LoginCheck, LoginCheckMessage, PageName, RightDiv} from "./routes/main/main";
import {useState} from "react";

function App () {
    let [id,setId] = useState("");
    let [pw,setPw] = useState("");

    return (
        <>
            <LeftDiv/>
            <RightDiv>
                <PageName>URSTAR</PageName>
                {/*id와 pw를 저장*/}
                <InputId onChange={(e) => {
                    setId(e.target.value)
                    // console.log(id);
                }}/>
                <InputPw onChange={(e)=>{
                    setPw(e.target.value)
                    // console.log(pw);
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