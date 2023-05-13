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
// 아이디 찾기 액션
export let {idFindModalOn, idFindModalOff} = isIdFindModalOpen.actions

export default configureStore({
    reducer: {
        isIdFindModalOpen : isIdFindModalOpen.reducer
    }
})