import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Home from "./pages/home"
import Register from './pages/registerPage';
import Declaration from './pages/declarationPage';
import Recruitment from './pages/recruitmentPage';
import RecruitmentCategory from './pages/recruitmentCategoryPage';
import RecommendOtt from './pages/recommendOttPage';
import RecommendMusic from './pages/recommendMusicPage';
import Login from './pages/loginPage'

import './App.css';
function App() {


  const [isLogin , setIsLogin] = useState(false);

  useEffect(()=>{
     const loginState = localStorage.getItem('user');
     
     if(loginState){
      setIsLogin(true);
      axios.defaults.headers['Authorization'] = 'Bearer '+ loginState;
     }
  },[]);

  function loginCallBack(login){
    setIsLogin(login);
  }

  return (
    <div className="App">

       <BrowserRouter>   
        <Routes> 
            
            <Route  path="/" element={<Home isLogin={isLogin} loginCallBack={loginCallBack}/>}></Route>
            <Route  path="/register" element={<Register />}></Route>
            <Route  path="/report" element={<Declaration  isLogin={isLogin} loginCallBack={loginCallBack}/>}></Route>
            <Route  path="/recruitment" element={<Recruitment  isLogin={isLogin} loginCallBack={loginCallBack}/>}></Route>
            <Route  path="/recruitment/:category" element={<RecruitmentCategory  isLogin={isLogin} loginCallBack={loginCallBack}/>}></Route>
            <Route  path="/recommendOtt" element={<RecommendOtt isLogin={isLogin} loginCallBack={loginCallBack}/>}></Route>
            <Route  path="/recommendMusic" element={<RecommendMusic isLogin={isLogin} loginCallBack={loginCallBack} />}></Route>
            <Route  path="/login" element={<Login loginCallBack={loginCallBack} />}></Route>
          </Routes> 
      </BrowserRouter>
    </div>
  );
}

export default App;
