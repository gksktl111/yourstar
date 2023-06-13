import React, {useCallback, useEffect, useState} from 'react';
import './AddChatRoom.css';
import {useDispatch} from 'react-redux';
import {newChatModalOff} from '../../store/Store';
import axios from "axios";


const AddChatRoom = () => {
    const dispatch = useDispatch();
    const [searchText, setSearchText] = useState('');
    const [foundUsers, setFoundUsers] = useState([]);
    const [selectedUser, setSelectedUser] = useState('');
    const [followingUsers, setFollowingUsers] = useState([]);


    const handleModalOff = useCallback(
        (e) => {
            if (e.currentTarget !== e.target) {
                return;
            }
            dispatch(newChatModalOff());
        },
        [dispatch]
    );

    const handleSearchChange = (e) => {
        setSearchText(e.target.value);
        console.log(followingUsers);
        if (e.target.value) {
            const found = followingUsers.filter(user => user.id && user.id.toLowerCase().includes(e.target.value.toLowerCase()));
            setFoundUsers(found);
        } else {
            setFoundUsers([]);
        }
    };

    const handleUserSelect = (e) => {
        setSelectedUser(e.target.value);
        console.log(selectedUser);
    };

    // 팔로우 목록을 가져오는 함수
    const fetchFollowingUsers = async () => {
        await axios.post('/getfollowlist', {}, {
            headers: {authorization: localStorage.getItem("token")},
        }).then((response) => {
            console.log(response.data);

            // 일단 팔로우목록 중에 검사
            const users = response.data.map((user) => ({
                id: user.userId,
                name: user.name,
                image: user.image,
            }));

            setFollowingUsers(users);

        }).catch(function (error) {
            console.log("실패함", error);
        })
    };

    // 팔로우 목록을 가져오는 useEffect
    useEffect(() => {
        fetchFollowingUsers();
    }, []);


    // 채팅방 만들기
    const createChatRoom = async (otherUserId) => {
        console.log(otherUserId)
        try {
            const response = await axios.post("/makechatroom", {
                userId: otherUserId
            },{
                headers: {authorization: localStorage.getItem("token")},
            });
            if (response.data === "success") {
                alert("Chat room created successfully")
                console.log("Chat room created successfully");
                dispatch(newChatModalOff());
            } else {
                alert("Failed to create chat room")
                console.log("Failed to create chat room");
            }
        } catch (error) {
            console.error("Failed to create chat room:", error);
        }
    };


    return (
        <div className="add_room_background" onClick={handleModalOff}>
            <div className="add_room_container">
                <h3 className="add_room_top">새로운 메시지</h3>
                <div className="add_room_middle">
                    <h4>받는 사람 :</h4>
                    <input
                        placeholder={"아이디 검색..."}
                        value={searchText}
                        onChange={handleSearchChange}
                    />
                </div>
                {foundUsers.length === 0 && searchText.length > 0 && (
                    <div className="add_room_bottom">계정을 찾을 수 없습니다.</div>
                )}
                {foundUsers.length > 0 && (
                    <div className="add_room_found">
                        {foundUsers.map((user) => (
                            <div
                                style={{display: "flex"}}
                                key={user.id}>
                                <img
                                    src={`data:image/jpeg;base64,${user.image}`}
                                    alt={"asdd"}/>
                                <span>계정: {user.id}<br/>이름: {user.name}</span>
                                <input
                                    type="radio"
                                    className="user_radio"
                                    value={user.id}
                                    checked={selectedUser === user.id}
                                    onChange={handleUserSelect}
                                />
                            </div>
                        ))}
                    </div>
                )}
                <button className="add_room_btn"
                        disabled={foundUsers.length === 0}
                        onClick={() => createChatRoom(selectedUser)}>
                    채팅
                </button>
            </div>
        </div>
    );
};

export default AddChatRoom;
