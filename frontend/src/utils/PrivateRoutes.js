import React from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useAuthenticate } from "../hooks/useAuthenticate";

export const PrivateRoutes = (): React.ReactElement => {
    // 로그인 검사 후 상황에 맞게 네비게이트
    return useAuthenticate() ? <Outlet /> : <Navigate to="/login" />;
};

export default PrivateRoutes;