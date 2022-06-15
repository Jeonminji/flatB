import React,{Component} from 'react'; 
import { Link } from 'react-router-dom';
import Logo from '../../img/flatB_logo.png'
import "./Header.css"

class Header extends Component { 
    render(){
        return ( 
            <div className = "Header"> 
                <div className="Header_logo"> 
                    <Link to="/"> 
                            <img className="logo" src={Logo}/>
                    </Link>
                </div>
                <div className="category_box">
                    <div className="login_box">
                        <Link to="/login"> <div>login</div> </Link>
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
     
} 

export default Header;

