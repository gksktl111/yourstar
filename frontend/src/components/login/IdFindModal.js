import React, {useState} from 'react';
import './IdFindModal.css';
import {IoClose} from "react-icons/io5";
import {useDispatch} from "react-redux";
import {idFindModalOff} from "../../store/Store";
import axios from "axios";

const IdFindModal = () => {
    const dispatch = useDispatch();

    // 이메일 찾기
    // 회원 이메일 input 값
    const [inputEmailValue, setInputEmailValue] = useState('');
    // 이메일 검색 결과값
    const [emailSearchResult, setEmailSearchResult] = useState('');

    // 메일 발송 스테이트
    const [showEmailSend, setShowEmailSend] = useState(false);

    // 로딩 상태
    const [isLoading, setIsLoading] = useState(false);

    const style1 = {
        color: "red"
    }

    // 이메일 검사
    // 나중에 서버에서 사용자 정보 가져오기

    // 이메일 검사후 메일발송
    const emailCheck = async () => {
        // 로딩시작
        setIsLoading(true);
        await axios.post('/user/findid', {
            userEmail: inputEmailValue,
        }).then((response   ) => {
            if (response.data === "success") {
                setEmailSearchResult("확인 되었습니다!");
                setShowEmailSend("임시ID를 보냈습니다!")
            } else {
                setEmailSearchResult(<span style={style1}>회원정보가 없습니다.</span>);
                setShowEmailSend("")
            }
        }).catch(function (error) {
            console.log('실패함',error)
        }).finally(() => {
            // 로딩끝
            setIsLoading(false)
        })
    }

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
                            emailCheck();
                        }
                    }}
                />

                {/* 로딩 중 상태 표시*/}
                {isLoading && (
                    <div className="loading-spinner">
                        <p>Loading...</p>
                    </div>
                )}

                {/*인풋1의 결과 출력*/}
                <div className="login_find_modal_input1_result">
                    {emailSearchResult}
                </div>

                {/*사용자 검색 버튼*/}
                <button className="login_find_modal_button1"
                        onClick={emailCheck}>
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
