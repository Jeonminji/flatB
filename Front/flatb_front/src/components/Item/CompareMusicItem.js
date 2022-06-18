import React from 'react';

const CompareMusicItem = (props) => {
  // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
  const {musicPlan, musicPrice, musicDiscount, musicPlandescription, musicEtc} = props.item;

  return (
    <>
    {props.type ? <div className="compare_info compare_text_color">
      {musicPlandescription ? <div><span className="compare_detailInfo">할인 혜택 : </span> <span>{musicPlandescription}</span></div>:null}
        <div>{musicPlan ? <span className="compare_detailInfo">{musicPlan}:</span> :null} <span>{musicPrice}</span></div> 
        {musicDiscount ? <div className="compare_detailInfo">- {musicDiscount}</div>:null}
        {musicEtc ? <div><span className="compare_detailInfo">기타 정보 : </span> <span>{musicEtc}</span></div>:null}
      </div> 
      :
    <div className="compare_info">
        <div>{musicPlan ? <span className="compare_detailInfo">{musicPlan}:</span> :null} <span>{musicPrice}</span></div> 
        {musicPlandescription ? <div><span>- {musicPlandescription}</span></div>:null}
        {musicDiscount ? <div><span className="compare_detailInfo">할인 가격 : </span><span>{musicDiscount}</span></div> :null}
        {musicEtc ? <div><span className="compare_detailInfo">기타 정보 : </span> <span>{musicEtc}</span></div>:null}  
    
    </div> 
      }
    
    </>
  );
};
export default CompareMusicItem;