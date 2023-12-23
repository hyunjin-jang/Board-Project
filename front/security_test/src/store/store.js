import { configureStore, createSlice } from '@reduxjs/toolkit'

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

export const { setLoginModal } = loginModal.actions
export const { setLoginToken } = loginToken.actions

const store = configureStore({
  reducer: { 
    loginModal : loginModal.reducer,
    loginToken : loginToken.reducer
  }
}) 

export default store