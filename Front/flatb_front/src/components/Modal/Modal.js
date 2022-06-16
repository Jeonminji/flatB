import React from 'react';
import './Modal.css';

const Modal = (props) => {
  
  const { open, close, header, writer, regdate, contentdelete, contentupdate} = props;

  return (
    // 모달이 열릴때 openModal 클래스가 생성된다.
    <div className={open ? 'openModal modal' : 'modal'}>
      {open ? (
        <section>
          <header>
            <div className={ header ? 'header':"notUse"}>
            {header}
            </div>
            <div className={ writer ? 'header2':"notUse"}>
              모집자:{writer}
            </div>
            <div className={ regdate ? 'header3':"notUse"}>
              모집일자:{regdate}
            </div>
            
            <button className="close" onClick={close}>
              &times;
            </button>
          </header>
          <main>{props.children}</main>
          <div className={contentupdate ? "amendBox":"notUse"}>
            <div className= {contentupdate ? "contentdelete":"notUse"}>
              <button  onClick={contentupdate}>
                수정
              </button>
            </div>
            <div className= {contentdelete ? "contentdelete":"notUse"}>
              <button  onClick={contentdelete}>
                삭제
              </button>
            </div>  
            
          </div>
          
        </section>
      ) : null}
    </div>
  );
};
export default Modal;