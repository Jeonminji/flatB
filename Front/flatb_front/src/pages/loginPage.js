import React, { useState, useCallback } from 'react';
import axios from 'axios';
import {Link,useNavigate} from 'react-router-dom'
import "./loginPage.css";
 
function Login(props) {
    
 
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