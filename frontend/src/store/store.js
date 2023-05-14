import {configureStore, createSlice} from "@reduxjs/toolkit";

// 아이디 찾기 모달창
let isIdFindModalOpen = createSlice({
    name : "isIdFindModalOpen",
    initialState : false,
    reducers : {
        idFindModalOn(){
            return true
        },
        idFindModalOff(){
            return false
        }
    }
})

// 비밀번호 찾기 모달창
let isPwFindModalOpen = createSlice({
    name : "isIdFindModalOpen",
    initialState : false,
    reducers : {
        pwFindModalOn(){
            return true
        },
        pwFindModalOff(){
            return false
        }
    }
})

// 아이디 찾기 액션
export let {idFindModalOn, idFindModalOff} = isIdFindModalOpen.actions
// 비밀번호 찾기 액션
export let {pwFindModalOn,pwFindModalOff} = isPwFindModalOpen.actions
export default configureStore({
    reducer: {
        isIdFindModalOpen : isIdFindModalOpen.reducer,
        isPwFindModalOpen : isPwFindModalOpen.reducer
    }
})