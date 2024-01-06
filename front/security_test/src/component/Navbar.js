import { Routes, Route, Link, useNavigate, Outlet } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux';
import { setJoinModal, setLoginModal, setLoginToken, setUserToken } from '../store/store';

function Navbar(){
  const navigate = useNavigate()
  const dispatch = useDispatch()
  const loginModal = useSelector((state)=> {return state.loginModal})
  const loginToken = useSelector((state)=> {return state.loginToken})
  const userToken = useSelector((state)=>{ return state.userToken })

  function logoutAction(){
    localStorage.removeItem('authorization')
    navigate("/")
  }
  console.log(localStorage.getItem('authorization'))
  
  return (
    <div className="navbar-container">
      <h4 style={{float: "left"}} onClick={()=>{
        navigate('/')
      }}>Logo</h4>
      <div className="auth">
        { localStorage.getItem('authorization') == null ? 
        <>
          <h4 onClick={()=>{ dispatch(setJoinModal(true)) }}>회원가입</h4>
          <h4 onClick={()=>{ dispatch(setLoginModal(true)) }}>로그인</h4>
        </> :
        <>
          <h4 onClick={ logoutAction }>로그아웃</h4>
          <h4 onClick={()=>{ navigate("/mypage") }}>마이페이지</h4>
        </>
        }
        <h4>검색</h4>
        <input placeholder="검색"></input>
      </div>
      <div className="clear"></div>
    </div>
  )}

 export default Navbar