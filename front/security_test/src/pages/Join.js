import { useState } from "react"
import { useDispatch } from "react-redux"
import { setJoinModal, setLoginModal } from '../store/store';
import axios from "axios";
import { useNavigate } from "react-router-dom";


export default function Join(){
  const dispatch = useDispatch()
  const [userName, setJoinUserName] = useState()
  const [userPassword, setJoinPassword] = useState()
  const [userBirth, setJoinBirth] = useState()

  const joinInfo = {
    userName,
    userPassword,
    userBirth
  }
  
  return (
    <div className="join-container">
      <div className="clear"></div>
      <h1 style={{float: "right", margin: "0px"}} onClick={()=>{
          dispatch(setJoinModal(false))
        }}>x</h1>
      <div className="clear"></div>
      <div className="join-box">
        <h3 style={{marginTop: "0px"}}>Logo</h3>
        <h3>더 많은 내용을 보고려면 로그인하세요.</h3>
        <form>
          <label>이메일</label>
          <input onChange={(e)=>{
            setJoinUserName(e.target.value)
          }}></input>
          <label>비밀번호</label>
          <input type="password" onChange={(e)=>{
            setJoinPassword(e.target.value)
         }}></input>
          <label>생년월일</label>
          <input type="date" onChange={(e)=>{
            setJoinBirth(e.target.value)
         }}></input>
          <h5 className="btn" style={{background: "#E32C2C", color: "white"}} 
            onClick={()=>{
              axios.post("http://localhost:8080/user", joinInfo)
              .then((response)=>{
                console.log(response.data)
                dispatch(setJoinModal(false))
                dispatch(setLoginModal(true))
              }).catch((error)=>{
                if(error.code == "ERR_BAD_REQUEST") {
                  console.log("중복 아이디 있음")
                } else {
                  console.log("error발생")
                }
              })
            }}>가입하기</h5>
        </form>
        <h5>또는</h5>
        <h5 className="btn" style={{background: "#2C80DE", color: "white"}}>페북 로그인</h5>
        <h5 className="btn" style={{background: "white", color: "#323232", border: "1px solid #323232"}}>구글 로그인</h5>
        <h5 className="btn" style={{background: "#21C148", color: "white"}}>라인 로그인</h5>
        <label>이미 회원이신가요? <span>로그인</span></label>
      </div>
    </div>
  )
}