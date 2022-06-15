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

    // data get
    const[recruitItem, setRecruitItem] = useState({items:[]});
    const getDB =  async () => {

        try {
            await axios({
            method: "GET",
            url: '/recruitmentOtt',
            responseType: "json"
            })
            .then((res) => {
                console.log(res);
                
                setRecruitItem({items:res.data.data});
                
            })
        } catch (err) {
                console.error(err)
            }

              
    }

    // 최초 한번 실행
    useEffect(()=>{

        getDB();
    
    },[])

    // 페이징 처리
    const [limit, setLimit] = useState(8); //한 페이지에 8개
    const [page, setPage] = useState(1);
    const offset = (page - 1) * limit;

       // 내글 보기
       const myContentGet =()=>{
       
        axios({
            method: "get",
            url: "/recruitmentOtt/my",
            responseType: "json"
        }).then((res)=>{
            console.log(res.data.data);
            setRecruitItem({items:res.data.data});
        });
        
    }

    const situationGet =()=>{
       
        axios({
            method: "get",
            url: "/recruitmentOtt?recruiting=1",
            responseType: "json"
        }).then((res)=>{
            console.log(res.data.data);
            setRecruitItem({items:res.data.data});
        });
        
    }
    

    const checkOnlyOne = (checkThis, checked) => {
        const checkboxes = document.getElementsByClassName('checkBox')
       
        if(checked){
            for (let i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i] !== checkThis) {
                    checkboxes[i].checked = false
                    
                }
            }
            if(checkThis.name==="my"){
                myContentGet();
            }
            else{
                situationGet();
            }
        }
        else{
            getDB();
        }
      }

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
                        <Pagination
                            total={recruitItem.items.length}
                            limit={limit}
                            page={page}
                            setPage={setPage}
                        />
                    </div>
                </div>
            </div>
        </div>
    </>
    ); 
} 

export default Recruitment;