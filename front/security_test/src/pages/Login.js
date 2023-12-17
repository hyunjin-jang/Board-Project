import axios from 'axios';
import React, { useState } from 'react';

export default function Login(){
  const [userName, setId] = useState(null)
  const [userPassword, setPassword] = useState(null)
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
          .then((result) => {
            console.log(result.headers.getAuthorization("Authorization"))
            
          }).catch(() => {
            console.log('실패함')
          })
      }}>Login</button>
    </div>
  )
}