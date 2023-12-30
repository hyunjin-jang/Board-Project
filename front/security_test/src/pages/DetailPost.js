import axios from "axios";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux"
import { useNavigate, useParams } from "react-router-dom"

export default function DetailPost(){
  const navigate = useNavigate()
  const postList = useSelector((state)=>{return state.postList})
  const [loginUser, setLoginUser] = useState()
  const {id} = useParams();

  
  
  useEffect(()=>{
    const token = localStorage.getItem('authorization')
    
    axios.interceptors.request.use((config)=>{
      config.headers["Authorization"] = token
      return config
    })
  })

  axios.get("http://localhost:8080/user").then((response)=>{

    setLoginUser(response.data.userName)
  })

  function postDelete(){
    axios.delete("http://localhost:8080/posts/"+(id+1))
    .then((response)=>{
      navigate(-1)
      console.log(response.data)
    }).catch((error)=>{
      console.log(error)
    })
  }

  return (
    <div className="detail-container">
      <div className="detail-box">
        {postList[id].postImageName && (
          <img src={`http://localhost:8080/posts/image/${postList[id].postImageName}`} alt="Post" />
        )}
        <div className="detail-content">
          { postList[id].user.userName == loginUser ?
          <>
            <button>수정</button>
            <button onClick={postDelete}>삭제</button>
          </> :
          null
          }
          
          <h3>{postList[id].postTitle}</h3>
          <h5>{postList[id].postContent}</h5>
          <h5>{postList[id].postCreateTime}</h5>
          <h5>{postList[id].user.userName}</h5>
          <h3>댓글</h3>
          <input className="comment-input" placeholder="댓글을 적어보세요"/>
          <button className="comment-btn">작성</button>
        </div>
        <div className="clear"></div>
      </div>
    </div>
  )
}