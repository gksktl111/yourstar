import React from "react";
import {Navigate, Route, Routes} from "react-router-dom";
import Login from "./login/LoginPage";
import MainPage  from "./main/MainPage";

function Authorized() {
    let isAuthorized = sessionStorage.getItem("isAuthorized");

    return (
        <div>
            {!isAuthorized ? <Navigate to="/login" /> : <Navigate to="/" />}
            <Routes>
                <Route path="/login">
                    <Login />
                </Route>

                <Route path="/">
                    <MainPage />
                </Route>
            </Routes>
        </div>
    );
}
export default Authorized;