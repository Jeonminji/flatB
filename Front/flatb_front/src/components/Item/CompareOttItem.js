import React from 'react';

const CompareOttItem = (props) => {
  // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
  const {ottPlan, ottPrice, ottPlaybacknum, ottQuality, ottEtc} = props.item;

  return (
    <>
    {props.type ?
      <div className="compare_info">
      <div>{ottPrice ? <span className="compare_detailInfo">{ottPrice}</span> :null}</div>  
      {ottPlaybacknum ? <div><span className="compare_detailInfo">동시 재생 가능 수 : </span> <span>{ottPlaybacknum}</span></div>:null}
      {ottQuality ? <div><span className="compare_detailInfo">최고 화질 : </span> <span>{ottQuality}</span></div>:null}
      {ottEtc ? <div><span className="compare_detailInfo">기타 정보 : </span> <span>{ottEtc}</span></div>:null}
    </div>:
    <div className="compare_info">
    <div>{ottPlan ? <span className="compare_detailInfo">{ottPlan}:</span> :null} <span>{ottPrice}</span></div>  
    {ottPlaybacknum ? <div><span className="compare_detailInfo">동시 재생 가능 수 : </span> <span>{ottPlaybacknum}</span></div>:null}
    {ottQuality ? <div><span className="compare_detailInfo">최고 화질 : </span> <span>{ottQuality}</span></div>:null}
    {ottEtc ? <div><span className="compare_detailInfo">기타 정보 : </span> <span>{ottEtc}</span></div>:null}
  </div>}
    
    
    </>
  );
};
export default CompareOttItem;