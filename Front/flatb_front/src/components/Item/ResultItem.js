import React,{useEffect,useState} from 'react';
import axios from 'axios';
import "./ResultItem.css"

const ResultItem = (props) => {


    return (
        <div className="recommendPlatform">
        <div className="recommendPlatformName">{platform}</div>
        <div className="resultContent">
            <img src={resultImg} alt="플랫폼 로고"/>
        </div>
        </div>
    );
};

export default ResultItem;