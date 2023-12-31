import { Routes, Route, Link, useNavigate, Outlet } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux';
import { setJoinModal, setLoginModal, setLoginToken, setUserToken } from '../store/store';
import { useEffect } from 'react';
import axios from 'axios';

function Navbar(){
  const navigate = useNavigate()
  const dispatch = useDispatch()
  const loginModal = useSelector((state)=> {return state.loginModal})
  const loginToken = useSelector((state)=> {return state.loginToken})
  const userToken = useSelector((state)=>{ return state.userToken })

  useEffect(()=>{
    dispatch(setUserToken(localStorage.getItem("authorization")))
  }, [loginModal, loginToken])

  return (
    <div className="navbar-container">
      <h4 style={{float: "left"}} onClick={()=>{
        navigate('/')
      }}>Logo</h4>
      <div className="auth">
        { userToken == null ? 
        <>
          <h4 onClick={()=>{
            dispatch(setJoinModal(true))
          }}>회원가입</h4>
          <h4 onClick={()=>{
            dispatch(setLoginModal(true))
          }}>로그인</h4>
        </> :
        <>
          <h4 onClick={()=>{
            localStorage.removeItem('authorization')
            dispatch(setUserToken(null))
            axios.interceptors.request.use((config)=>{
              delete config.headers["Authorization"]
              return config
            })
            navigate("/")
          }}>로그아웃</h4>
          <h4 onClick={()=>{
            navigate("/mypage")
          }}>마이페이지</h4>
        </>
        }
        <h4>검색</h4>
        <input placeholder="검색"></input>
      </div>
      <div className="clear"></div>
    </div>
  )
  }

 export default Navbar