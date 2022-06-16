import React,{useEffect,useState} from 'react';
import axios from 'axios';
import "./ResultItem.css"

const ResultItem = (props) => {
    const{platformName, recommend}=props;
    const [resultImg, setResultImg]=useState("");
    const [platform, setplatform] = useState("");
    console.log(platformName.toUpperCase());

    useEffect(() => {

        if(recommend ==="OTT"){
            axios({
                method: "get",
                url: "/ottTest/"+platformName,
                responseType: "json"
            }).then((res)=>{
                console.log(res.data.data.ottName.length);
                setResultImg(res.data.data.ottLogo);
                setplatform(res.data.data.ottName);
                
            });
        }
        else if(recommend === "Music"){
            axios({
                        method: "GET",
                        url: '/musicTest/'+platformName,
                        responseType: "json"
                        })
                        .then((res) => {
                            console.log(res.data.data.ottLogo);
                            setResultImg(res.data.data.ottLogo);
                            setplatform(res.data.data.ottName);
                            
                        })
        }
              
    },[])

    return (
        <div className="recommendPlatform">
        <div className="recommendPlatformName">{platform}</div>
        <div className="resultContent">
            <img src={resultImg} alt="플랫폼 로고"/>
        </div>
        </div>
    );
};

export default ResultItem;