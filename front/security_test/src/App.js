import Login from './pages/Login';
import Join from './pages/Join';
import Navbar from './component/Navbar'
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Routes, Route, Link, useNavigate, Outlet } from 'react-router-dom'
import axios from 'axios';
import './App.css';
import PostList from './pages/PostList';
import WirtePost from './pages/WritePost';
import DetailPost from './pages/DetailPost';

function App() {
  const navigate = useNavigate()
  const loginModal = useSelector((state)=>{ return state.loginModal })
  const joinModal = useSelector((state)=>{ return state.joinModal })

  return (
    <div className="App">
      <Navbar/>
      {loginModal ? <Login/> : null}
      {joinModal ? <Join/> : null}
      <Routes>
        <Route path='/' element={<div onClick={()=>{
          navigate("/posts")
        }}>Main Page</div>}/>
        <Route path='/posts/:id' element={<DetailPost/>}/>
        <Route path='/posts' element={<PostList/>}/>
        <Route path='/write' element={<WirtePost/>}/>
      </Routes>
      
      
      <div id='fail_page'></div>
    </div>
  );
}

export default App;
