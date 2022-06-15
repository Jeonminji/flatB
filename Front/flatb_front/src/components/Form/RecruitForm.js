import React , { useState, useCallback, useEffect } from 'react';
import axios from 'axios';
import "./RecruitForm.css"
const RecruitForm = (props) => { 
    
    const {close,boardNo, title, content, platformname,totalcount, currentcount, usedate_start, usedate_end, contact, writer_id, regdate} = props;

            
    
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
                    <input type="text" value={close ? "get":recruit_currentcount}className="personnelInput" onChange={onChange_recruit_currentcount} placeholder="현재 인원 수(본인 제외)"/>
                    / 
                    <input type="text" className="personnelInput" value={close ? "get":recruit_totalcount} onChange={onChange_recruit_totalcount} placeholder="전체 모집 인원(본인 제외)"/>
                </label>
            </div>
            <div className="recruitDateInputBox">
                <label>
                    사용 기간
                    <input type="date" value={close ? "get": recruit_usedate_start} className="dateInput" onChange={onChange_recruit_usedate_start}/>
                    ~
                    <input type="date"  value={close ? "get": recruit_usedate_end} className="dateInput" onChange={onChange_recruit_usedate_end}/>
                </label>
            </div>
            <div className="recruitDateInputBox">
                <label>
                    연락 수단
                    <input type="text"  value={close ? "get": recruit_contact} className="contactInput" onChange={onChange_recruit_contact} placeholder="ex) 오픈카톡 링크 "/>

                </label>
            </div>
            <div className="recruitOneWordInputBox">
                <div id="oneWord">
                    한 마디
                </div>
                <textarea type="text" value={close ? "get": recruit_content}className="onewordInput" onChange={onChange_recruit_content} placeholder="짧은 한마디( ex) 마음 맞는 분들인 경우 연장 가능합니다!)"/>
            </div>
            <div className="line"></div>
            <div className="recruitFormBtnBox">
                <button className="recruitFormBtn" onClick={contentSubmit}>submit</button>
            </div>

        </div> 
        
        )
    
     
} 
export default RecruitForm;