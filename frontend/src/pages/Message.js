import React, {useEffect, useRef, useState} from "react";
import {io} from "socket.io-client";
import Picker from "emoji-picker-react";
import "./Message.css";
import {BsEmojiSmile} from "react-icons/bs";
import {SlNote} from "react-icons/sl";
import {ImExit} from "react-icons/im";
import {useDispatch, useSelector} from "react-redux";
import AddChatRoom from "../components/message/AddChatRoom";
import {newChatModalOn} from "../store/Store";
import axios from "axios";

const Message = () => {
    let state = useSelector((state) => state);
    let dispatch = useDispatch();

    // 유저 관리 스테이트
    const [selectedUser, setSelectedUser] = useState(null);
    const [chatContent, setChatContent] = useState([]);

    // 메시지 저장 스테이트
    const [inputMessage, setInputMessage] = useState("");
    const [showEmojiPicker, setShowEmojiPicker] = useState(false);

    // 현재 사용자 ID를 나타내는 상수를 추가합니다.
    const MY_USER_ID = localStorage.getItem("token");

    // 유저 관리
    const [users, setUsers] = useState([
        {id: 1, name: "User1"},
        {id: 2, name: "User2"},
        {id: 3, name: "User3"},
        {id: 4, name: "User4"},
        {id: 5, name: "User5"},
        {id: 6, name: "user6"},
        {id: 7, name: "user7"},
        {id: 8, name: "user8"},
        {id: 9, name: "user9"},
        {id: 10, name: "user10"},
    ]);

    // 채팅창 포커스 관리
    const messagesEndRef = useRef(null);

    useEffect(() => {
        if (selectedUser) {
            setChatContent(chatContent[selectedUser]);
        }
    }, [selectedUser]);

    // 메시지 입력 핸들러
    const handleInput = (e) => {
        setInputMessage(e.target.value);
    };

    // 메시지 보내기
    const sendMessage = (e) => {
        e.preventDefault();

        if (inputMessage.trim() === "" || !selectedUser || !socket) return;

        const newMessage = {
            content: inputMessage,
            receiver: selectedUser,
        };

        // 서버로 메시지 전송
        socket.emit("chat", selectedUser, newMessage);

        // 프론트엔드에서 내가 전송한 메시지 처리
        setChatContent((prevContent) => [...prevContent, newMessage]);

        setInputMessage("");
    };

    // 현재 선택된 사용자 정보 받아오기
    const getUserInfo = (userId) => {
        if (userId) {
            const user = users.find((user) => user.id === userId);
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
        messagesEndRef.current?.scrollIntoView({behavior: "smooth"});
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

    // 이모티콘 창 말고 다른곳 누르면 없어지기 "
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

    const exitCheck = () => {
        const result = window.confirm("채팅방을 나가시겠습니까?");

        if (!result) {
            return;
        }

        alert("채팅방을 나갑니다.");
    }
    // Socket.io 관련 코드 추가
    const [socket, setSocket] = useState();

    useEffect(() => {
        const newSocket = io("http://localhost:8080/chat", {
            transports: ["websocket"],
        });

        setSocket(newSocket);

        return () => {
            newSocket.close();
        };
    }, []);

    useEffect(() => {
        if (socket) {
            socket.on("message", (message) => {
                // 받은 메시지 처리...
                setChatContent((prevContent) => [...prevContent, message]);
            });

            return () => {
                socket.off("message");
            };
        }
    }, [socket])

    // 과거 메시지 가져오기
    const fetchPastMessages = async (otherUserId) => {
        try {
            const response = await axios.post("/past-messages", {userId: otherUserId});
            setChatContent(response.data);
        } catch (error) {
            console.error("Failed to fetch past messages:", error);
        }
    };

    return (
        <div className="message-layout">
            <div className="messages">
                <div className="message-card">
                    {/* 회원 목록 및 검색바 컴포넌트를 추가하세요 */}
                    <div className="message-card-header1">
                        <span className="my-name">als_rb_1214</span>
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
                    <span style={{fontSize: "15px", fontWeight: "bold"}}>메시지</span>
                    <div className="user-list">
                        {users.map((user) => (
                            <div
                                key={user.id}
                                className="user-item"
                                onClick={() => {
                                    // Fetch past messages when selecting user
                                    fetchPastMessages(user.id);
                                    setSelectedUser(user.id);
                                }}
                            >
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
                {selectedUser ? (
                    <div className="message-wrapper">
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
                                    {currentSelectedUserInfo
                                        ? currentSelectedUserInfo.name
                                        : "대화 상대방 이름"}
                                </div>
                            </div>
                            <div className="user-exit" onClick={exitCheck}>
                                <ImExit/>
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
                                <BsEmojiSmile
                                    style={{fontSize: "30px", marginRight: "20px"}}
                                />
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
                                    if (e.key === "Enter") {
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
                    </div>
                ) : (
                    <div className={"no-user-selected"}>
                        메시지를 보낼 사람을 선택해 주세요!
                    </div>
                )}
            </div>
            {state.isNewChatModalOpen === true ? <AddChatRoom/> : null}
        </div>
    );
};

export default Message;
