import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from "./pages/home"
import Register from './pages/registerPage';
import Declaration from './pages/declarationPage';
import RecommendOtt from './pages/recommendOttPage';
import RecommendMusic from './pages/recommendMusicPage';
import './App.css';

function App() {
  return (
    <div className="App">
       <BrowserRouter>   
        <Routes> 
            <Route  path="/" element={<Home />}></Route>
            <Route  path="/register" element={<Register />}></Route>
            <Route  path="/report" element={<Declaration />}></Route>
            <Route  path="/recommendOtt" element={<RecommendOtt />}></Route>
            <Route  path="/recommendMusic" element={<RecommendMusic />}></Route>
            
          </Routes> 
      </BrowserRouter>
    </div>
  );
}

export default App;
