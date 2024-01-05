import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom"

export default function DetailPost(){
  const navigate = useNavigate()
  const {id} = useParams()
  const [posting, setPosting] = useState()
  const [images, setImages] = useState()
  const [tokenUser, setTokenUser] = useState();
  let currentImage = 0
  
  useEffect(()=>{
    axios.get('http://localhost:8080/posts/'+(id))
    .then((response)=>{
      console.log(response.data)
      setPosting(response.data)
      setImages(response.data.postImageNames)
    })
    if(localStorage.getItem('authorization')){
      axios.defaults.headers.common.Authorization = localStorage.getItem('authorization')
      axios.get('http://localhost:8080/user')
      .then((response)=>{
        setTokenUser(response.data.userNickName)
      })
    }
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
                />
              }):
              null
            }
          </div>
          <div className="selectImage">
            <button onClick={(e)=>{
              if(currentImage > 0){
                currentImage = currentImage-650
                document.querySelector('.slide-container').style.transform='translateX(-'+currentImage+'px)'
              }
            }}>◀</button>

            {
              posting ?
              images.map((image, id)=>{
                return (
                  <button key={id} onClick={()=>{
                    currentImage = id*650
                    document.querySelector('.slide-container').style.transform='translateX(-'+currentImage+'px)'
                  }}>{id+1}</button>
                )
              }):
              null
            }
            <button onClick={()=>{
              if(currentImage < (images.length-1)*650){
                currentImage = currentImage + 650
                document.querySelector('.slide-container').style.transform='translateX(-'+currentImage+'px)'
              } else {
                currentImage = 0
                document.querySelector('.slide-container').style.transform='translateX(-'+currentImage+'px)'
              }
            }}>▶</button>
          </div>
        </div>
        <div className="detail-content"> 
          { posting ?
            posting.userNickName == tokenUser ?
            <>
              <button onClick={()=>{ navigate('/posts/edit/'+id) }}>수정</button>
              <button onClick={postDelete}>삭제</button> 
            </>
            :
            null
          :
          null
          }
          {
            posting ?
            <>
              <h3>{posting.postTitle}</h3>
              <h5>{posting.postContent}</h5>
              <h5>{posting.postCreateTime}</h5>
              <h5>{posting.userNickName}</h5>
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