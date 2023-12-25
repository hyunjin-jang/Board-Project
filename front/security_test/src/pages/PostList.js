import axios from "axios"
import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux"
import { useNavigate } from "react-router-dom"
import { setPostList } from "../store/store"

export default function PostList(){
  const navigate = useNavigate()
  const dispatch = useDispatch()
  const loginToken = useSelector((state)=> {return state.loginToken})
  const postList = useSelector((state)=> {return state.postList})
  const [postCount] = useState(postList.length)

  useEffect(()=>{
    axios.get("http://localhost:8080/posts")
    .then((response)=>{
      dispatch(setPostList(response.data))
    }).catch((error)=>{
    })
  }, [postCount])

  return (
    <div className="post-container">
      <div className="main-box">
        <div className="clear"></div>
        {postList.map((postContent)=> (
          <div key={postContent.postId} className="post-content">
            <img src={postContent.postFile}/>
            <h4>{postContent.postTitle}</h4>
            <h5>작성자 {postContent.user.userName}</h5>
          </div>
        ))}
        <div className="clear"></div>

        {
          loginToken ?
          <button onClick={()=>{ navigate("/write") }}>글쓰기</button> :
          null
        }
      </div> 
    </div>
  )
}