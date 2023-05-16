import React, {useEffect, useState} from "react";

function TestApi() {
    const [msg, setMsg] = useState([]);
    useEffect(() => {
        fetch("/profile")
            .then((res) => {return res.json();})
            .then((data) => {setMsg(data);})
    }, []);
    return (
        <div className="App">
            <header className="App-header">
                <ul>
                    {msg.map((content, idx) => <li key={`${idx} - ${content}`}>{content}</li>)}
                </ul>
            </header>
        </div>
    );
}

export default TestApi;