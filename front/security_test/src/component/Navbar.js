import { Routes, Route, Link, useNavigate, Outlet } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux';
import { setLoginModal } from '../store/store';
import Login from '../pages/Login';

function Navbar(){
  const navigate = useNavigate()
  const dispatch = useDispatch()
  const loginModal = useSelector((state)=>{ return state.loginModal })

  return (
    <div className="navbar-container">
      <h4 style={{float: "left"}}>Logo</h4>
      <div className="auth">
        { localStorage.getItem('authorization')==null ? 
        <>
          <h4 onClick={()=>{
            
          }}>회원가입</h4>
          <h4 onClick={()=>{
            dispatch(setLoginModal())
          }}>로그인</h4>
        </> :
          <h4 onClick={()=>{
            localStorage.removeItem('authorization')
          }}>로그아웃</h4>
        }
        <h4>검색</h4>
        <input placeholder="검색"></input>
      </div>
      <div className="clear"></div>
    </div>
  )
  }

 export default Navbar