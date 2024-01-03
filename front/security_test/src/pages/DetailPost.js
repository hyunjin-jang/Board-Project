import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom"

export default function DetailPost(){
  const navigate = useNavigate()
  const {id} = useParams()
  const [posting, setPosting] = useState()
  const [images, setImages] = useState()
  
  useEffect(()=>{
    axios.get('http://localhost:8080/posts/'+(id))
    .then((response)=>{
      setPosting(response.data)
      setImages(response.data.postImageNames)
    })
  }, [])

  function postDelete(){
    axios.delete("http://localhost:8080/posts/"+(id))
    .then((response)=>{
      navigate(-1)
    }).catch((error)=>{
      console.log(error)
    })
  }

  

  return (
    <div className="detail-container">
      <div className="detail-box">
        <div className="slide-box">
          <div className="slide-container">
            { posting ?
              images.map((imageName, id)=>{
                return <img 
                  key={id} 
                  src={`http://localhost:8080/posts/image/${imageName}`}
                  alt="Post" 
                  onClick={()=>{
                    console.log(id)
                    document.getElementsByClassName('slide-container')[0].style.transform = 'translateX(-650px)';
                  }}
                />
              }):
              null
            }
          </div>
        </div>
        <div className="detail-content"> 
          {/* { postList[id].user.userName == loginUser ?
          <>
            <button>수정</button>
            <button onClick={postDelete}>삭제</button>
          </> :
          null
          } */}
          {
            posting ?
            <>
              <h3>{posting.postTitle}</h3>
              <h5>{posting.postContent}</h5>
              <h5>{posting.postCreateTime}</h5>
              <h5>{posting.userName}</h5>
            </> :
            <p>Loding...</p>
          }
          <h3>댓글</h3>
          <input className="comment-input" placeholder="댓글을 적어보세요"/>
          <button className="comment-btn">작성</button>
        </div>

      </div>
    </div>
  )
}