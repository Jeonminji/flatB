import React, {useState, useCallback} from 'react';  
import { Link } from 'react-router-dom';
import "./registerPage.css";


const RegisterPage =(props) =>{ 

    // id . 오류메세지, 유효성검사
    const [id, setId] = useState('');
    const [idMessage, setIdMessage] = useState('');
    const [isId, setIsId] = useState(false);

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

    // 닉네임, 오류메세지, 유효성검사
    const [ninkname, setNinkName] = useState('');
    const [ninkNameMessage, setNinkNameMessage] = useState('');
    const [isNinkName, setIsNinkName] = useState(false);

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
 

    const onChangeId = useCallback((e) => {
        const idRegex = /[a-zA-Z0-9_-]{5,10}/;
        const idCurrent=e.target.value;
        setId(idCurrent);

        if(idCurrent === ""){
            setIdMessage('필수 입력 요소입니다');
            setIsId(false);
        }
        else if(!idRegex.test(idCurrent)){
            setIdMessage('"5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다');
            setIsId(false);
        }
        else{
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


      const onChangeNinkName = useCallback((e) => {
        const ninknameRegex=/[a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
        const ninknamespcRegex=/[~!@#$%^&*()_+|<>?:{}]/;
        const ninknameCurrent = e.target.value;
        setName(ninknameCurrent);

        if(ninknameCurrent === "" || ninknameCurrent===null){
            setNinkNameMessage('필수 입력 요소입니다');
            setIsNinkName(false);
        }
        else if (!ninknameRegex.test(ninknameCurrent) || ninknamespcRegex.test(ninknameCurrent) || ninknameCurrent.indexOf(" ") > -1) {
          setNinkNameMessage('한글과 영문 대 소문자 숫자를 사용하세요. (특수기호, 공백 사용 불가)');
          setIsNinkName(false);
        } else {
          setIsNinkName(true);
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
        const contactRegex=/([01]{2})([01679]{1})([0-9]{3,4})([0-9]{4})/;
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


    return ( 
        <div className='register'> 
           <div className="header_logo">
               <Link to="/">
                   <img src="/img/flatB_logo.png" alt="flatB_logo" />
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
                            <input type="text" id="register_id" className="register_int" maxLength="20" onChange={onChangeId}/>
                            <button className="id_check_btn" >중복</button>
                        </span>
                        <span className={`register_errorTxt ${isId ? 'success' : 'error'}`}>{idMessage}</span>
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

                    {/* NINKNAME */}
                    <div>
                        <h3>
                            <label htmlFor="register_ninkname">닉네임</label>
                        </h3>
                        <span className="input_box int_ninkname">
                            <input type="text" id="register_nickname" className="register_int" onChange={onChangeNinkName}/>
                        </span>
                        <span className={`register_errorTxt ${isNinkName ? 'success' : 'error'}`}>{ninkNameMessage}</span>
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
                            <input type="tel" id="register_contact" className="register_int" maxLength="11" onChange={onChangeContact}/>
                        </span>
                        <span className={`register_errorTxt ${isContact ? 'success' : 'error'}`}>{contactMessage}</span>
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