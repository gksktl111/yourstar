import React from "react";
import Sidebar from "./components/ex/Sidebar";
import {Route, Routes, useLocation} from "react-router-dom";
import Home from "./pages/Home";
import Profile from "./pages/Profile";
import Message from "./pages/Message";
import PlayList from "./pages/PlayList";
import Setting from "./pages/Setting";
import LoginPage from "./pages/LoginPage";
import PrivateRoutes from "./utils/PrivateRoutes";
import MusicPlayer from "./components/ex/MusicPlayer";
import SearchResults from "./components/PlayList/SearchResults";
import AdminRoutes from "./utils/AdminRoutes";
import NewPeed from "./components/NewPeed/NewPeed"
import NewProfile from "./components/profile/NewProfile";

function App() {
    const location = useLocation();

    // 해당페이지에서는 사이드바와 뮤직 플레이어를 렌더링하지 않습니다.
    if (location.pathname === "/login") {
        return (
            <Routes>
                <Route path="/login" element={<LoginPage/>}/>
            </Routes>
        );
    } else if (location.pathname === "/admin") {
        return (
            <Routes>
                <Route path="/admin" element={<div>asdsadsad</div>}/>
            </Routes>
        );
    }

    return (
        <>
            <Sidebar>
                <Routes>
                    {/*사용자 로그인 하면 해당 페이지*/}
                    <Route path={"/"} element={<PrivateRoutes/>}>
                        <Route path="/" element={<Home/>}/>
                        <Route path="profile" element={<Profile/>}/>
                        <Route path="message" element={<Message/>}/>
                        <Route path="playList" element={<PlayList/>}/>
                        <Route path="newPeed" element={<NewPeed/>}/>
                        <Route path="setting" element={<Setting/>}/>
                        <Route path="search" element={<SearchResults/>}/>
                         {/*상대방 프로필*/}
                        <Route path="*" element={<NewProfile/>}/>
                    </Route>

                    {/*관리자 로그인 하면 해당 페이지*/}
                    <Route path={"/admin"} element={<AdminRoutes/>}>
                        <Route path="/admin" element={<Home/>}/>
                    </Route >
                </Routes>
            </Sidebar>
            <MusicPlayer/>
        </>
    )
}

export default App;