import React, { useCallback, useState } from 'react';
import './AddChatRoom.css';
import { useDispatch } from 'react-redux';
import { newChatModalOff } from '../../store/Store';

// 일단 팔로워중에 검사
const dummyUsers = [
    { id: 'user1', name: '홍길동' },
    { id: 'user2', name: '이순신' },
    { id: 'user3', name: '유순' },
    { id: 'user4', name: '홍길동' },
    { id: 'user5', name: '이순신' },
    { id: 'user6', name: '유순' },
    { id: 'user7', name: '유순' },
    { id: 'user8', name: '홍길동' },
    { id: 'user9', name: '이순신' },
    { id: 'user10', name: '유순' },
    { id: 'user11', name: '홍길동' },
    { id: 'user12', name: '이순신' },
    { id: 'user13', name: '유순' },
    { id: 'aser4', name: '홍길동' },
    { id: 'aser5', name: '이순신' },
    { id: 'aser6', name: '순' },
    { id: 'iser7', name: '홍길동' },
    { id: 'iser8', name: '이순신' },
    { id: 'iser9', name: '유순' }
];

const AddChatRoom = () => {
    const dispatch = useDispatch();
    const [searchText, setSearchText] = useState('');
    const [foundUsers, setFoundUsers] = useState([]);
    const [selectedUser, setSelectedUser] = useState('');

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
        if (e.target.value) {
            const found = dummyUsers.filter((user) => user.id.includes(e.target.value));
            setFoundUsers(found);
        } else {
            setFoundUsers([]);
        }
    };

    const handleUserSelect = (e) => {
        setSelectedUser(e.target.value);
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
                                style = {{display : "flex"}}
                                key={user.id}>
                                <img
                                     src={"/assets/img/3.jpg"}
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
                <button className="add_room_btn" disabled={foundUsers.length === 0}>
                    채팅
                </button>
            </div>
        </div>
    );
};

export default AddChatRoom;
