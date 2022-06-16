import React,{ useState} from 'react';
import Parser from 'html-react-parser';
import Header from '../components/Header/Header';
import Question from '../components/Question/Question'
import MusicQuestion from "../components/Question/questionMusic.json"
import QuestionBtn from "../components/Btn/QuestionBtn"
import Loading from "../components/Question/Loading"
import RecommendResult from "../components/Question/RecommendResult"

const RecommendMusicPage = () => {
    const questionTotal = MusicQuestion.length;
    const [loading, setLoading] = useState(false);
    const [result, setResult] = useState(false);
    const [questionNum, setQuestionNum] = useState(0);
    const [bugsTotal, setbugsTotal] = useState(0);
    const [melonTotal, setmelonTotal] = useState(0);
    const [youtubeTotal, setyoutubeTotal] = useState(0);
    const [spotifyTotal, setspotifyTotal] = useState(0);
    const [floTotal, setfloTotal] = useState(0);
    const [genieTotal, setgenieTotal] = useState(0)
    

    const onScoreChange = (choose) => {
        const type = MusicQuestion[questionNum].answers[choose].type;
        const score = MusicQuestion[questionNum].answers[choose].score;

        for(const i of type){
            if(i==="벅스"){
                setbugsTotal(bugsTotal+score);
            }
            else if(i==="멜론"){
                setmelonTotal(melonTotal+score);
            }
            else if(i==="유튜브뮤직"){
                setyoutubeTotal(youtubeTotal+score);
            }
            else if(i==="스포티파이"){
                setspotifyTotal(spotifyTotal+score);
            }
            else if(i==="플로"){
                setfloTotal(floTotal+score);
            }
            else if(i==="지니"){
                setgenieTotal(genieTotal+score);
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
                <Question questionNum={questionNum+1} questionNum_end={questionTotal} question={MusicQuestion[questionNum].question}>
    
                {MusicQuestion[questionNum].answers.map((answer,i)=>(
                <QuestionBtn key={i} choose={i} text={Parser(answer.text)} click={onScoreChange}/>))}
    
                </Question>
              
                
            </>
        );
        
    }
    else if((questionNum+1)>questionTotal){

        const totalScore = {
            "bugs":bugsTotal,
            "melon":melonTotal,
            "youtubemusic":youtubeTotal,
            "spotify":spotifyTotal,
            "flo":floTotal,
            "genie":genieTotal
        }
        
        const maxScore = Math.max(bugsTotal,melonTotal,youtubeTotal,spotifyTotal,floTotal,genieTotal);
        const platforms = Object.keys(totalScore).filter(key => totalScore[key] === maxScore);
        
        //결과 여러 개 일 경우 하나만 도출
        const number = Math.floor(Math.random() * platforms.length);
        const platform = platforms[number];

        return (
            <>
            
            {loading ? (<Loading recommend={"Music"}/>):null}
            {result ? (<RecommendResult recommend={"Music"} platform={platform}/>):null}

            </>
        );
            
    }
    
};

export default RecommendMusicPage;