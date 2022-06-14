import React, {useState, useCallback} from 'react';  
import { Link, useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import "./registerPage.css";
import axios from 'axios';


const RegisterPage =(props) =>{ 



    return ( 
        <div className='register'> 
           <div className="header_logo">
               <Link to="/">
                   <img src="/img/flatB_logo.png" alt="flatB_logo" />
               </Link>
           </div>
          
           <div className="register_wrap">
          
                <div className="register_content">
                <form onSubmit={onSubmit}>

                    {/* ID */}
                    <div>
                        <h3>
                            <label htmlFor="register_id">아이디</label>
                        </h3>
                        <span className="input_box int_id">
                            <input type="text" id="register_id" className="register_int" maxLength="20" onChange={onChangeId}/>
                            <button className="id_check_btn" onClick={onIdCheck} >중복</button>
                        </span>
                        <span className={`register_errorTxt ${isId ? 'check_success' : 'error'}`}>{idMessage}</span>
                    </div>
                    {/*  PW1  */}
                    <div>
                        <h3>
                            <label htmlFor="register_pw1">비밀번호</label>
                        </h3>
                        <span className="input_box int_pw">
                            <input type="password" id="register_pw1" className="register_int" maxLength="20" onChange={onChangePw}/>
                            <img src={pwImg} className="pswdImg" alt="password"/>
                        </span>
                        <span className={`register_errorTxt ${isPw ? 'success' : 'error'}`}>{pwMessage}</span>
                    </div>

                    {/* PW2 */}
                    <div>
                        <h3>
                            <label htmlFor="register_pw2">비밀번호 재확인</label>
                            </h3>
                        <span className="input_box int_pw_check">
                            <input type="password" id="register_pw2" className="register_int" maxLength="20" onChange={onChangePwConfirm}/>
                            <img src={pwConfirmImg}  className="pswdImg" alt="passwordCheck"/>
                        </span>
                        <span className={`register_errorTxt ${isPwConfirm ? 'success' : 'error'}`}>{pwConfirmMessage}</span>
                    </div>

                    {/* NAME */}
                    <div>
                        <h3>
                            <label htmlFor="register_name">이름</label>
                            </h3>
                        <span className="input_box int_name">
                            <input type="text" id="register_name" className="register_int" onChange={onChangeName}/>
                        </span>
                        <span className={`register_errorTxt ${isName ? 'success' : 'error'}`}>{nameMessage}</span>
                    </div>

                    {/* NickNAME */}
                    <div>
                        <h3>
                            <label htmlFor="register_nickname">닉네임</label>
                        </h3>
                        <span className="input_box int_nickname">
                            <input type="text" id="register_nickname" className="register_int" onChange={onChangeNickName}/>
                            <button className="id_check_btn" onClick={onNickNameCheck}>중복</button>
                        </span>
                        <span className={`register_errorTxt ${isNickName ? 'check_success' : 'error'}`}>{nickNameMessage}</span>
                    </div>

                    {/* AGE */}
                    <div>
                        <h3>
                            <label htmlFor="register_age">생년</label>
                        </h3>
                        <span className="input_box int_age">
                            <input type="text" id="register_age" className="register_int" maxLength="4" onChange={onChangeAge}/>
                        </span>
                        <span className={`register_errorTxt ${isAge ? 'success' : 'error'}`}>{ageMessage}</span>
                    </div>

                    {/* GENDER */}
                    <div>
                        <h3>
                            <label htmlFor="register_gender">성별</label>
                        </h3>
                        <span className="input_box gender_code">
                            <select id="register_gender" className="sel" onChange={onChangeGender}>
                                <option value="성별">성별</option>
                                <option value="여성">여자</option>
                                <option value="남성">남자</option>
                            </select>                            
                        </span>
                        <span className={`register_errorTxt ${isGender ? 'success' : 'error'}`}>{genderMessage}</span>
                    </div>

                    {/* contact */}
                    <div>
                        <h3>
                            <label htmlFor="register_contact">연락처</label>
                        </h3>
                        <span className="input_box int_contact">
                            <input type="tel" id="register_contact" className="register_int" maxLength="13" onChange={onChangeContact}/>
                        </span>
                        <span className={`register_errorTxt ${isContact ? 'success' : 'error'}`}>{contactMessage}</span>
                    </div>

                    {/* JOIN BTN */}
                    <div className="register_btn_area">
                        <button className="register_btn"
                        disabled={!(idCheck && isPw && isPwConfirm && isName && nickNameCheck && isAge && isGender && isContact)} 
                        type="submit">
                            가입하기
                        </button>
                   </div>
                </form>


                </div>            
            </div> 
        </div>
    ); 
} 

export default RegisterPage;