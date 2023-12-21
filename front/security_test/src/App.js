import Login from './pages/Login';
import Navbar from './component/Navbar'
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Routes, Route, Link, useNavigate, Outlet } from 'react-router-dom'
import axios from 'axios';
import './App.css';

function App() {
  const navigate = useNavigate()
  const [data, setData] = useState('Security Context');
  const loginModal = useSelector((state)=>{ return state.loginModal })

  return (
    <div className="App">
      <Navbar/>
      {loginModal ? <Login/> : null}

      <h4 onClick={()=>{
        axios.post('http://localhost:8080/users/admin',{},{
          headers: {
            'Authorization': localStorage.getItem('authorization')
          }
        })
          .then((result)=>{
            console.log(result.data)
            var target = document.getElementById('fail_page')
            if (target) {
              target.innerHTML = result.data;
            }
          }).catch(()=>{
            console.log('fail')
            var target = document.getElementById('fail_page')
            if (target) {
              target.innerHTML = "Sever Error";
            }
          })
      }}>{ data }</h4>
      <div id='fail_page'></div>
    </div>
  );
}

export default App;
