import React from 'react';


import Header from '../components/Header'; 
import "./declarationPage.css";


const Declaration=(props) =>{ 

    return ( 
       <div>
           <Header/>
           
           <div className="declar_wrap">
               <div className="declar_cont">
                    <form className="declar_form" onSubmit={declar_onsubmit}>
                            <div className="declar_nickname">
                                <span>신고 닉네임</span>
                                <input type="text" className="declar_input_nickname" onChange={onChangeDeclar_nickname}/>
                            </div>

                            <div className="declar_category">
                                <span>신고 게시판</span>
                                <select className="declar_select" onChange={onChangeDeclar_category}>
                                                <option value="신고게시판">==신고 게시판==</option>
                                                <option value="리뷰">리뷰 게시판</option>
                                                <option value="">모집 게시판</option>
                                            </select>  
                            </div>

                            <div className="declar_type">
                                    <span>신고 유형</span>
                                    <select className="declar_select" onChange={onChangeDeclar_type} >
                                            <option value="declaration_type">==신고 유형==</option>
                                            <option value="nickname_declar">부적절한 닉네임</option>
                                            <option value="cheat_declar">금융 사기</option>
                                            <option value="etc_declar">기타</option>
                                    </select>               
                                </div>

                            <div className="declar_content">
                                <div>신고 내용</div>
                                
                                <textarea type="text" className="declar_textarea_content" onChange={onChangeDeclar_content}/> 
                            </div>
                            <div className="declar_btn_area">
                                <button type="submit" className="declar_btn">신고</button>
                            </div>
                    </form>
               </div>
           </div>
           
       </div>
    ); 
} 

export default Declaration;