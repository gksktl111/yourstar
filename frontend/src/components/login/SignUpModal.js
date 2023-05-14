import React, {useState} from 'react';
import './SignUpModal.css';
import {IoClose} from "react-icons/io5";
import {useDispatch} from "react-redux";
import {signUpModalOff} from "../../store/LoginModalstore";

const SignUpModal = () => {
    const dispatch = useDispatch();

    // 검사 통과 될시 스타일
    const style1 = {
        color: "green",
        fontSize: "15px"
    }

    // 회원가입 정보들 스테이트
    // email input 값
    const [inputEmailValue, setInputEmailValue] = useState('');
    // emailSearchResult 값
    const [emailSearchResult, setEmailSearchResult] = useState('');

    // number input 값
    const [inputNumberValue, setInputNumberValue] = useState('');
    // numberSearchResult 값
    const [numberSearchResult, setNumberSearchResult] = useState('')

    // id input 값
    const [inputIdValue, setInputIdValue] = useState('');
    // idSearchResult 값
    const [idSearchResult, setIdSearchResult] = useState('')


    // 이메일 검사
    const handleEmailSearch = () => {
        // 이메일 정규식 5~20자의 영문 소문자, 숫자와 특수기호(_),(-)
        const emailRegexp = /^[a-z0-9_-]{5,20}(\.[a-z0-9_-]+)*@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/;

        // 이메일 정규식 위반시 스타일
        const style2 = {
            color: "red",
            fontSize: "10px"
        }

        // 이메일 검사
        // 이메일 형식이 맞지 않으면 오류 메시지 출력 후 함수 종료
        if (!emailRegexp.test(inputEmailValue)) {
            setEmailSearchResult(<span style={style2}>5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.</span>);
            return;
        }

        const domainRegexp = /(naver|gmail|daum|hotmail)\.(com|kr)/;

        // 도메인 형식이 맞지 않으면 오류 메시지 출력 후 함수 종료
        if (!domainRegexp.test(inputEmailValue.split('@')[1])) {
            setEmailSearchResult("잘못된 도메인 형식입니다.");
            return;
        }

        // TODO: 서버와 통신해서 이메일 중복 검사

        if (inputEmailValue === "gksktl111@naver.com") {
            setEmailSearchResult("이미 존재하는 메일입니다");
            return;
        }

        setEmailSearchResult(<span style={style1}>확인 되었습니다!</span>);
    };

    // 전화번호 검사
    const handleNumberSearch = () => {
        const regex = /^010\d{4}\d{4}$/;

        // 전화번호 유효성 검사 및 유효하지 않을 때 처리
        if (!regex.test(inputNumberValue)) {
            setNumberSearchResult("전화번호를 다시 확인해주세요.");
            return;
        }

        // TODO: 서버와 통신해서 전화번호 중복 검사

        // 전화번호가 중복되는 경우 처리
        if (inputNumberValue === "01066628752") {
            setNumberSearchResult("중복되는 번호입니다.");
            return;
        }

        // 전화번호가 중복되지 않는 경우 처리
        setNumberSearchResult(<span style={style1}>확인 되었습니다!</span>);
    }

    // 아이디 검사
    const handleIdSearch = () => {
        // 아이디 정규식 특수문자와 숫자를 하나씩은 반드시 포함
        const idRegex = /^[a-z0-9]{8,20}$/i;

        // 정규식 위반시 스타일
        const style2 = {
            color: "red",
            fontSize: "10px"
        }

        // 입력한 아이디가 정규식에 맞는지 확인
        if (!idRegex.test(inputIdValue)) {
            setIdSearchResult(<span style={style2}>8~20자의 영문 소문자, 숫자만 사용 가능합니다.</span>);
            return;
        }

        // TODO: 중복 검사 로직 추가 (여기서는 test 아이디가 중복됐다고 가정함)
        if (inputIdValue === "gksktl111") {
            setIdSearchResult("중복되는 아이디입니다.");
        } else {
            setIdSearchResult(<span style={style1}>확인 되었습니다!</span>);
        }
    }


    return (
        <div className="modal_background">
            <div className="sign_up_modal_container">
                {/*x 버튼*/}
                <div className="sign_up_modal_close"
                     onClick={() => dispatch(signUpModalOff())}>
                    <IoClose style={{fontSize: "20px"}}/>
                </div>
                {/*최상단 context*/}
                <div className="sign_up_modal_context">
                    계정을 생성하세요!
                </div>

                {/*e이메일 context*/}
                <div className="sign_up_modal_input_email_context">
                    이메일
                </div>
                
                {/*이메일 input*/}
                <input
                    className="sign_up_modal_input_email"
                    placeholder={"이메일"}
                    value={inputEmailValue}
                    // 인풋값 변경시 값 수정
                    onChange={(e) => {
                        setInputEmailValue(e.target.value);
                    }}
                    // 엔터 와 탭 으로 확인 가능
                    onKeyDown={(e) => {
                        if (e.key === 'Enter' || e.key === 'Tab') {
                            handleEmailSearch();
                        }
                    }}

                />

                {/*email 의 결과 출력*/}
                <div className="sign_up_modal_input_email_result">
                    {emailSearchResult}
                </div>

                {/*전화번호 context*/}
                <div className="sign_up_modal_input_number_context">
                    전화번호
                </div>
                
                {/*전화번호 input*/}
                <input
                    className="sign_up_modal_input_number"
                    placeholder={"전화번호"}
                    value={inputNumberValue}
                    // 인풋값 변경시 값 수정
                    onChange={(e) => {
                        setInputNumberValue(e.target.value);
                    }}
                    // 엔터와 탭으로 확인 가능
                    onKeyDown={(e) => {
                        if (e.key === 'Enter' || e.key === 'Tab') {
                            handleNumberSearch();
                        }
                    }}
                />

                {/*number 의 결과 출력*/}
                <div className="sign_up_modal_input_number_result">
                    {numberSearchResult}
                </div>

                {/*아이디 context*/}
                <div className="sign_up_modal_input_id_context">
                    아이디
                </div>
                
                {/*id input*/}
                <input
                    className="sign_up_modal_input_id"
                    placeholder={"아이디"}
                    value={inputIdValue}
                    // 인풋값 변경시 값 수정
                    onChange={(e) => {
                        setInputIdValue(e.target.value);
                    }}
                    // 엔터와 탭으로 확인 가능
                    onKeyDown={(e) => {
                        if (e.key === 'Enter' || e.key === 'Tab') {
                            handleIdSearch();
                        }
                    }}
                />

                {/*number 의 결과 출력*/}
                <div className="sign_up_modal_input_id_result">
                    {idSearchResult}
                </div>

                {/*가입하기 버튼*/}
                <button className="sign_up_modal_button"
                        onClick={handleEmailSearch}>
                    가입하기
                </button>
            </div>
        </div>
    );
};

export default SignUpModal;
