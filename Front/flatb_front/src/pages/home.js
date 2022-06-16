import React from 'react'; 
import { Link } from 'react-router-dom'
import Header from '../components/Header/Header';
import "./home.css" 
const Home=() =>{ 

    return ( 
        <> 
            <Header /> 
            <div className="home_wrap">
                <div className="home_cont">
                    <div className="recommendChoose">
                        <Link to="recommendOtt"><div className="choosePlatform">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OTT 추천<br/> 테스트 받으러가기</div></Link>
                        <Link to="recommendMusic"><div className="choosePlatform">&nbsp;&nbsp;&nbsp;&nbsp;MUSIC 추천 <br/>테스트 받으러가기</div></Link>
                    </div>
                    <div className="recommendSkip">
                        <Link to="/비교"><div className="skipBtn">skip</div></Link>
                    </div>

                </div>
            </div>
        </> 
    ); 
} 

export default Home;

