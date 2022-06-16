import React,{useState, useEffect} from 'react'; 
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Logo from '../../img/flatB_logo.png';
import "./Header.css";

const Header= (props) =>{ 

    const [loginState, setLoginState] = useState("");
    
    //페이지 이동
    const navigate = useNavigate();

    useEffect(() => {

        if(props.isLogin){
            setLoginState("logout");
        }
        else{
            setLoginState("login");
        }

    },[props.isLogin] )

    const testClick=()=>{
        if(loginState==="login"){
            navigate("/login");
        }
        else{
            axios({
            method: "get",
            url: "/member/me",
            responseType: "json"
             }).then((res)=>{
               setLoginState("login");
                props.loginCallBack(false);
                localStorage.clear();
                alert("로그아웃 되었습니다.");
                delete axios.defaults.headers.common['Authorization'];
            
        });
        }
    }

    
        return ( 
            <div className = "Header"> 
                <div className="Header_logo"> 
                    <Link to="/"> 
                            <img className="logo" src={Logo} alt="로고 이미지"/>
                    </Link>
                </div>
                <div className="category_box">
                    <div className="login_box">
                        <div className="loginState" onClick={testClick} >{loginState}</div>
                        <div> | </div>
                        <Link to="/report"> <div>🚨</div> </Link>    
                    </div>
                    <ul className = "categories">
                    <Link to="/"><li>플랫폼 추천</li></Link>
                    <Link to="/compare"><li>플랫폼 비교</li></Link>
                    <Link to="/review"><li>플랫폼 리뷰</li></Link>
                    <Link to="/recruitment"><li>플랫폼 모집</li></Link>
                    </ul>
                </div>
            </div> 
            );
    
     
} 

export default Header;

