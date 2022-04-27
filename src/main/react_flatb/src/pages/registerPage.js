import React from 'react'; 
import { Link } from 'react-router-dom';
// import "./RegisterPage.css";


const RegisterPage =(props) =>{ 

    return ( 
        <div className='register'> 
           <div className="header_logo">
               <Link to="/">
                   <img src="/img/flatB_logo.png" />
               </Link>
           </div>
          
           <div className="register_wrap">
          
                <div className="register_content">
                <form>
                {/* ID */}
                <div>
                        <h3>
                            <label htmlFor="register_id">아이디</label>
                        </h3>
                        <span className="input_box int_id">
                            <input type="text" id="register_id" className="register_int" maxLength="10"/>
                            <button className="id_check_btn" >중복</button>
                        </span>
                        <span className="register_errorTxt"></span>
                    </div>
                    {/*  PW1  */}
                    <div>
                        <h3>
                            <label htmlFor="register_pw1">비밀번호</label>
                        </h3>
                        <span className="input_box int_pw">
                            <input type="password" id="register_pw1" className="register_int" maxLength="20"/>
                            <img src="img/m_icon_pass.png" className="pswdImg"/>
                        </span>
                        <span className="register_errorTxt"></span>
                    </div>

                    {/* PW2 */}
                    <div>
                        <h3>
                            <label htmlFor="register_pw2">비밀번호 재확인</label>
                            </h3>
                        <span className="input_box int_pw_check">
                            <input type="password" id="register_pw2" className="register_int" maxLength="20" />
                            <img src="img/m_icon_check_disable.png"  className="pswdImg"/>
                        </span>
                        <span className="register_errorTxt"></span>
                    </div>

                    {/* NAME */}
                    <div>
                        <h3>
                            <label htmlFor="register_name">이름</label>
                            </h3>
                        <span className="input_box int_name">
                            <input type="text" id="register_name" className="register_int"/>
                        </span>
                        <span className="register_errorTxt"></span>
                    </div>

                    {/* NINKNAME */}
                    <div>
                        <h3>
                            <label htmlFor="register_ninkname">닉네임</label>
                        </h3>
                        <span className="input_box int_ninkname">
                            <input type="text" id="register_nickname" className="register_int" />
                        </span>
                        <span className="register_errorTxt"></span>
                    </div>

                    {/* AGE */}
                    <div>
                        <h3>
                            <label htmlFor="register_age">생년</label>
                        </h3>
                        <span className="input_box int_age">
                            <input type="text" id="register_age" className="register_int"/>
                        </span>
                        <span className="register_errorTxt"></span>
                    </div>

                    {/* GENDER */}
                    <div>
                        <h3>
                            <label htmlFor="register_gender">성별</label>
                        </h3>
                        <span className="input_box gender_code">
                            <select id="register_gender" className="sel">
                                <option value="성별">성별</option>
                                <option value="여성">여자</option>
                                <option value="남성">남자</option>
                            </select>                            
                        </span>
                        <span className="register_errorTxt"></span>
                    </div>

                    {/* contact */}
                    <div>
                        <h3>
                            <label htmlFor="register_contact">연락처</label>
                        </h3>
                        <span className="input_box int_contact">
                            <input type="tel" id="register_contact" className="register_int" maxLength="11" />
                        </span>
                        <span className="register_errorTxt"></span>
                    </div>

                    {/* JOIN BTN */}
                    <div className="register_btn_area">
                        <button className="register_btn" 
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