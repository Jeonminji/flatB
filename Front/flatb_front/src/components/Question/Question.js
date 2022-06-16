import React from 'react';
import ProgressBar from '../ProgressBar/ProgressBar';
import "./Question.css"
const Question = (props) => {
    const {num, questionNum, questionNum_end, question} = props;
    return (
        <div className="question_wrap">
            <div className="question_cont">
                <ProgressBar num={num} completed={(questionNum/questionNum_end)*100} question={question} questionNum ={questionNum} questionNum_end={questionNum_end} />
                <div className="questionBox">
                    {props.children}
                </div>
               
                
            </div>
        </div>
        
    );
};

export default Question;