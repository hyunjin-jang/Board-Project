import Login from './pages/Login';
import Navbar from './component/Navbar'
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Routes, Route, Link, useNavigate, Outlet } from 'react-router-dom'
import axios from 'axios';
import './App.css';

function App() {
  const navigate = useNavigate()
  const loginModal = useSelector((state)=>{ return state.loginModal })

  return (
    <div className="App">
      <Navbar/>
      {loginModal ? <Login/> : null}

      
      <div id='fail_page'></div>
    </div>
  );
}

export default App;
