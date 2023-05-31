import React from 'react';
import {Navigate, Outlet} from "react-router-dom";

const AdminRoutes = ({ component: component, ...rest }) => {

    // 로컬스토리지에 토큰이 있으면 프라이빗루트로 렌더링
    // 없으면 로그인 페이지로 네비게이트

    // 예시로 사용 하는 토큰
    // 다시 막고 싶으면 스토리지에서 삭제해야됨
    // localStorage.setItem("adminToken", true);
    const token = localStorage.getItem("adminToken");
    return token ? <Outlet /> : <Navigate to="/login" />;
};

export default AdminRoutes;