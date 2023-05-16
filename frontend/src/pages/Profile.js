import React, {useEffect, useState} from 'react';

const Profile = () => {
    const [msg, setMsg] = useState([]);
    useEffect(() => {
        fetch("/profile")
            .then((res) => {return res.json();})
            .then((data) => {setMsg(data);})
    }, []);
    return (
        <div>
            <h1>프로필 페이지</h1>
            <header className="App-header">
                <ul>
                    {msg.map((content, idx) => <li key={`${idx} - ${content}`}>{content}</li>)}
                </ul>
            </header>
        </div>
    );
};

export default Profile;