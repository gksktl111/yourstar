import React from "react";
import Sidebar from "./components/Sidebar";
import {Route, Routes, useLocation} from "react-router-dom";
import Home from "./pages/Home";
import Profile from "./pages/Profile";
import Message from "./pages/Message";
import PlayList from "./pages/PlayList";
import Setting from "./pages/Setting";
import LoginPage from "./pages/LoginPage";
import PrivateRoutes from "./utils/PrivateRoutes";
import MusicPlayer from "./components/MusicPlayer";
import SearchResults from "./components/PlayList/SearchResults";

function App() {
    const location = useLocation();

    // 로그인 페이지에서는 사이드바와 뮤직 플레이어를 렌더링하지 않습니다.
    if (location.pathname === "/login") {
        return (
            <Routes>
                <Route path="/login" element={<LoginPage />} />
            </Routes>
        );
    }

    return (
        <>
            <Sidebar>
                <Routes>
                    <Route path="/login" element={<LoginPage/>}/>

                    <Route path={"/"} element={<PrivateRoutes/>}>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/profile" element={<Profile/>}/>
                        <Route path="/message" element={<Message/>}/>
                        <Route path="/playList" element={<PlayList/>}/>
                        <Route path="/setting" element={<Setting/>}/>
                    </Route>

                    <Route path="/search" element={<SearchResults/>} />
                </Routes>
            </Sidebar>
            <MusicPlayer/>
            <Routes>
                <Route path = "/admin"></Route>
            </Routes>
        </>
    )
}

export default App;