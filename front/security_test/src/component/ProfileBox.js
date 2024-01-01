import axios from "axios"
import { useState } from "react"
import { useSelector } from "react-redux"

export default function ProfileBox(){

  const [userName, setUserName] = useState()
  const [userBirth, setUserBirth] = useState()
  const [userEmail, setUserEmail] = useState("-")
  const [userPhone, setUserPhone] = useState("-")
  const [userAddress, setUserAddress] = useState("-")

  // const userToken = useSelector((state)=>{ return state.userToken})

  // axios.interceptors.request.use((config)=>{
  //   config.headers.setAuthorization(userToken)
  //   return config
  // })

  // axios.get('http://localhost:8080/user') 
  //   .then((response)=>{
  //     if(response.data.userName != null){setUserName(response.data.userName)}
  //     if(response.data.userBirth != null){setUserBirth(response.data.userBirth)}
  //     if(response.data.userEmail != null){setUserEmail(response.data.user.Email)}
  //     if(response.data.userPhone != null){setUserPhone(response.data.userPhone)}
  //     if(response.data.userAddress != null){setUserAddress(response.data.userAddress)}
  //   })
  

  return (
    <div className="profile-box">
      <div className="profile-top">
        <div className="clear"></div>
        <h2 style={{ float:"left" }}>내 정보</h2>
        <h4 className="edit-btn">수정</h4>
        <div className="clear"></div>
      </div>
      <div className="profile-middle">
        <div className="profile-img">
          <img src="https://kormedi.com/wp-content/uploads/2022/04/ck_tica1010005154_l-580x387.jpg"/>
        </div>
        <h3>이름 : { userName }</h3>
        <h3>생년월일 : { userBirth }</h3>
        <h3>이메일 : { userEmail }</h3>
        <h3>전화번호 : { userPhone }</h3>
        <h3>주소 : { userAddress } </h3>
      </div>
    </div>
  )
}