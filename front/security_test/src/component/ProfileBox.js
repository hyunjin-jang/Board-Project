export default function ProfileBox(){

  const userSample1 = {
    userName : "hyun",
    userBirth : "93.11.21",
    userEmail : "okmt0423@gmail.com",
    userPhone : "090-4399-0887",
    userAddress : "Fukuoka Naka 5-17-4"
  }

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
        <h3>이름 : { userSample1.userName }</h3>
        <h3>생년월일 : { userSample1.userBirth }</h3>
        <h3>이메일 : { userSample1.userEmail }</h3>
        <h3>전화번호 : { userSample1.userPhone }</h3>
        <h3>주소 : { userSample1.userAddress } </h3>
      </div>
    </div>
  )
}