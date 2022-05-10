import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Register from './pages/registerPage';
function App() {
  return (
    <div className="App">
       <BrowserRouter>   
        <Routes> 
            <Route  path="/register" element={<Register />}></Route>
          </Routes> 
      </BrowserRouter>
    </div>
  );
}

export default App;
