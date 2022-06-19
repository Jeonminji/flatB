import React, { useState, useCallback } from 'react';
import axios from 'axios';
import {Link,useNavigate} from 'react-router-dom'
import "./loginPage.css";
 
function Login(props) {
    const [inputId, setInputId] = useState('')
    const [inputPw, setInputPw] = useState('')

    //페이지 이동
    const navigate = useNavigate();
 
	// input data 의 변화가 있을 때마다 value 값을 변경해서 useState 해준다
    const handleInputId = (e) => {
        setInputId(e.target.value)
    }
 
    const handleInputPw = (e) => {
        setInputPw(e.target.value)
    }
 
	// login 버튼 클릭 이벤트
    const onSubmit  = useCallback(
        async (e) => {
          e.preventDefault()
          try {
            await axios({
              method: "POST",
              url: '/user/login',
              data:{ 
                userId: inputId,
                password: inputPw}

            })
              .then((res) => {
                if(res.status === 200){
                  const accessToken = res.data.accessToken;
                  axios.defaults.headers['Authorization'] = 'Bearer '+ accessToken;
                  props.loginCallBack(true);
                  localStorage.setItem("user",accessToken);
                  navigate("/")
                }
                else{
                  alert("로그인 실패");
                }
                
              })
          } catch (err) {
            alert("로그인 실패");
          }
        },
        [inputId,inputPw,navigate,props]
      )
 
 
    return(
        <div className="login-form">
            <div className="header_logo">
              <Link to="/">
                   <img src="/img/flatB_logo.png" alt="flatB_logo" />
               </Link>
            </div>
            <div className="int-area">
                <input type="text" name="id" id="id"  value={inputId} onChange={handleInputId} />
                <label for="id">아이디</label>
            </div>
            <div className="int-area">
                <input type="password" name="pw" id="pw"value={inputPw} onChange={handleInputPw} />
                <label for="pw">비밀번호</label>
            </div>
            <div className="btn-area">
                <button id = "btn" onClick={onSubmit} >로그인</button>
            </div>
        
        <div className="caption">
            <Link to="/register">회원 가입하기</Link>
        </div>
    
      </div>
    )
}
 
export default Login;
