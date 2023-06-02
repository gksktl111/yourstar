import React, {useEffect, useRef, useState} from 'react';
import Picker from "emoji-picker-react";
import './Message.css';
import {BsEmojiSmile} from "react-icons/bs";
import {SlNote} from "react-icons/sl";
import {FiMail} from "react-icons/fi";

const Message = () => {

    // 유저 관리 스테이트
    const [selectedUser, setSelectedUser] = useState(null);
    const [chatContent, setChatContent] = useState([]);

    // 메시지 저장 스테이트
    const [inputMessage, setInputMessage] = useState('');
    const [showEmojiPicker, setShowEmojiPicker] = useState(false);
    
    // 가상의 사용자 ID를 나타내는 상수를 추가합니다.
    const MY_USER_ID = 1;

    // 유저 관리
    const [users, setUsers] = useState([
        {id: 1, name: 'User1'},
        {id: 2, name: 'User2'},
        {id: 3, name: 'User3'},
        {id: 4, name: 'User4'},
        {id: 5, name: 'User5'},
        {id: 6, name: 'user6'},
        {id: 7, name: 'user7'},
        {id: 8, name: 'user8'},
        {id: 9, name: 'user9'},
        {id: 10, name: 'user10'}
    ]);

    // 가상의 채팅 데이터 예시입니다.
    const chatData = {
        1: [
            {content: "안녕하세요?", senderId: 2},
            {content: "반갑습니다!", senderId: 1},
            {content: "지금 뭐하시고 계신가요?", senderId: 2},
            {content: "지금 카페에서 작업중입니다 ㅜㅜ", senderId: 1},
            {content: "안녕하세요?", senderId: 2},
            {content: "반갑습니다!", senderId: 1},
            {content: "지금 뭐하시고 계신가요?", senderId: 2},
            {content: "지금 카페에서 작업중입니다 ㅜㅜ", senderId: 1},
            {content: "안녕하세요?", senderId: 2},
            {content: "반갑습니다!", senderId: 1},
            {content: "지금 뭐하시고 계신가요?", senderId: 2},
            {content: "지금 카페에서 작업중입니다 ㅜㅜ", senderId: 1},
            {content: "안녕하세요?", senderId: 2},
            {content: "반갑습니다!", senderId: 1},
            {content: "지금 뭐하시고 계신가요?", senderId: 2},
            {content: "마지막 글입니다", senderId: 1}
        ],
        2: [
            {content: "오늘 날씨가 좋네요.", senderId: 2},
            {content: "맞아요, 날씨가 정말 좋아요.", senderId: 1}
        ],
        3: [
            {content: "여행 가고 싶어요.", senderId: 3},
            {content: "저도 같이 가고 싶어요.", senderId: 1}
        ],
        4: [
            {content: "프로젝트 진행 상황 어떻게 되세요?", senderId: 4},
            {content: "잘 진행되고 있어요!", senderId: 1}
        ],
        5: [
            {content: "주말에 뭐 하실 예정이세요?", senderId: 5},
            {content: "영화를 보러 가려고요.", senderId: 1}
        ],
        6: [
            {content: "최근에 어떤 책 읽었어요?", senderId: 6},
            {content: "미래를 미리 보는 기술이라는 책을 읽었습니다.", senderId: 1}
        ],
        7: [
            {content: "커피 좋아하세요?", senderId: 7},
            {content: "네, 좋아합니다. 라떼를 자주 마셔요.", senderId: 1}
        ],
        8: [
            {content: "운동하시나요?", senderId: 8},
            {content: "조깅과 헬스를 즐겨합니다.", senderId: 1}
        ],
        9: [
            {content: "요즘 들어 노래 중에 좋아하는 곡 있어요?", senderId: 9},
            {content: "아이유의 '라일락' 곡이 좋아요.", senderId: 1}
        ],
        10: [
            {content: "드라마 볼만한 추천 드라마 있나요?", senderId: 10},
            {content: "오징어게임 추천합니다.", senderId: 1}
        ]
    };

    // 채팅창 포커스 관리
    const messagesEndRef = useRef(null);

    useEffect(() => {
        if (selectedUser) {
            setChatContent(chatData[selectedUser]);
        }
    }, [selectedUser]);

    // 메시지 입력 핸들러
    const handleInput = (e) => {
        setInputMessage(e.target.value);
    };

    // 메시지 전송 핸들러
    const sendMessage = (e) => {
        e.preventDefault();
        if (inputMessage.trim() === '' || !selectedUser) return;

        const newMessage = {
            content: inputMessage,
            senderId: MY_USER_ID,
        };

        // 선택된 사용자와의 대화 내용 업데이트
        setChatContent([...chatContent, newMessage]);

        setInputMessage('');
    };

    // 현재 선택된 사용자 정보 받아오기
    const getUserInfo = (userId) => {
        if (userId) {
            const user = users.find(user => user.id === userId);
            return user;
        }
        return null;
    };

    // 현재 선택된 사용자 이미지 경로 받아오기
    const getProfileImgPath = (userId) => {
        if (userId) {
            return `/assets/img/${userId}.jpg`;
        }
        return null;
    };

    const currentSelectedUserInfo = getUserInfo(selectedUser);
    const currentSelectedUserProfilePath = getProfileImgPath(selectedUser);

    // 채팅창 포커스 관리
    const scrollToBottom = () => {
        messagesEndRef.current?.scrollIntoView({behavior: 'smooth'});
    };

    useEffect(() => {
        scrollToBottom();
    }, [chatContent]);

    // 이모티콘 선택기를 토글하는 함수
    const toggleEmojiPicker = () => {
        setShowEmojiPicker(!showEmojiPicker);
    };

    // 이모티콘을 텍스트 에어리어에 삽입하는 함수
    const addEmoji = (e, emojiObject) => {
        // 여기에 이전 내용 대신 객체에서 'emoji' 속성을 사용해야 합니다.
        setInputMessage((prevMessage) => prevMessage + e.emoji);
    };

    // 이모티콘 창 말고 다른곳 누르면 없어지기
    const useOutsideClick = (ref, callback) => {
        const handleClick = (e) => {
            if (ref.current && !ref.current.contains(e.target)) {
                callback();
            }
        };

        useEffect(() => {
            document.addEventListener("mousedown", handleClick);

            return () => {
                document.removeEventListener("mousedown", handleClick);
            };
        });
    };

    const emojiPickerRef = useRef();
    useOutsideClick(emojiPickerRef, () => {
        setShowEmojiPicker(false);
    });


    return (
        <div className="message-layout">
            <div className="messages">
                <div className="message-card">
                    {/* 회원 목록 및 검색바 컴포넌트를 추가하세요 */}
                    <div className='message-card-header1'>
                        <span className='my-name'>
                            als_rb_1214
                        </span>
                        {/*여기서 채팅창 추가하기*/}
                        <SlNote style={{ fontSize : "25px" , marginLeft : "150px", cursor : "pointer"}}/>
                        <br/>
                    </div>
                    <span style={{fontSize: '15px', fontWeight: "bold"}}>
                        메시지
                    </span>
                    <div className="user-list">
                        {users.map((user) => (
                            <div key={user.id}
                                 className="user-item"
                                 onClick={() => setSelectedUser(user.id)}>
                                <div className="list-profile-image">
                                    <img
                                        src={`/assets/img/${user.id}.jpg`}
                                        alt="Profile"
                                        className="list-profile-picture"
                                    />
                                </div>
                                <div className="list-user-name">{user.name}</div>
                            </div>
                        ))}
                    </div>
                </div>
                {selectedUser ? <div className="message-wrapper">
                    <div className="message-user">
                        <div className="user-profile-image">
                            <img
                                src={currentSelectedUserProfilePath}
                                alt="Profile"
                                className="profile-picture"
                            />
                        </div>
                        <div className="user-info">
                            <div className="username">
                                {currentSelectedUserInfo ? currentSelectedUserInfo.name : '대화 상대방 이름'}
                            </div>
                            <div className="user-status">Active now</div>
                        </div>
                    </div>
                    <div className="message-content">
                        {chatContent.map((message, index) => {
                            // 자신의 말풍선인지 확인하고 클래스를 적용합니다.
                            const isFromMyself = message.senderId === MY_USER_ID;
                            return (
                                <div
                                    key={index}
                                    className={`message-bubble ${
                                        isFromMyself ? "myself" : "other-user"
                                    }`}
                                >
                                    {message.content}
                                </div>
                            );
                        })}
                        <div ref={messagesEndRef}></div>
                    </div>

                    <div className="message-input">
                        <button type="button" onClick={toggleEmojiPicker}>
                            <BsEmojiSmile style={{fontSize : "30px", marginRight : "20px"}}/>
                        </button>
                        {showEmojiPicker && (
                            <div ref={emojiPickerRef} className="emoji-picker">
                                <Picker onEmojiClick={addEmoji} />
                            </div>
                        )}
                        <textarea
                            placeholder="Send message..."
                            value={inputMessage}
                            onChange={handleInput}
                            onKeyDown={(e) => {
                                if (e.key === 'Enter') {
                                    sendMessage(e);
                                }
                            }}
                            autoComplete="off"
                        />
                        {/* 전송 버튼 컴포넌트를 추가하세요 */}
                        <button
                            type="button"
                            onClick={sendMessage}
                            className="send-button"
                        >
                            전송
                        </button>
                    </div>
                </div> : <div className={"no-user-selected"}>
                    메시지를 보낼 사람을 선택해 주세요!
                </div>}
            </div>
        </div>
    );
};

export default Message;
