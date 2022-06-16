import React from 'react';
import "./Loading.css"
import Spinner from "../../img/spinner.gif"
const Loading = (props) => {
    const {recommend} = props;
    return (
        <div className="loading_wrap">
           <div className="recommendLoading">추천 {recommend}를 찾고 있는 중입니다...</div> 
           <div className="loadingSpinner">
               <img src={Spinner} alt="로딩 중"/>
           </div>
            
        </div>
    );
};

export default Loading;