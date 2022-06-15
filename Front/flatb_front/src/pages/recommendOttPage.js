import React,{ useState} from 'react';
import Parser from 'html-react-parser';
import Header from '../components/Header/Header';
import Question from '../components/Question/Question'
import OttQuestion from "../components/Question/questionOtt.json"
import QuestionBtn from "../components/Btn/QuestionBtn"
import Loading from "../components/Question/Loading"
import RecommendResult from "../components/Question/RecommendResult"

const RecommendOttPage = () => {
    const questionTotal = OttQuestion.length;
 

    if ((questionNum+1) <= questionTotal ){
    
        return (
            <>
                <Header/>
                <Question questionNum={questionNum+1} questionNum_end={questionTotal} question={OttQuestion[questionNum].question}>
    
                {OttQuestion[questionNum].answers.map((answer,i)=>(
                <QuestionBtn key={i} choose={i} text={Parser(answer.text)} click={onScoreChange}/>))}
    
                </Question>
              
                
            </>
        );
        
    }
    else if((questionNum+1)>questionTotal){

 

        return (
            <>
            
            {loading ? (<Loading recommend={"OTT"}/>):null}
            {result ? (<RecommendResult recommend={"OTT"} platform={platform}/>):null}

            </>
        );
            
    }
    
};

export default RecommendOttPage;