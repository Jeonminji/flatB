import React , { useState, useCallback, useEffect } from 'react';
import axios from 'axios';
import "./RecruitForm.css"
const RecruitForm = (props) => { 
    
    const {close,boardNo, title, content, platformname,totalcount, currentcount, usedate_start, usedate_end, contact} = props;
    // 제목
    const [recruit_title, set_title] = useState(title);
    const [is_title, set_is_title] = useState(false);
    // 플랫폼명
    const [recruit_platformname, set_platformname] = useState(platformname);
    const [is_platformname, set_is_platformname] = useState(false);
    // 모집 인원
    const [recruit_totalcount, set_totalcount] = useState(totalcount);
    const [is_totalcount, set_is_totalcount] = useState(false);
    // 현재 인원
    const [recruit_currentcount, set_currentcount] = useState(currentcount);
    const [is_currentcount, set_is_currentcount] = useState(false);
    // 시작사용기간
    const [recruit_usedate_start, set_usedate_start] = useState(usedate_start);
    const [is_usedate_start, set_is_usedate_start] = useState(false);
    // 사용기간 끝
    const [recruit_usedate_end, set_usedate_end] = useState(usedate_end);
    const [is_usedate_end, set_is_usedate_end] = useState(false);
    // 연락 수단
    const [recruit_contact, set_contact] = useState(contact);
    const [is_contact, set_is_contact] = useState(false);
    // 한 마디
    const [recruit_content, set_content] = useState(content);
    const [is_content, set_is_content] = useState(false);
  
    useEffect(() => {
        if(boardNo){
            set_is_title(true);
            set_is_platformname(true);
            set_is_totalcount(true);
            set_is_currentcount(true);
            set_is_usedate_start(true);
            set_is_usedate_end(true);
            set_is_contact(true);
            set_is_content(true);
        }
    },[boardNo])
   
          
            
    const onChange_recruit_title = useCallback((e) => {
        const title_current=e.target.value;
        set_title(title_current);

        if(title_current === ""){
            set_is_title(false);
        }
        else{
            set_is_title(true);
        }
        
    },[]);


    const onChange_recruit_platformname = useCallback((e) => {
        const platfromname_current=e.target.value;
        set_platformname(platfromname_current);

        if(platfromname_current === "platform"){
            set_is_platformname(false);
        }
        else{
            set_is_platformname(true);
        }
        
    },[]);


    const onChange_recruit_totalcount = useCallback((e) => {
        const totalcount_current=e.target.value;
        const totalcountRegex=/[0-5]{1}/;
        set_totalcount(totalcount_current);

        if(totalcount_current === "" || !totalcountRegex.test(totalcount_current)){
            set_is_totalcount(false);
        }
        else{
            set_is_totalcount(true);
        }
        
    },[]);


    const onChange_recruit_currentcount = useCallback((e) => {
        
        const currentcountRegex=/[0-5]{1}/;
        const currentcount_current=e.target.value;
        set_currentcount(currentcount_current);

        if(currentcount_current === "" || !currentcountRegex.test(currentcount_current)){
            set_is_currentcount(false);
        }
        else{
            set_is_currentcount(true);
        }
        
    },[]);


    const onChange_recruit_usedate_start = useCallback((e) => {
        const usedate_current=e.target.value;
        set_usedate_start(usedate_current);

        if(usedate_current === ""){
            set_is_usedate_start(false);
        }
        else{
            set_is_usedate_start(true);
        }
        
    },[]);


    const onChange_recruit_usedate_end = useCallback((e) => {
        const usedate_end_current=e.target.value;
        set_usedate_end(usedate_end_current);

        if(usedate_end_current === ""){
            set_is_usedate_end(false);
        }
        else{
            set_is_usedate_end(true);
        }
        
    },[]);


    const onChange_recruit_contact = useCallback((e) => {
        const contact_current=e.target.value;
        set_contact(contact_current);

        if(contact_current === ""){
            set_is_contact(false);
        }
        else{
            set_is_contact(true);
        }
        
    },[]);

        
    const onChange_recruit_content = useCallback((e) => {
        const content_current=e.target.value;
        set_content(content_current);

        if(content_current === ""){
            set_is_content(false);
        }
        else{
            set_is_content(true);
        }
        
    },[]);

    // 글쓰기 모달 submit
    const contentSubmit = useCallback(
        async (e) => {
            e.preventDefault()
            if(!boardNo){
                if(!is_title||!is_platformname||!is_totalcount||!is_currentcount||
                    !is_usedate_start|| !is_usedate_end || !is_contact || !is_content){
                        alert("'제목, 플랫폼 명, 모집 인원, 사용 기간, 연락 수단, 한마디를 입력했는지 확인해주세요");
                    }
                else{
                    try {
                        await axios({
                        method: "POST",
                        url: '/recruitmentOtt/write',
                        data:{ 
                            title: recruit_title,
                            platformname: recruit_platformname,
                            totalcount: recruit_totalcount,
                            currentcount: recruit_currentcount,
                            usedateEnd: recruit_usedate_end,
                            usedateStart: recruit_usedate_start,
                            contact: recruit_contact,
                            content: recruit_content
                            }
                        })
                        .then((res) => {
                            if(res.status===201){
                                alert("글쓰기 성공하셨습니다.");
                                window.location.reload();            
                            }
                            else{
                                alert("글쓰기 실패");
                            }
                        
                        })
                    }catch(err){
                        console.error(err)
                        }
                    }
            }
            else{
                if(!is_title||!is_platformname||!is_totalcount||!is_currentcount||
                    !is_usedate_start|| !is_usedate_end || !is_contact || !is_content){
                        alert("'제목, 플랫폼 명, 모집 인원, 사용 기간, 연락 수단, 한마디를 입력했는지 확인해주세요");
                    }
                else{
                    try {
                        await axios({
                        method: "PUT",
                        url: '/recruitmentOtt/update/'+boardNo,
                        data:{ 
                            title: recruit_title,
                            platformname: recruit_platformname,
                            totalcount: recruit_totalcount,
                            currentcount: recruit_currentcount,
                            usedateEnd: recruit_usedate_end,
                            usedateStart: recruit_usedate_start,
                            contact: recruit_contact,
                            content: recruit_content
                            }
                        })
                        .then((res) => {
                            if(res.status===201){
                                alert("글수정에 성공하셨습니다.");
                                window.location.reload();            
                            }
                            else{
                                console.log(res);
                                alert("글수정 실패");
                            }
                        
                        })
                    }catch(err){
                        console.error(err)
                        }
                    }
            }
          
            },
        [recruit_title, recruit_platformname, recruit_totalcount, 
        recruit_currentcount, recruit_usedate_start, recruit_usedate_end, 
        recruit_contact, recruit_content, is_title, is_platformname, 
        is_totalcount, is_currentcount, is_usedate_start, is_usedate_end, 
        is_contact, is_content,boardNo]
      )
            
    
        return ( 
         <div className="recruitContentModal">
            <div className="recruitTitleInputBox">
                <label>
                    제목
                    <input type="text" value={close ? "get":recruit_title} className="titleInput" onChange={onChange_recruit_title} placeholder="ex) [단기] / [장기] 플랫폼명 구합니다!"/>
                </label>
            </div>
            <div className="recruitPlatform">
            <label>플랫폼 명
            <select className="platformSelect" value={close ? "get":recruit_platformname}onChange={onChange_recruit_platformname}>
                <option value="platform">모집 플랫폼</option>
                <option value="넷플릭스">넷플릭스</option>
                <option value="왓챠">왓챠</option>
                <option value="티빙">티빙</option>
                <option value="웨이브">웨이브</option>
                <option value="디즈니플러스">디즈니플러스</option>
                <option value="라프텔">라프텔</option>
            </select>     
            </label>
            </div>
            <div className="recruitPersonInputBox">
                <label>
                    모집 인원
                    <input type="text" value={close ? "":recruit_currentcount}className="personnelInput" onChange={onChange_recruit_currentcount} placeholder="현재 인원 수(본인 제외)"/>
                    / 
                    <input type="text" className="personnelInput" value={close ? "":recruit_totalcount} onChange={onChange_recruit_totalcount} placeholder="전체 모집 인원(본인 제외)"/>
                </label>
            </div>
            <div className="recruitDateInputBox">
                <label>
                    사용 기간
                    <input type="date" value={close ? "": recruit_usedate_start} className="dateInput" onChange={onChange_recruit_usedate_start}/>
                    ~
                    <input type="date"  value={close ? "": recruit_usedate_end} className="dateInput" onChange={onChange_recruit_usedate_end}/>
                </label>
            </div>
            <div className="recruitDateInputBox">
                <label>
                    연락 수단
                    <input type="text"  value={close ? "": recruit_contact} className="contactInput" onChange={onChange_recruit_contact} placeholder="ex) 오픈카톡 링크 "/>

                </label>
            </div>
            <div className="recruitOneWordInputBox">
                <div id="oneWord">
                    한 마디
                </div>
                <textarea type="text" value={close ? "": recruit_content}className="onewordInput" onChange={onChange_recruit_content} placeholder="짧은 한마디( ex) 마음 맞는 분들인 경우 연장 가능합니다!)"/>
            </div>
            <div className="line"></div>
            <div className="recruitFormBtnBox">
                <button className="recruitFormBtn" onClick={contentSubmit}>submit</button>
            </div>

        </div> 
        
        )
    
     
} 
export default RecruitForm;