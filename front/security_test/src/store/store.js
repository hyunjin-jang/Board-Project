import { configureStore, createSlice } from '@reduxjs/toolkit'

const token = createSlice({
  name : 'token',
  initialState : null,
  reducers : {
    setToken(state, token){
      return token.payload
    }
  }
})
export const { setToken } = token.actions

export default configureStore({
  reducer: { 
    token : token.reducer
  }
}) 