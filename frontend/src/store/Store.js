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

// 회원가입 모달창
let isSignUpModalOpen = createSlice({
    name : "isSignUpModalOpen",
    initialState : false,
    reducers : {
        signUpModalOn(){
            return true
        },
        signUpModalOff(){
            return false
        }
    }
})

// 피드 햄버거 아이콘 오픈
let isOptionModalOpen = createSlice({
    name : "isOptionModalOpen",
    initialState : false,
    reducers : {
        optionModalOn(){
            return true
        },
        optionModalOff(){
            return false
        }
    }
})

// 피드 댓글 오픈
let isCommentModalOpen = createSlice({
    name : "isCommentModalOpen",
    initialState : false,
    reducers : {
        commentModalOn(){
            return true
        },
        commentModalOff(){
            return false
        }
    }
})

// 새피드 만들기 오픈
let isNewPeedModalOpen = createSlice({
    name : "isNewPeedModalOpen",
    initialState : false,
    reducers : {
        newPeedModalOn(){
            return true
        },
        newPeedModalOff(){
            return false
        }
    }
})

// 새채팅방 만들기 오픈
let isNewChatModalOpen = createSlice({
    name : "isNewChatModalOpen",
    initialState : false,
    reducers : {
        newChatModalOn(){
            return true
        },
        newChatModalOff(){
            return false
        }
    }
})


// 아이디 찾기 액션
export let {idFindModalOn, idFindModalOff} = isIdFindModalOpen.actions

// 비밀번호 찾기 액션
export let {pwFindModalOn,pwFindModalOff} = isPwFindModalOpen.actions

// 회원가입 액션
export let {signUpModalOn, signUpModalOff} = isSignUpModalOpen.actions

// 피드 햄버거 아이콘 액션
export let {optionModalOn, optionModalOff} = isOptionModalOpen.actions

// 피드 코멘트 액션
export let {commentModalOn, commentModalOff} = isCommentModalOpen.actions

// 뉴피드 액션
export let {newPeedModalOn, newPeedModalOff} = isNewPeedModalOpen.actions

// 뉴채팅방 액셕
export let {newChatModalOn, newChatModalOff} = isNewChatModalOpen.actions

export default configureStore({
    reducer: {
        isIdFindModalOpen : isIdFindModalOpen.reducer,
        isPwFindModalOpen : isPwFindModalOpen.reducer,
        isSignUpModalOpen : isSignUpModalOpen.reducer,
        isOptionModalOpen : isOptionModalOpen.reducer,
        isCommentModalOpen : isCommentModalOpen.reducer,
        isNewPeedModalOpen : isNewPeedModalOpen.reducer,
        isNewChatModalOpen : isNewChatModalOpen.reducer
    }
})