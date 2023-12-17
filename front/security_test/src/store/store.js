import { configureStore, createSlice } from '@reduxjs/toolkit'

const jwtToken = createSlice({
  name : 'jwtToken',
  initialState : null,
  reducers : {
    setJwtToken(state, token){
      return token.payload
    }
  }
})
export const { setJwtToken } = jwtToken.actions

export default configureStore({
  reducer: { 
    jwtToken : jwtToken.reducer
  }
}) 