import React, {useState} from 'react';
import './IdFindModal.css';
import {IoClose} from "react-icons/io5";
import {useDispatch} from "react-redux";
import {idFindModalOff} from "../../store/LoginModalstore";

const IdFindModal = () => {
    const dispatch = useDispatch();

    // 이메일 찾기
    // 회원 이메일 input 값
    const [inputEmailValue, setInputEmailValue] = useState('');
    // 이메일 검색 결과값
    const [emailSearchResult, setEmailSearchResult] = useState('');

    // 메일 발송 스테이트
    const [showEmailSend, setShowEmailSend] = useState(false);

    // 이메일 검사
    const handleEmailSearch = () => {
        // 회원정보 인풋의 내용을 가져옴
        // 나중에 서버에서 사용자 정보 가져오기

        const style1 = {
            color : "red"
        }

        // console.log(inputValue)
        if (inputEmailValue === "gksktl111@naver.com") {
            setEmailSearchResult("확인 되었습니다!");
            setShowEmailSend("임시ID를 보냈습니다!")
        } else {
            setEmailSearchResult(<span style={style1}>회원정보가 없습니다.</span>);
            setShowEmailSend("")
        }
    };

    return (
        <div className="modal_background">
            <div className="login_find_modal_container">
                {/*x 버튼*/}
                <div className="login_find_modal_close"
                     onClick={() => dispatch(idFindModalOff())}>
                    <IoClose style={{fontSize: "20px"}}/>
                </div>
                {/*최상단 context*/}
                <div className="login_find_modal_context">
                    아이디를 잊어버리셨나요?
                </div>

                {/*사용자 검색 input*/}
                <input
                    className="login_find_modal_input1"
                    placeholder={"이메일을 입력해주세요"}
                    value={inputEmailValue}
                    onChange={(e) => {
                        setInputEmailValue(e.target.value);
                    }}
                    onKeyDown={(e) => {
                        if (e.key === 'Enter' || e.key === 'Tab') {
                            handleEmailSearch();
                        }
                    }}
                />

                {/*인풋1의 결과 출력*/}
                <div className="login_find_modal_input1_result">
                    {emailSearchResult}
                </div>

                {/*사용자 검색 버튼*/}
                <button className="login_find_modal_button1"
                        onClick={handleEmailSearch}>
                    사용자 검색
                </button>

                {/*사용자 검색 통과시 보여주기*/}
                <div className='login_find_modal_show_email_send'>
                    {showEmailSend}
                </div>
            </div>
        </div>
    );
};

export default IdFindModal;
