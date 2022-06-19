import React from 'react';
import "./RecommendBtn.css"

const RecommendBtn = (props) => {
    const {text, click, link } = props;

    const onClickBtn=()=>{
        click(link);
    }
    return (
        <button className="recommendBtn" onClick={onClickBtn}>{text}</button>
    );
};

export default RecommendBtn;