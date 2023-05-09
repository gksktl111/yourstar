import React from "react";

export const useAuthenticate = (): boolean => {

    // 임시 스토리지데이터
    localStorage.setItem("logincheck", true);
    // 여기서 React-Query를 이용해 서버로부터 data fetching 수행
    const isUserLoggedIn = localStorage.getItem("logincheck");

    return isUserLoggedIn;
};