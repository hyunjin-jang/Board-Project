import axios from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

export default function WirtePost(){
  const navigate = useNavigate()

  const [postTitle, setPostTitle] = useState()
  const [postContent, setPostContent] = useState()
  const [postImageName, setPostImageName] = useState()
  const [postFile, setPostFile] = useState()

  const token = localStorage.getItem('authorization')
  const jwToken = token.replace(/^Bearer\s/, '')

  const writeDto = {
    postTitle,
    postContent,
    postImageName,
    jwToken 
  }

  const handleImageUpload = (e) =>{
    setPostFile(e.target.files[0]);
  }

  const handleSubmit = async (e) =>{
    if(token != null){
      e.preventDefault();

      const formData = new FormData()
      formData.append('postFile' , postFile)

      try {
      const response = await axios.post('http://localhost:8080/posts/image', formData);
      setPostImageName(response.data);
      console.log(postImageName)
      axios.post('http://localhost:8080/posts', writeDto)
      .then((response)=>{
        console.log(response.data)
      })
      // 처리 완료 후 필요한 동작 수행
      } catch (error) {
        console.error('Error creating post:', error);
      }
    }
  }

  return (
    <div className="write-container">
      <form onSubmit={handleSubmit} className="write-box">
        <h5>제목</h5>
        <input className="write-title" onChange={(e)=>{
            setPostTitle(e.target.value)
          }}></input>
        <h5>내용</h5>
        <textarea onChange={(e)=>{
            setPostContent(e.target.value)
          }}/>
        <br/>
        <input type="file" onChange={handleImageUpload}></input>
        <br/>
        <button className="write-btn" type="submit">작성하기</button>
      </form>
    </div>
  )
}