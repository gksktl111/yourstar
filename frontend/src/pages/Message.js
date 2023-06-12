import React, {useEffect, useRef, useState} from 'react';
import Picker from "emoji-picker-react";
import './Message.css';
import {BsEmojiSmile} from "react-icons/bs";
import {SlNote} from "react-icons/sl";
import {useDispatch, useSelector} from "react-redux";
import {newChatModalOn} from "../store/Store";
import AddChatRoom from "../components/message/AddChatRoom";
import axios from "axios";
// import SockJS from 'sockjs-client';
// import { Client } from 'stompjs';

const Message = () => {
    let state = useSelector((state) => state);
    let dispatch = useDispatch();

    // 유저 관리 스테이트
    const [selectedUser, setSelectedUser] = useState(null);
    const [chatContent, setChatContent] = useState([]);

    // 메시지 저장 스테이트
    const [inputMessage, setInputMessage] = useState('');
    const [showEmojiPicker, setShowEmojiPicker] = useState(false);

    // 가상의 사용자 ID를 나타내는 상수를 추가합니다.
    const [myId,setMyId] = useState('');

    const loadMyId = async () => {
        await axios.post('/user/myid', {},{
            headers: {authorization: localStorage.getItem("token")},
        }).then((response) => {
            console.log(response.data);

            setMyId(response.data)
        }).catch(function (error) {
            console.log("실패함", error);
        })
    };

    // 채팅방 목록
    const [users, setUsers] = useState([]);

    // 채팅내역
    const [chatData, setChatData] = useState([]);

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
            senderId: myId,
        };

        // 선택된 사용자와의 대화 내용 업데이트
        setChatContent([...chatContent, newMessage]);

        setInputMessage('');
    };

    // 현재 선택된 사용자 정보 받아오기
    const getUserInfo = (user) => {
        if (user) {
            const userName = user.name;
            return userName;
        }
        return null;
    };

    // 현재 선택된 사용자 이미지 경로 받아오기
    const getProfileImgPath = (user) => {
        if (user) {
            return `data:image/jpeg;base64,${user.image}`;
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

    // 내 채팅방 불러오기
    const loadChatRoom = async () => {
        await axios.post('/getchatroom', {}, {
            headers: {authorization: localStorage.getItem("token")},
        }).then((response) => {
            console.log(response.data);

            // 일단 팔로우목록 중에 검사
            const users = response.data.map((user) => ({
                id: user.userId,
                name: user.name,
                image: user.image,
            }));

            setUsers(users);
        }).catch(function (error) {
            console.log("실패함", error);
        })
    };

    // 내 채팅내용 불러오기
    const loadChatData = async (otherUser) => {
        setSelectedUser(otherUser);

        await axios
            .post(
                "/past-messages",
                {
                    userId: otherUser.id,
                },
                {
                    headers: {authorization: localStorage.getItem("token")},
                }
            )
            .then((response) => {
                console.log(response.data);

                const chats = response.data.map((chat) => ({
                    id: chat.sender,
                    content: chat.content,
                    time: chat.sentAt,
                }));

                console.log(chats);

                setChatData(chats);
            })
            .catch(function (error) {
                console.log("실패함", error);
            });
    };

    //
    // // 채팅 보내기
    // const subscribeQueue = () => {
    //     // YOUR_USERNAME에 해당하는 값을 설정하세요.
    //     const YOUR_USERNAME = 'als_rb_1214';
    //
    //     // 서버와 웹소켓 연결을 시작하고 주소를 구독하여 메시지 받기
    //     let socket = new SockJS('/websocket');
    //     let stompClient = Client.over(socket);
    //
    //     stompClient.connect({}, function () {
    //         stompClient.subscribe('/queue/messages/' + YOUR_USERNAME, function (message) {
    //             if (message.body) {
    //                 const receivedMessage = JSON.parse(message.body);
    //                 setChatContent((prevChatContent) => [...prevChatContent, receivedMessage]);
    //             }
    //         });
    //     });
    // }

    // useEffect(() => {
    //     subscribeQueue();
    // }, []);
    
    // 프로필 데이터들을 호출하는 useEffect
    useEffect(() => {
        loadChatRoom();
        loadMyId();
    }, []);

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
                        <SlNote
                            onClick={() => {
                                dispatch(newChatModalOn());
                            }}
                            style={{
                                fontSize: "25px",
                                marginLeft: "150px",
                                cursor: "pointer",
                            }}
                        />
                        <br/>
                    </div>
                    <span style={{fontSize: '15px', fontWeight: "bold"}}>
                        메시지
                    </span>
                    <div className="user-list">
                        {users.map((user) => (
                            <div key={user.id}
                                 className="user-item"
                                 onClick={() => loadChatData(user)}>
                                <div className="list-profile-image">
                                    <img
                                        src={`data:image/jpeg;base64,${user.image}`}
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
                                {currentSelectedUserInfo ? currentSelectedUserInfo : '대화 상대방 이름'}
                            </div>
                            <div className="user-status">Active now</div>
                        </div>
                    </div>
                    <div className="message-content">
                        {chatContent.map((message, index) => {
                            // 자신의 말풍선인지 확인하고 클래스를 적용합니다.
                            const isFromMyself = (message.senderId === myId);
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
                            <BsEmojiSmile style={{fontSize: "30px", marginRight: "20px"}}/>
                        </button>
                        {showEmojiPicker && (
                            <div ref={emojiPickerRef} className="emoji-picker">
                                <Picker onEmojiClick={addEmoji}/>
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
            {state.isNewChatModalOpen === true ? <AddChatRoom/> : null}
        </div>
    );
};

export default Message;