import React from "react";
import Sidebar from "./components/Sidebar";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./pages/Home";
import Profile from "./pages/Profile";
import Mail from "./pages/Mail";
import PlayList from "./pages/PlayList";
import Setting from "./pages/Setting";

function App() {

    return (
        <>
            <Sidebar>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/home" element={<Home/>}/>
                    <Route path="/profile" element={<Profile/>}/>
                    <Route path="/mail" element={<Mail/>}/>
                    <Route path="/playList" element={<PlayList/>}/>
                    <Route path="/setting" element={<Setting/>}/>
                </Routes>
            </Sidebar>
        </>
    )
}

export default App;