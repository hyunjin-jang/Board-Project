import axios from "axios"
import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux"
import { useNavigate } from "react-router-dom"
import { setLoginToken, setPostList } from "../store/store"

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
      if(localStorage.getItem("authorization")){
        dispatch(setLoginToken(true))
      }
    }).catch((error)=>{
    })
  }, [postCount])

  return (
    <div className="post-container">
      <div className="main-box">
        <div className="clear"></div>
        {postList.map((postContent)=> (
          <div key={postContent.postId} className="post-content" onClick={(e)=>{
            let postIndex = postContent.postId
            navigate("/posts/" + postIndex)
          }}>
            <div className="post-list-img">
            {postContent.postImageName && (
              <img src={`http://localhost:8080/posts/image/${postContent.postImageName}`} alt="Post" />
            )}
            </div>
            <div className="post-list-context">
            <h4>{postContent.postTitle}</h4>
            <h5>작성자 {postContent.user.userName}</h5>
            </div>
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