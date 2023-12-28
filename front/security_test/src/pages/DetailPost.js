import { useSelector } from "react-redux"
import { useParams } from "react-router-dom"

export default function DetailPost(){
  const postList = useSelector((state)=>{return state.postList})
  const {id} = useParams();

  return (
    <div className="detail-container">
      <div className="detail-box">
        {postList[id].postImageName && (
          <img src={`http://localhost:8080/posts/image/${postList[id].postImageName}`} alt="Post" />
        )}
        <div className="detail-content">
          <h3>{postList[id].postTitle}</h3>
          <h5>{postList[id].postContent}</h5>
          <h5>{postList[id].postCreateTime}</h5>
          <h5>{postList[id].user.userName}</h5>
          <h3>댓글</h3>
          <input className="comment-input" placeholder="댓글을 적어보세요"/>
          <button className="comment-btn">작성</button>

          {/* {commentList.map((comment)=> (
          <div key={comment.commentId} className="post-commentList">
            <img src="#"/>
            <h5>{comment.userName}</h5>
            <label>{comment.commentContent}</label>
          </div>
        ))} */}
        </div>
        <div className="clear"></div>
      </div>
    </div>
  )
}