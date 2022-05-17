import React, {useState, useCallback} from 'react';


import Header from '../components/Header'; 
import "./declarationPage.css";


const Declaration=(props) =>{ 

    const [declar_category, set_declar_category] = useState('');
    const [is_declar_category, set_is_declar_category] = useState(false);
    
    const onChangeDeclar_category = useCallback((e) => {
        const declar_category_current=e.target.value;
        set_declar_category(declar_category_current);

        if(declar_category_current === "신고게시판"){
            set_is_declar_category(false);
        }
        else{
            set_is_declar_category(true);
        }
        
    },[]);


    // 신고유형
    const [declar_type, set_declar_type] = useState('');
    const [is_declar_type, set_is_declar_type] = useState(false);
    
    const onChangeDeclar_type = useCallback((e) => {
        const declar_type_current=e.target.value;
        set_declar_type(declar_type_current);

        if(declar_type_current === "declaration_type"){
            set_is_declar_type(false);
        }
        else{
            set_is_declar_type(true);
        }
        
    },[]);

        // 신고 내용
        const [declar_content, set_declar_content] = useState('');
        const [is_declar_content, set_is_declar_content] = useState(false);
        
        const onChangeDeclar_content = useCallback((e) => {
            const declar_content_current=e.target.value;
            set_declar_content(declar_content_current);
    
            if(declar_content_current === ""){
                set_is_declar_content(false);
            }
            else{
                set_is_declar_content(true);
            }
            
        },[]);


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