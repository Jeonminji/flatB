import React from 'react';
import "./QuestionBtn.css"
const QuestionBtn = (props) => {
    const {text, click,choose} = props;
    const onClickQuestionBtn=()=>{
        click(choose);
    }
    return (
        <button className="questionBtn" onClick={onClickQuestionBtn}>{text}</button>
    );
};

export default QuestionBtn;