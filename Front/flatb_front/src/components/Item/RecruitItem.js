import React,{useState, useCallback,useEffect} from 'react';
import axios from 'axios';

import "./RecruitItem.css";
import Modals from '../Modal/Modal';
import RecruitForm from '../../components/Form/RecruitForm'

const RecruitItem = (props) =>{
    const {boardNo,ottLogo, title, content, platformname,totalcount, currentcount, usedateStart, usedateEnd, contact, nickname, regdate
     } = props.item;
    const rodWidth = (currentcount/totalcount)*100+"%";
    const [recruitmentSituation, setRecruitmentSituation] = useState();

    useEffect(() =>{
        if(totalcount === currentcount){
            setRecruitmentSituation("모집 완료");
        }
        else{
            setRecruitmentSituation("모집 중");
        }
        
    })

    return(
        
        <>
            {/* 모집 recruit_content 박스 */}
            <div className="recruitContentBoxCover" onClick={openrecruit_contentModal}>
                <div className="recruitContentBox">
                    <div className="recruitContentHeader">
                        <div className="recruitImageBox">
                            <div className="recruitImageBox2">
                                <div className="recruitImageBox3">
                                    <img className="recruitImg"src={ottLogo} alt="recruit_category_img"/>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                    <div className="recruitContBox">
                        <div className="recruitContSituation">
                            <span>{recruitmentSituation}</span>
                        </div>
                        <div className="recruitContBox2">
                            <div className="recruitTitle">{title}</div>
                            <div className="recruitPeriod">{usedateStart} ~ {usedateEnd}</div>
                            <div className="recruitPersonnelBox">
                                <div className="recruitPersonnelRod1">
                                    <div className="recruitPersonnelRod2" style={{width:rodWidth}}></div>
                                </div>
                            
                                <div className="recruitPersonnel">
                                    <span>{currentcount}/{totalcount}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
       {modalrecruit_contentOpen ? 
       <Modals open={modalrecruit_contentOpen} close={closerecruit_contentModal}  writer={nickname} regdate={regdate} contentdelete={recruit_contentDelete} contentupdate={openUpdateModal} >
        <div className="recruit_modal_content_box">
            <div className="recruit_modal_img_box">
                <div><img src={ottLogo} alt="recruit_category_img"/></div>
            </div>
            <div className="recruit_modal_content_box2">
                <div className="recruit_modal_note">플랫폼 명: <span>{platformname}</span> </div>
                <div className="recruit_modal_note">모집 인원: <span>{totalcount}</span> </div>
                <div className="recruit_modal_note">사용 기간: <span>{usedateStart} ~ {usedateEnd}</span> </div>
                <div className="recruit_modal_note">연락 수단: <span>{contact}</span> </div>
                <div className="recruit_modal_note">한 마디</div>
                <div className="recruit_modal_oneWord">{content}</div>
            </div>
        </div>
        </Modals> : null} 
        { modalUpdateOpen ? 
        <Modals open={modalUpdateOpen} close={closeUpdateModal} header="게시글 수정">
            <RecruitForm 
            boardNo = {boardNo}
            title = {title}
            platformname = {platformname}
            writer_id = {nickname}
            currentcount = {currentcount}
            totalcount = {totalcount}
            contact = {contact}
            content = {content}
            usedate_start = {usedateStart}
            usedate_end = {usedateEnd}
            regdate={regdate}
            />
        </Modals> : null}

        
   </>
    );
};
export default RecruitItem;