import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Register from './pages/registerPage';
import Declaration from './pages/declarationPage';
function App() {
  return (
    <div className="App">
       <BrowserRouter>   
        <Routes> 
            <Route  path="/register" element={<Register />}></Route>
            <Route  path="/declaration" element={<Declaration />}></Route>
          </Routes> 
      </BrowserRouter>
    </div>
  );
}

export default App;
