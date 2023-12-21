import { configureStore, createSlice } from '@reduxjs/toolkit'

const loginModal = createSlice({
  name : 'loginModal',
  initialState : false,
  reducers : {
    setLoginModal(state){
      return !state
    }
  }
})

export const { setLoginModal } = loginModal.actions

const store = configureStore({
  reducer: { 
    loginModal : loginModal.reducer
  }
}) 

export default store