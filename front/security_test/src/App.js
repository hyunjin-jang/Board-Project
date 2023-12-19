import Login from './pages/Login';
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';
import { useSelector } from 'react-redux';

function App() {
  const [login, setLogin] = useState(false)
  const [data, setData] = useState('Security Context');
  const token = useSelector((state)=>{ return state.token })

  return (
    <div className="App">
      <h4 onClick={()=>{ setLogin(!login) }}>Login</h4>
      { login ? <Login/> : null }

      <h4 onClick={()=>{
        axios.post('http://localhost:8080/users/admin',{},{
          headers: {
            'Authorization': token
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
