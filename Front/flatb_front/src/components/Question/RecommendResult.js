import React from 'react';
import "./RecommendResult.css"
import ResultItem from "../Item/ResultItem"
import RecommendBtn from "../Btn/RecommendBtn"
import { useNavigate } from 'react-router-dom';
import logo from "../../img/flatBCharacter.png"
const RecommendResult = (props) => {

    const {recommend, platform}=props;

    //페이지 이동
    const navigate = useNavigate();
    const onclick=(link)=>{
        if(link=="retry"){
            window.location.reload();
        }
        else if(link=="detail"){
            navigate("/compare");
        }
        
    }
    return (
        <div className="result_wrap">
            <div className="result_cont">
                <div id="recommend">
                    <img id="logoCharacter" src={logo} alt="logo"/>
                    <div>추천 {recommend}</div>
                </div> 
                <div className="recommendResult">
                 <ResultItem recommend={recommend} platformName={platform}/>
                </div>
                <div><RecommendBtn text={"테스트 다시하기"} link={"retry"} click={onclick}/></div>
                <div><RecommendBtn text={recommend+" 정보 \n 자세히 보기"} link={"detail"} click={onclick}/></div>
                <div></div>
                
            </div>
     </div>
    );
};

export default RecommendResult;