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
    const [loading, setLoading] = useState(false);
    const [result, setResult] = useState(false);
    const [questionNum, setQuestionNum] = useState(0);
    const [netflixTotal, setNetflixTotal] = useState(0);
    const [tvingTotal, setTvingTotal] = useState(0);
    const [watchaTotal, setWatchaTotal] = useState(0);
    const [wavveTotal, setWavveTotal] = useState(0);
    const [disneyTotal, setDisneyTotal] = useState(0);
    const [laftelTotal, setLaftelTotal] = useState(0)
    

    const onScoreChange = (choose) => {
        const type = OttQuestion[questionNum].answers[choose].type;
        const score = OttQuestion[questionNum].answers[choose].score;

        for(const i of type){
            if(i==="netflix"){
                setNetflixTotal(netflixTotal+score);
            }
            else if(i==="tving"){
                setTvingTotal(tvingTotal+score);
            }
            else if(i==="watcha"){
                setWatchaTotal(watchaTotal+score);
            }
            else if(i==="wavve"){
                setWavveTotal(wavveTotal+score);
            }
            else if(i==="disney"){
                setDisneyTotal(disneyTotal+score);
            }
            else if(i==="laftel"){
                setLaftelTotal(laftelTotal+score);
            }
        }

        if(questionNum ===(questionTotal-1)){
            setLoading(true);
            setTimeout(function(){ 
                setLoading(false);
                setResult(true);
            },3000);
        }

        setQuestionNum(questionNum + 1);
       
    }

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