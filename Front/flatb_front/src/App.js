import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Register from './pages/registerPage';

import './App.css';
function App() {
  return (
    <div className="App">

       <BrowserRouter>   
        <Routes> 
            <Route  path="/" ></Route>
            <Route  path="/register" element={<Register />}></Route>
  
          </Routes> 
      </BrowserRouter>
    </div>
  );
}

export default App;
