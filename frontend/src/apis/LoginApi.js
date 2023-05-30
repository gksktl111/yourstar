import React from 'react';
import axios from "axios";

const LoginApi = () => {
    return (
        <div>

        </div>
    );
};

const EmailCheck = async () => {
    await axios.post('/user/findid', {
        email: inputEmailValue,
    }).then((response) => {
        if (response.data === "success") {
            setEmailSearchResult("확인 되었습니다!");
            setShowEmailSend("임시ID를 보냈습니다!")
        } else {
            setEmailSearchResult(<span style={style1}>회원정보가 없습니다.</span>);
            setShowEmailSend("")
        }
    }).catch(function (error) {
        console.log('실패함',error)
    })
}

export {EmailCheck};