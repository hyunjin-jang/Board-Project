import { configureStore, createSlice } from '@reduxjs/toolkit'
import axios from 'axios'

const loginToken = createSlice({
  name : 'loginToken',
  initialState : false,
  reducers : {
    setLoginToken(state, action){
      return action.payload
    }
  }
})

const loginModal = createSlice({
  name : 'loginModal',
  initialState : false,
  reducers : {
    setLoginModal(state, action){
      return action.payload
    }
  }
})

const joinModal = createSlice({
  name : 'joinModal',
  initialState : false,
  reducers : {
    setJoinModal(state, action){
      return action.payload
    }
  }
})

const postList = createSlice({
  name : 'postList',
  initialState : [],
  reducers : {
    setPostList(state, action){
      return [...action.payload]
    }
  }
})

export const { setLoginToken } = loginToken.actions
export const { setLoginModal } = loginModal.actions
export const { setJoinModal } = joinModal.actions
export const { setPostList } = postList.actions

const store = configureStore({
  reducer: { 
    loginModal : loginModal.reducer,
    loginToken : loginToken.reducer,
    joinModal : joinModal.reducer,
    postList : postList.reducer
  }
}) 

export default store