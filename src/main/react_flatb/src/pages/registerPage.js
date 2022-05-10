import React, {useState, useCallback} from 'react';  
import { Link, useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import "./registerPage.css";
import axios from 'axios';


const RegisterPage =(props) =>{ 

    // id . 오류메세지, 유효성검사, 중복확인
    const [userId, setId] = useState('');
    const [idMessage, setIdMessage] = useState('');
    const [isId, setIsId] = useState(false);
    const [idCheck, setIdCheck] = useState(false);

    // 비밀번호, 오류메세지, 유효성검사
    const [pw, setPw] = useState('');
    const [pwMessage, setPwMessage] = useState('');
    const [isPw, setIsPw] = useState(false);
    const [pwImg, setPwImg]=useState('img/m_icon_pass.png');

    // 비밀번호 확인, 오류메세지, 유효성검사
    const [pwConfirm, setPwConfirm] = useState('');
    const [pwConfirmMessage, setPwConfirmMessage] = useState('');
    const [isPwConfirm, setIsPwConfirm] = useState(false);
    const [pwConfirmImg, setPwConfirmImg]=useState('img/m_icon_check_disable.png');

    // 이름, 오류메세지, 유효성검사
    const [name, setName] = useState('');
    const [nameMessage, setNameMessage] = useState('');
    const [isName, setIsName] = useState(false);

    // 닉네임, 오류메세지, 유효성검사, 중복 확인
    const [nickname, setNickName] = useState('');
    const [nickNameMessage, setNickNameMessage] = useState('');
    const [isNickName, setIsNickName] = useState(false);
    const [nickNameCheck, setNickNameCheck] = useState(false);

    // 나이, 오류메세지, 유효성검사
    const [age, setAge] = useState('');
    const [ageMessage, setAgeMessage] = useState('');
    const [isAge, setIsAge] = useState(false);

    // 성별, 오류메세지, 유효성검사
    const [gender, setGender] = useState('');
    const [genderMessage, setGenderMessage] = useState('');
    const [isGender, setIsGender] = useState(false);

    // 전화번호, 오류메세지, 유효성검사
    const [contact, setContact] = useState('');
    const [contactMessage, setContactMessage] = useState('');
    const [isContact, setIsContact] = useState(false);
 
    //페이지 이동
    const navigate = useNavigate();
    

    const onChangeId = useCallback((e) => {
        const idRegex = /[a-zA-Z0-9_-]{5,10}/;
        const idCurrent=e.target.value;
        setId(idCurrent);

        if(idCurrent === ""){
            setIdMessage('필수 입력 요소입니다');
            setIsId(false);
        }
        else if(!idRegex.test(idCurrent)){
            setIdMessage('5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다');
            setIsId(false);
        }
        else{
            setIdMessage('');
            setIsId(true);
        }
        
    },[]);

    const onChangePw = useCallback((e) => {
        const pwRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
        const pwCurrent = e.target.value;
        setPw(pwCurrent);

        if(pwCurrent===""){
            setPwMessage('필수 입력 요소입니다.');
            setIsPw(false);
            setPwImg('img/m_icon_not_use.png');
        }
        else if(!pwRegex.test(pwCurrent) || pwCurrent.length<8){
            setPwMessage('숫자+영문자+특수조합으로 8자리 이상 입력해주세요.');
            setIsPw(false);
            setPwImg('img/m_icon_not_use.png');
        }
        else{
            setIsPw(true);
            setPwImg('img/m_icon_safe.png');
        }

    },[]);

    const onChangePwConfirm = useCallback((e) =>{
        const pwConfirmCurrent = e.target.value;
        setPwConfirm(pwConfirmCurrent);

        if(pw === pwConfirmCurrent){
            setIsPwConfirm(true);
            setPwConfirmImg('img/m_icon_check_enable.png');
        }
        else if(pwConfirmCurrent===""){
            setPwConfirmMessage('필수 입력 요소입니다');
            setIsPwConfirm(false);
        }
        else{
            setPwConfirmMessage('비밀번호가 동일하지 않습니다.');
            setIsPwConfirm(false);
        }

    },[pw]);

    const onChangeName = useCallback((e) => {
        const nameRegex=/[a-zA-Z가-힣]/;
        const namespcRegex=/[~!@#$%^&*()_+|<>?:{}]/;
        const nameCurrent = e.target.value;
        setName(nameCurrent);

        if(nameCurrent === ""){
            setNameMessage('필수 입력 요소입니다');
            setIsName(false);
        }
        else if (!nameRegex.test(nameCurrent)|| namespcRegex.test(nameCurrent) || nameCurrent.indexOf(" ")>-1) {
          setNameMessage('한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)');
          setIsName(false);
        } else {
          setIsName(true);
        }
      }, []);


      const onChangeNickName = useCallback((e) => {
        const nicknameRegex=/[a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
        const nicknamespcRegex=/[~!@#$%^&*()_+|<>?:{}]/;
        const nicknameCurrent = e.target.value;
        setNickName(nicknameCurrent);

        if(nicknameCurrent === "" || nicknameCurrent===null){
            setNickNameMessage('필수 입력 요소입니다');
            setIsNickName(false);
        }
        else if (!nicknameRegex.test(nicknameCurrent) || nicknamespcRegex.test(nicknameCurrent) || nicknameCurrent.indexOf(" ") > -1) {
          setNickNameMessage('한글과 영문 대 소문자 숫자를 사용하세요. (특수기호, 공백 사용 불가)');
          setIsNickName(false);
        } else {
          setIsNickName(true);
          setNickNameMessage(null);
        }
      }, []);

      const onChangeAge = useCallback((e) => {
        setAge(e.target.value);
        const ageRegex=/[0-9]{4}/;
        if(e.target.value === "" ){
            setAgeMessage('필수 입력 요소입니다');
            setIsAge(false);
        }
        else if (!ageRegex.test(e.target.value) || e.target.value < 1920 || e.target.value > 2022) {
          setAgeMessage('생년를 제대로 입력해주세요');
          setIsAge(false);
        } 
        else {
          setIsAge(true);
        }
      }, []);

      const onChangeGender = useCallback((e) => {
        setGender(e.target.value);
        
        if(e.target.value === "성별"){
            setGenderMessage('필수 입력 요소입니다');
            setIsGender(false);
        }
        else {
          setIsGender(true);
        }
      }, []);

      const onChangeContact = useCallback((e) => {
        const contactRegex=/([01]{2})([01679]{1})([-]{1})([0-9]{3,4})([-]{1})([0-9]{4})/;
        const contactCurrent=e.target.value;
        setContact(contactCurrent);
        if(contactCurrent===""){
            setContactMessage('필수 입력 요소입니다');
            setIsContact(false);
        }
        else if(!contactRegex.test(contactCurrent)){
            setContactMessage('형식에 맞지 않는 번호입니다');
            setIsContact(false);
        }
        else {
          setIsContact(true);
        }
      }, []);

      
      const onIdCheck  = useCallback(
        async (e) => {
          e.preventDefault()
          try {
            await axios({
              method: "GET",
              url: '/user/signup/'+ userId
            }).then((res) => {
                if (res.data === false) {
                    setIdCheck(true);
                    setIdMessage("사용 가능한 아이디입니다.");
                }
                else{
                    setIdCheck(false);
                    setIdMessage("사용 불가능한 아이디입니다.");
                    setIsId(false);
                }
              })
          } catch (err) {
            console.error(err)
          }
        },
        [userId]
      )

      const onNickNameCheck  = useCallback(
        async (e) => {
          e.preventDefault()
          try {
            await axios({
              method: "GET",
              url: '/user/signup/'+nickname+'/exists'
            })
              .then((res) => {
                if (res.data === false) {
                    setNickNameCheck(true);
                    setNickNameMessage("사용 가능한 닉네임입니다.");
                }
                else{
                    setNickNameCheck(false);
                    setNickNameMessage("사용 불가능한 닉네임입니다.");
                    setIsNickName(false);
                }
              })
          } catch (err) {
            console.error(err)
          }
        },
        [nickname]
      )


      const onSubmit  = useCallback(
        async (e) => {
          e.preventDefault()
          try {
            await axios({
              method: "POST",
              url: '/user/signup',
              data:{ 
                userId: userId,
                password: pw,
                name: name,
                nickname: nickname,
                contact: contact,
                age: age,
                gender: gender}

            })
              .then((res) => {
                if(res.status===201){
                  Swal.fire({ 
                    icon: 'success', // Alert 타입 
                    title: '회원가입 성공', // Alert 제목 
                    confirmButtonColor: '#DE4D31'
                    });
                  navigate("/login");
                }
                else{
                  Swal.fire({ 
                    icon: 'warning', // Alert 타입 
                    title: '회원가입 실패', // Alert 제목 
                    confirmButtonColor: '#DE4D31'
                    });
                }
              })
          } catch (err) {
            console.error(err)
          }
        },
        [userId,pw, name, nickname, contact, age, gender]
      )

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