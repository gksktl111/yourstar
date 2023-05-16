import React from "react";
import Sidebar from "./components/Sidebar";
import {Route, Routes, useLocation} from "react-router-dom";
import Home from "./pages/Home";
import Profile from "./pages/Profile";
import Mail from "./pages/Mail";
import PlayList from "./pages/PlayList";
import Setting from "./pages/Setting";
import LoginPage from "./pages/LoginPage";
import PrivateRoutes from "./utils/PrivateRoutes";
import MusicPlayer from "./components/MusicPlayer";

function App() {

    const location = useLocation();

    // 로그인 페이지에서는 사이드바를 렌더링하지 않습니다.
    if (location.pathname === '/login') {
        return (
            <Routes>
                <Route path="/login" element={<LoginPage/>}/>
            </Routes>
        );
    }

    return (
        <div>
            <Sidebar>
                <Routes>
                    <Route element={<PrivateRoutes/>}>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/profile" element={<Profile/>}/>
                        <Route path="/mail" element={<Mail/>}/>
                        <Route path="/playList" element={<PlayList/>}/>
                        <Route path="/setting" element={<Setting/>}/>
                    </Route>
                    <Route path="/login" element={<LoginPage/>}/>
                </Routes>
            </Sidebar>
            <MusicPlayer/>
        </div>
    )
}

export default App;