import React,{ useState} from 'react';
import Parser from 'html-react-parser';
import Header from '../components/Header/Header';
import Question from '../components/Question/Question'
import MusicQuestion from "../components/Question/questionMusic.json"
import QuestionBtn from "../components/Btn/QuestionBtn"
import Loading from "../components/Question/Loading"
import RecommendResult from "../components/Question/RecommendResult"

const RecommendMusicPage = () => {
  

    if ((questionNum+1) <= questionTotal ){
    
        return (
            <>
                <Header/>
                <Question questionNum={questionNum+1} questionNum_end={questionTotal} question={MusicQuestion[questionNum].question}>
    
                {MusicQuestion[questionNum].answers.map((answer,i)=>(
                <QuestionBtn key={i} choose={i} text={Parser(answer.text)} click={onScoreChange}/>))}
    
                </Question>
              
                
            </>
        );
        
    }
    else if((questionNum+1)>questionTotal){

        
        return (
            <>
            
            {loading ? (<Loading recommend={"Music"}/>):null}
            {result ? (<RecommendResult recommend={"Music"} platform={platform}/>):null}

            </>
        );
            
    }
    
};

export default RecommendMusicPage;