import React, {useState, useCallback,useEffect} from 'react';
import axios from 'axios';
import {useNavigate} from 'react-router-dom';
import Header from '../components/Header/Header'; 
import "./declarationPage.css";


const Declaration=(props) =>{ 

        //페이지 이동
         const navigate = useNavigate();
        // 최초 한번 실행
        useEffect(()=>{

           if(!props.isLogin){
                navigate("/login");
           }
        
        },[navigate, props])

    // 신고 닉네임
    const [declar_nickname, set_declar_nickname] = useState('');
    const [is_declar_nickname, set_is_declar_nickname] = useState(false);
    
    const onChangeDeclar_nickname = useCallback((e) => {
        const declar_nickname_current=e.target.value;
        set_declar_nickname(declar_nickname_current);

        if(declar_nickname_current === ""){
            set_is_declar_nickname(false);
        }
        else{
            set_is_declar_nickname(true);
        }
        
    },[]);

    // 신고 게시판  
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


    const declar_onsubmit  = useCallback(
        async (e) => {
            e.preventDefault()

            if(!is_declar_nickname||!is_declar_category||!is_declar_type||!is_declar_content){
                alert("신고에 실패하셨습니다.\n닉네임, 게시판, 유형, 내용을 입력했는지 확인해주세요.")
        }else{
            
            try {
            await axios
            ({
                method: "POST",
                url: '/report',
                data:{ 
                    nickname: declar_nickname,
                    note: declar_category,
                    type: declar_type,
                    content: declar_content
                }
    
                })
                .then((res) => {
                console.log('response:', res)
                if (res.status === 201) {
                 alert("신고되었습니다.");
                }
                else{
                    alert("신고에 실패하였습니다.");
                }
                })
            } catch (err) {
            console.error(err)
            }
        }},
        [declar_nickname, declar_category, declar_type, declar_content,
            is_declar_nickname,is_declar_category,is_declar_type,is_declar_content]
        )

    return ( 
       <div>
           <Header isLogin={props.isLogin} loginCallBack={props.loginCallBack}/> 
           
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
                                                <option value="신고">==신고 게시판==</option>
                                                <option value="리뷰 게시판">리뷰 게시판</option>
                                                <option value="모집 게시판">모집 게시판</option>
                                            </select>  
                            </div>

                            <div className="declar_type">
                                    <span>신고 유형</span>
                                    <select className="declar_select" onChange={onChangeDeclar_type} >
                                            <option value="declaration_type">==신고 유형==</option>
                                            <option value="부적절한 닉네임">부적절한 닉네임</option>
                                            <option value="금융 사기">금융 사기</option>
                                            <option value="기타">기타</option>
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