import React from 'react';
import { useNavigate } from 'react-router-dom';
import "./RecruitCategory.css"
const RecruitCategory = () => {
    const navigate = useNavigate();
    const categoryGet=(platform)=>{
        navigate("/recruitment/"+platform);
    }
    return (
        <div className="recruitCategory">
            <ul className='recruitCategoryUl'>
                <li onClick={()=>{categoryGet("netflix")}}>NETFLIX</li>
                <li onClick={()=>{categoryGet("watcha")}}>WATCHA</li>
                <li onClick={()=>{categoryGet("tving")}}>Tving</li>
                <li onClick={()=>{categoryGet("wavve")}}>WAVVE</li>
                <li onClick={()=>{categoryGet("disneyplus")}}>DisneyPlus</li>
                <li onClick={()=>{categoryGet("laftel")}}>LAFTEL</li>
            </ul>
        </div>
    );
};

export default RecruitCategory;