import React, {useState} from 'react';
import './IdFindModal.css';
import {IoClose} from "react-icons/io5";
import {useDispatch} from "react-redux";
import {idFindModalOff} from "../../store/store";

const IdFindModal = () => {
    const dispatch = useDispatch();
    // 회원정보 찾기 관련 스테이트
    // on/off
    const [showInputResult, setShowInputResult] = useState(false);
    // 회원 휴대폰 번호 input 값
    const [inputNumValue, setInputNumValue] = useState('');
    // 휴대폰 번호 검색 결과값
    const [numSearchResult, setNumSearchResult] = useState('');

    // 질문 관련 스테이트
    // on/off
    const [showQuestion, setShowQuestion] = useState(false);
    // 질문 input 값
    const [inputQuestionValue, setInputQuestionValue] = useState('');
    // questionSearch 값
    const [questionResult, setQuestionResult] = useState('');

    // 아이디 관련 스테이트
    // on/off
    const [showId, setShowId] = useState(false);
    const [showIdResult, setShowIdResult] =useState('');


    // 휴대폰번호 검사
    const handleNumSearch = () => {
        // 회원정보 인풋의 내용을 가져옴
        // 나중에 서버에서 사용자 정보 가져오기

        // console.log(inputValue)
        if (inputNumValue === "01066628752") {
            setShowInputResult(true);
            setShowQuestion(true);
            setNumSearchResult("확인되었습니다!");
        } else {
            setShowInputResult(true);
            setShowQuestion(false)
            setNumSearchResult("회원정보가 없습니다.");
        }
    };

    // 질문 관련 검사
    const handleQuestionSearch = () => {
        // 서버에서 질문 가져와서 검사 후 아이디 까지 가져오기
        // 나중에 서버에서 사용자 정보 가져오기

        // 검사 통과시 아이디 가져오기
        if (inputQuestionValue === "개복치") {
            setShowInputResult(true);
            setShowQuestion(true);
            setShowId(true);
            setShowIdResult("gksktl111");
            setQuestionResult("확인되었습니다!");
        } else {
            setShowInputResult(true);
            setShowId(false)
            setQuestionResult("정보가 다릅니다!");
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
                    placeholder={"휴대폰 번호"}
                    value={inputNumValue}
                    onChange={(e) => {
                        setInputNumValue(e.target.value);
                    }}
                    onKeyDown={(e) => {
                        if (e.key === 'Enter') {
                            handleNumSearch();
                        }
                    }}
                />

                {/*인풋1의 결과 출력*/}
                <div className="login_find_modal_input1_result">
                    {showInputResult ? numSearchResult : ''}
                </div>

                {/*사용자 검색 버튼*/}
                <button className="login_find_modal_button1"
                        onClick={handleNumSearch}>
                    사용자 검색
                </button>

                {/*사용자 검색 통과시 보여주기*/}
                {showQuestion ? <>
                    <div className="login_find_modal_question">
                        {/*20자 이내로 작성하기*/}
                        당신의 어릴적 별명은?
                    </div>

                    {/*질문 답변 검사*/}
                    <input className='login_find_modal_input2'
                           placeholder={"답변을 적어주세요!"}
                           value={inputQuestionValue}
                           onChange={(e) => {
                               setInputQuestionValue(e.target.value);
                           }}
                           onKeyDown={(e) => {
                               if (e.key === 'Enter') {
                                   handleQuestionSearch();
                               }
                           }}
                    >
                    </input>

                    {/*아이디 찾기 결과 출력*/}
                    <div className="login_find_modal_question_result">
                        {showQuestion ? questionResult : ''}
                    </div>
                    <button className="login_find_modal_button2"
                            onClick={handleQuestionSearch}>
                        확인
                    </button>
                </> : null}
                {showId ? <div className = 'login_find_modal_show_id'>
                    아이디를 찾았습니다!
                    <div className = 'login_find_modal_show_id_result'>
                        {showIdResult}
                    </div>
                </div> : null}
            </div>
        </div>
    );
};

export default IdFindModal;
