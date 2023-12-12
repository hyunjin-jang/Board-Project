import axios from 'axios';
import React, { useState } from 'react';

export default function Login(){
  const [id, setId] = useState()
  const [password, setPassword] = useState()

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
        axios.post()
      }}>Login</button>
    </div>
  )
}