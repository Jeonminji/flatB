import React , { useState, useCallback, useEffect } from 'react';
import Swal from 'sweetalert2';
import axios from 'axios';

import "./recruitmentPage.css";
import Header from '../components/Header/Header'; 
import Modals from '../components/Modal/Modal';
import Item from '../components/Item/RecruitItem';
import Pagination from "../components/Pagination/Pagination"
import RecruitCategory from '../components/Category/RecruitCategory';
import RecruitForm from '../components/Form/RecruitForm'

const Recruitment=(props) =>{ 




    return ( 
    <>
        <Header /> 
        <div className="warningComment">경고: 모집장과 모집 팀원의 닉네임을 잘 확인해두시길 바랍니다.</div>
        <div className="recruitWarp">
            <div className="recruitContent">
                <div className="recruitFilterbox">
                    <div className="recruitSituationCheckBox">
                        <label><input type="checkbox" className="checkBox" name="situation"
                            onChange={(e) => checkOnlyOne(e.target,e.target.checked)} 
                            />모집 중</label>
                    </div>
                    <div className="recruitMyCheckBox">
                        <label>
                            <input type="checkbox"  className="checkBox" name="my"
                                onChange={(e) => checkOnlyOne(e.target,e.target.checked)} 
                                />내 글 보기</label>
                    </div>
                    <div className="recruitContentButton">
                        <button onClick={openbtnModal} className="recruitButton">글쓰기</button>
                        <Modals open={modalbtnOpen} close={closebtnModal} header="모집게시판 글쓰기">
                            <RecruitForm/>     
                        </Modals>
                    </div>
                </div>
                <div className="recruitBox">
                    <RecruitCategory/>
                    <div className="recruitCont">
                        <div className="recruitCont2">
                            {recruitItem.items.slice(offset, offset + limit).map(item => <Item key={item.boardNo} item={item}/>)}
                        </div>
                      
                    </div>
                </div>
            </div>
        </div>
    </>
    ); 
} 

export default Recruitment;