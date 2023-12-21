import axios from 'axios';
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { setLoginModal } from '../store/store';

export default function Login(){
  const [userName, setUserName] = useState(null)
  const [userPassword, setPassword] = useState(null)
  const dispatch = useDispatch()
  const loginInfo = {
    userName,
    userPassword
  }

  return (
    <div className="login-container">
      
     <div className="clear"></div>
     <h1 style={{float: "right", margin: "0px"}} onClick={()=>{
      dispatch(setLoginModal())
     }}>x</h1>
     <div className="clear"></div>
     <div className="login-box">
       <h3 style={{marginTop: "0px"}}>Logo</h3>
       <h3>더 많은 내용을 보고려면 로그인하세요.</h3>
       <form>
         <label>이메일</label>
         <input onChange={(e)=>{
          setUserName(e.target.value)
         }}></input>
         <label>비밀번호</label>
         <input onChange={(e)=>{
          setPassword(e.target.value)
         }}></input>
         <label style={{fontWeight: "bold"}}>비밀번호를 잊으셨나요?</label>
         <h5 className="btn" style={{background: "#E32C2C", color: "white"}}
          onClick={()=>{
            axios.post("http://localhost:8080/login", loginInfo)
            .then((response)=>{
              localStorage.setItem("authorization", response.headers['authorization'])
              
            }).catch((error)=>{
              console.log(error)
            })
          }}>로그인</h5>
       </form>
       <h5>또는</h5>
       <h5 className="btn" style={{background: "#2C80DE", color: "white"}}>페북 로그인</h5>
       <h5 className="btn" style={{background: "white", color: "#323232", border: "1px solid #323232"}}>구글 로그인</h5>
       <h5 className="btn" style={{background: "#21C148", color: "white"}}>라인 로그인</h5>
       <label>아직 Logo를 사용하고 있지 않으신가요?<span>가입하기</span></label>
     </div>
     {userName}
     {userPassword}
   </div>
  )
}