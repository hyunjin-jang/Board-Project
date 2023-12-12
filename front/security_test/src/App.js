import Login from './pages/Login';
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [login, setLogin] = useState(false)
  const [data, setData] = useState('Security Context');

  return (
    <div className="App">
      <h4 onClick={()=>{ setLogin(!login) }}>Login</h4>
      { login ? <Login/> : null }

      <h4 onClick={()=>{
        axios.get('http://localhost:8080/hello')
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
