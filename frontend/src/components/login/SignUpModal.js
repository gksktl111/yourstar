import React, {useState} from 'react';
import './SignUpModal.css';
import {IoClose} from "react-icons/io5";
import {useDispatch} from "react-redux";
import {signUpModalOff} from "../../store/Store";
import axios from "axios";

const SignUpModal = () => {
    const dispatch = useDispatch();

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
    const [idSearchResult, setIdSearchResult] = useState('');

    // pw input 값
    const [inputPwValue, setInputPwValue] = useState('');
    // pwCheckResult 값
    const [pwCheckResult, setPwCheckResult] = useState('');
    // pw double input 값
    const [inputPwDoubleValue, setInputPwDoubleValue] = useState('');
    // pwDoubleCheckResult 값
    const [pwDoubleCheckResult, setPwDoubleCheckResult] = useState('');

    // name input 값
    const [inputNameValue, setInputNameValue] = useState('');
    // nameCheckResult 값
    const [nameCheckResult, setNameCheckResult] = useState('');

    // gender select 값
    const [selectGenderValue, setSelectGenderValue] = useState('');
    // genderCheckResult 값
    const [genderCheckResult, setGenderCheckResult] = useState('');


    // age input 값
    const [inputAgeValue, setInputAgeValue] = useState('');
    // ageCheckResult 값
    const [ageCheckResult, setAgeCheckResult] = useState('');


    // 이메일 정규식 위반시 스타일
    const style2 = {
        color: "red",
        fontSize: "10px"
    }

    // 이메일 검사
    const handleEmailSearch = async () => {
        // 이메일 정규식 5~20자의 영문 소문자, 숫자와 특수기호(_),(-)
        const emailRegexp = /^[a-z0-9_-]{5,20}(\.[a-z0-9_-]+)*@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/;

        // 이메일 검사
        // 이메일 형식이 맞지 않으면 오류 메시지 출력 후 함수 종료
        if (!emailRegexp.test(inputEmailValue)) {
            setEmailSearchResult(<span style={style2}>5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.</span>);
            return;
        }

        const domainRegexp = /(naver|gmail|daum|hotmail)\.(com|kr)/;

        // 도메인 형식이 맞지 않으면 오류 메시지 출력 후 함수 종료
        if (!domainRegexp.test(inputEmailValue.split('@')[1])) {
            setEmailSearchResult(<span style={style2}>잘못된 도메인 형식입니다.</span>);
            return;
        }

        // 서버와 통신해서 이메일 중복 검사 진행
        emailCheck();
    };

    const emailCheck = async () => {
        // 여기 바꿔야됨
        await axios.post('/user/findid', {
            email: inputEmailValue,
        }).then((response) => {
            if (response.data === "success") {
                setEmailSearchResult("확인 되었습니다!");
            } else {
                setEmailSearchResult(<span style={style2}>회원정보가 없습니다.</span>);
            }
        }).catch(function (error) {
            console.log('실패함', error)
        })
    }


    // 전화번호 검사
    const handleNumberSearch = () => {
        const regex = /^010\d{4}\d{4}$/;

        const style2 = {
            color: "red",
        }

        // 전화번호 유효성 검사 및 유효하지 않을 때 처리
        if (!regex.test(inputNumberValue)) {
            setNumberSearchResult(<span style={style2}>전화번호를 다시 확인해주세요.</span>);
            return;
        }

        // TODO: 서버와 통신해서 전화번호 중복 검사

        // 전화번호가 중복되는 경우 처리
        if (inputNumberValue === "01066628752") {
            setNumberSearchResult(<span style={style2}>중복되는 번호 입니다.</span>);
            return;
        }

        // 전화번호가 중복되지 않는 경우 처리
        setNumberSearchResult("확인 되었습니다!");
    }

    // 아이디 검사
    const handleIdSearch = () => {
        // 8~20자의 영문 소문자, 숫자만 사용 가능합니다.
        const idRegex = /^[a-z0-9]{8,20}$/;

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
            // 여기서 서버로 보내기
            setIdSearchResult("확인 되었습니다!");
        }
    }

    // 비밀번호 확인
    const handlePwCheck = () => {
        // 정규식 비밀번호는 8~20자의 영문자, 숫자, 특수문자를 포함
        const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,20}).*$/;

        // 정규식 위반시 스타일
        const style2 = {
            color: "red",
            fontSize: "10px"
        }

        if (passwordRegex.test(inputPwValue)) {
            setPwCheckResult("확인 되었습니다!")
        } else {
            setPwCheckResult(<span style={style2}>8~20자의 영문자, 숫자, 특수문자를 포함해야 사용 가능합니다.</span>)
        }
    }

    // 비밀번호 재확인
    const handlePwDoubleCheck = () => {

        // 비밀번호가 다를시 스타일
        const style2 = {
            color: "red",
        }

        // 비밀번호 재확인
        if (inputPwValue === inputPwDoubleValue && inputPwValue !== '') {
            // 여기서 서버로 보내기
            setPwDoubleCheckResult("확인 되었습니다!")
        } else {
            setPwDoubleCheckResult(<span style={style2}>비밀번호를 확인해 주세요.</span>)
        }
    }

    // 이름 확인
    const handleNameCheck = () => {
        // 5~20자의 영문 대 소문자, 숫자, 언더바만 사용 가능합니다.
        const Regex = /^[\w]{5,20}$/;

        // 정규식 위반시 스타일
        const style2 = {
            color: "red",
            fontSize: "10px"
        }

        if (Regex.test(inputNameValue)) {
            setNameCheckResult('확인 되었습니다!');
        } else {
            setNameCheckResult(<span style={style2}>5~20자의 영문 대 소문자, 숫자, ( _ )만 사용 가능합니다.</span>);
        }
    };

    // 성별 확인
    const handleGenderCheck = () => {

        if (selectGenderValue === "man" || selectGenderValue === "woman") {
            setGenderCheckResult("확인 되었습니다!")
        } else {
            setGenderCheckResult("")
        }
    }

    // 나이 확인
    const handleAgeCheck = () => {
        // 나이는 범위 정하기

        if (inputAgeValue === "") {
            setAgeCheckResult(<span style={{color: "red"}}>나이를 확인해주세요.</span>)
            return;
        }

        if (inputAgeValue >= 8 && inputAgeValue <= 110) {
            setAgeCheckResult("확인 되었습니다!")
        } else {
            setAgeCheckResult(<span style={{color: "red"}}>정말이세요?</span>)
        }
    }

    // 올클리어시 서버로 전달 가능
    // 입력창을 다 입력했는지 검사
    const handleContextCheck = () => {
        if (
            emailSearchResult === "확인 되었습니다!" &&
            numberSearchResult === "확인 되었습니다!" &&
            idSearchResult === "확인 되었습니다!" &&
            pwCheckResult === "확인 되었습니다!" &&
            pwDoubleCheckResult === "확인 되었습니다!" &&
            nameCheckResult === "확인 되었습니다!" &&
            genderCheckResult === "확인 되었습니다!" &&
            ageCheckResult === "확인 되었습니다!"
        ) {
            // 다입력했으면 true 리턴
            return true;
        }
    }

    // 로그인 검사
    const signUpCheck = async () => {
        await axios.post('/user/signup', {
            // 여기서 회원 정보 넘겨줘야함
            // id: inputId,
            // pw: inputPw
        }).then((response) => {

            // 응답이 success면 축하메시지 띄운후 가입 창 닫기
            if (response.data === "success") {
                alert("회원 가입을 축하합니다!")
                dispatch(signUpModalOff())
            } else {
                // 실패하면 오류 메시지
                alert('오류')
            }
        }).catch(function () {
            console.log('실패함')
        })
    }

    // 이메일 인증 만들기

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
                    placeholder={"이메일을 입력해 주세요"}
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
                    placeholder={"전화번호를 입력해 주세요"}
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
                    placeholder={"아이디를 입력해 주세요"}
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

                {/*id 의 결과 출력*/}
                <div className="sign_up_modal_input_id_result">
                    {idSearchResult}
                </div>

                {/*비밀번호 context*/}
                <div className="sign_up_modal_input_pw_check_context">
                    비밀번호
                </div>

                {/*pw input*/}
                <input
                    className="sign_up_modal_input_pw_check"
                    type="password"
                    placeholder={"비밀번호를 입력해 주세요"}
                    value={inputPwValue}
                    // 인풋값 변경시 값 수정
                    onChange={(e) => {
                        setInputPwValue(e.target.value);
                    }}
                    // 엔터와 탭으로 확인 가능
                    onKeyDown={(e) => {
                        if (e.key === 'Enter' || e.key === 'Tab') {
                            handlePwCheck();
                        }
                    }}
                />

                {/*pw 의 결과 출력*/}
                <div className="sign_up_modal_input_pw_check_result">
                    {pwCheckResult}
                </div>

                {/*비밀번호 재확인 context*/}
                <div className="sign_up_modal_input_pw_double_check_context">
                    비밀번호 재확인
                </div>

                {/*비밀번호 재확인 input*/}
                <input
                    className="sign_up_modal_input_pw_double_check"
                    type="password"
                    placeholder={"비밀번호를 확인해 주세요"}
                    value={inputPwDoubleValue}
                    // 인풋값 변경시 값 수정
                    onChange={(e) => {
                        setInputPwDoubleValue(e.target.value);
                    }}
                    // 엔터와 탭으로 확인 가능
                    onKeyDown={(e) => {
                        if (e.key === 'Enter' || e.key === 'Tab') {
                            handlePwDoubleCheck();
                        }
                    }}
                />

                {/*비밀번호 재확인 결과 출력*/}
                <div className="sign_up_modal_input_pw_double_check_result">
                    {pwDoubleCheckResult}
                </div>

                {/*이름 context*/}
                <div className="sign_up_modal_input_name_context">
                    이름
                </div>

                {/*이름 input*/}
                <input
                    className="sign_up_modal_input_name"
                    placeholder={"이름을 적어주세요"}
                    value={inputNameValue}
                    // 인풋값 변경시 값 수정
                    onChange={(e) => {
                        setInputNameValue(e.target.value);
                        // console.log(inputNameValue);
                    }}
                    // 엔터와 탭으로 확인 가능
                    // 서버로 보내기
                    onKeyDown={(e) => {
                        if (e.key === 'Enter' || e.key === 'Tab') {
                            handleNameCheck();
                        }
                    }}
                />

                {/*이름결과 출력*/}
                <div className="sign_up_modal_input_name_result">
                    {nameCheckResult}
                </div>

                {/*성별 context*/}
                <div className="sign_up_modal_select_gender_context">
                    성별
                </div>

                {/*성별 select*/}
                <select
                    className="sign_up_modal_select_gender"
                    value={selectGenderValue}
                    // 인풋값 변경시 값 수정
                    // 셀렉트는 값이 변하면
                    onChange={(e) => {
                        setSelectGenderValue(e.target.value);
                    }}
                    onClick={() => {
                        handleGenderCheck();
                    }}
                >
                    {/*select 옵션*/}
                    <option value="">성별을 선택하세요</option>
                    <option value="man">남자</option>
                    <option value="woman">여자</option>
                </select>

                {/*성별 결과 출력*/}
                <div className="sign_up_modal_input_gender_result">
                    {genderCheckResult}
                </div>

                {/*나이 context*/}
                <div className="sign_up_modal_input_age_context">
                    나이
                </div>

                {/*나이 input*/}
                <input
                    className="sign_up_modal_input_age"
                    type={"number"}
                    placeholder={"나이를 적어주세요"}
                    value={inputAgeValue}
                    // 인풋값 변경시 값 수정
                    onChange={(e) => {
                        setInputAgeValue(e.target.value);
                        // console.log(inputNameValue);
                    }}
                    // 엔터와 탭으로 확인 가능
                    // 서버로 보내기
                    onKeyDown={(e) => {
                        if (e.key === 'Enter' || e.key === 'Tab') {
                            handleAgeCheck();
                        }
                    }}
                />

                {/*나이결과 출력*/}
                <div className="sign_up_modal_input_age_result">
                    {ageCheckResult}
                </div>

                {/*가입하기 버튼*/}

                <button
                    className="sign_up_modal_button"
                    onClick={() => {
                        if (handleContextCheck() === true) {
                            // 아직 서버랑 연결 안됨
                            signUpCheck()
                        } else {
                            alert('입력정보를 다시 확인해주세요')
                        }

                    }
                    }>
                    회원가입
                </button>

                <div className='sign_up_modal_footer'/>
            </div>
        </div>
    );
};

export default SignUpModal;
