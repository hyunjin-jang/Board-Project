import axios from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

export default function WirtePost(){
  const navigate = useNavigate()

  const [postTitle, setPostTitle] = useState()
  const [postContent, setPostContent] = useState()
  const [postFile, setPostFile] = useState()

  const token = localStorage.getItem('authorization')
  const jwToken = token.replace(/^Bearer\s/, '')

  const postInfo = {
    postTitle,
    postContent,
    postFile,
    jwToken 
  }

  return (
    <div className="write-container">
      <div className="write-box">
        <h5>제목</h5>
        <input className="write-title" onChange={(e)=>{
            setPostTitle(e.target.value)
          }}></input>
        <h5>내용</h5>
        <textarea onChange={(e)=>{
            setPostContent(e.target.value)
          }}/>
        <br/>
        <input type="file" onChange={(e)=>{
            setPostFile(e.target.value)
          }}></input>
        <br/>
        <h5 className="write-btn" onClick={()=>{
          if(token != null) {
            axios.post("http://localhost:8080/posts", postInfo)
            .then((response)=>{
              console.log(response)
              navigate("/posts")
            })
          } else {
            console.log("Token 없음")
          }
        }}>작성하기</h5>
      </div>
    </div>
  )
}