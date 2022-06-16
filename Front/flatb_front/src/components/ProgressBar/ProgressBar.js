import React from 'react';
import "./ProgressBar.css";

const ProgressBar = (props) => {
    const { questionNum, questionNum_end,question} = props;
    const progressWidth = (questionNum/questionNum_end)*100+"%";
    
    return (
      <div className="progress_wrap">
        <div className="progress_cont">
          <div className="progressCurrent">
            <span id="questionNum">{questionNum}</span>
            <span id="totalQuestionNum">/ {questionNum_end}</span>
            <div id="questionTitle">{question}</div>
          </div>
        
          <div className="progressContainer">
            <div className="progressFiller" style={{width:progressWidth}}></div>
          </div>
        </div>
       
      </div>
      
    );
  };

export default ProgressBar;