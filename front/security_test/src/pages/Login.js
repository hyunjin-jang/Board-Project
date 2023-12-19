import axios from 'axios';
import React, { useState } from 'react';
import { setToken } from '../store/store';
import { useDispatch } from 'react-redux';

export default function Login(){
  const [userName, setId] = useState(null)
  const [userPassword, setPassword] = useState(null)
  const dispatch = useDispatch()
  const loginInfo = {
    userName,
    userPassword
  }

  return (
    <div>
      <h4>ID</h4>
      <input placeholder='Email' onChange={(e)=>{
        setId(e.target.value)
      }}></input>
      <h4>Password</h4>
      <input placeholder='Password' onChange={(e)=>{
        setPassword(e.target.value)
      }}></input>
      <br/>
      <button onClick={()=>{
        axios.post('http://localhost:8080/login', loginInfo)
          .then(response => {
            dispatch(setToken(response.headers['authorization']))
          }).catch(() => {
            console.log('실패함')
          })
      }}>Login</button>
    </div>
  )
}