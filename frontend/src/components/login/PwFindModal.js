import React, {useState} from 'react';
import './PwFindModal.css';
import {IoClose} from "react-icons/io5";
import {useDispatch} from "react-redux";
import {pwFindModalOff} from "../../store/Store";
import axios from "axios";

const PwFindModal = () => {
    const dispatch = useDispatch();

    // 회원정보 찾기 관련 스테이트
    // on/off
    const [showInputResult, setShowInputResult] = useState(false);
    // 회원정보 input 값
    const [inputIdValue, setInputIdValue] = useState('');
    // idSearchResult 값
    const [idSearchResult, setIdSearchResult] = useState('');

    // 메일 관련 스테이트
    // on/off
    const [showEmail, setShowEmail] = useState(false);
    // 질문 input 값
    const [inputEmailValue, setInputEmailValue] = useState('');
    // questionSearch 값
    const [emailResult, setEmailResult] = useState('');

    // 메일발송 관련 스테이트
    // on/off
    const [showSend, setShowSend] = useState(false);


    // id 검사
    const handleIdSearch = async () => {
        // post 주소는 바뀔수있음
        await axios.post('/user/findid', {
            id: inputIdValue,
        }).then((response) => {
            if (response.data === "success") {
                    setShowInputResult(true);
                    setShowEmail(true);
                    setIdSearchResult("확인 되었습니다!");
            } else {
                    setShowInputResult(true);
                    setShowEmail(false)
                    setIdSearchResult(<span style={{color : "red"}}>회원 정보가 없습니다.</span>);
            }
        }).catch(function (error) {
            console.log('실패함',error)
        })
    };

    // 이메일로 pw 보냄
    const handleEmailSearch = async () => {
        // post 주소는 바뀔수있음
        await axios.post('/user/findid', {
            email: inputEmailValue,
        }).then((response) => {
            if (response.data === "success") {
                    setShowInputResult(true);
                    setShowEmail(true);
                    setShowSend(true);
                    setEmailResult("메일을 발송했습니다!");
            } else {
                    setShowInputResult(true);
                    setShowSend(false)
                    setEmailResult(<span style={{color : "red"}}>다시 확인해주세요.</span>);
            }
        }).catch(function (error) {
            console.log('실패함',error)
        })
    };

    return (
        <div className="modal_background">
            <div className="pw_find_modal_container">
                {/*x 버튼*/}
                <div className="pw_find_modal_close"
                     onClick={() => dispatch(pwFindModalOff())}>
                    <IoClose style={{fontSize: "20px"}}/>
                </div>
                {/*최상단 context*/}
                <div className="pw_find_modal_context">
                    비밀번호를 잊어버리셨나요?
                </div>

                {/*사용자 검색 input*/}
                <input
                    className="pw_find_modal_input1"
                    placeholder={"아이디"}
                    value={inputIdValue}
                    // 인풋값 변경시 값 수정
                    onChange={(e) => {
                        setInputIdValue(e.target.value);
                    }}
                    // 엔터로 확인 가능
                    onKeyDown={(e) => {
                        if (e.key === 'Enter' || e.key === "Tab") {
                            handleIdSearch();
                        }
                    }}
                />

                {/*인풋1의 결과 출력*/}
                <div className="pw_find_modal_input1_result">
                    {showInputResult ? idSearchResult : ''}
                </div>

                {/*사용자 검색 버튼*/}
                <button className="pw_find_modal_button1"
                        onClick={handleIdSearch}>
                    사용자 검색
                </button>

                {/*사용자 검색 통과시 보여주기*/}
                {showEmail ? <>
                    <div className="pw_find_modal_question">
                        {/*이메일 보내기*/}
                        가입에 사용한 이메일을 적어주세요
                    </div>

                    {/*이메일 검사*/}
                    <input className='pw_find_modal_input2'
                           placeholder={"이메일을 적어주세요!"}
                           value={inputEmailValue}
                           onChange={(e) => {
                               setInputEmailValue(e.target.value);
                           }}
                           onKeyDown={(e) => {
                               if (e.key === 'Enter'|| e.key === "Tab") {
                                   handleEmailSearch();
                               }
                           }}
                    >
                    </input>

                    {/*이메일 찾기 결과 출력*/}
                    <div className="pw_find_modal_question_result">
                        {showEmail ? emailResult : ''}
                    </div>
                    <button className="pw_find_modal_button2"
                            onClick={handleEmailSearch}>
                        확인
                    </button>
                </> : null}
                {showSend ? <div className='pw_find_modal_show_pw'>
                    임시 PW를 발송했습니다!
                </div> : null}
            </div>
        </div>
    );
};

export default PwFindModal;
