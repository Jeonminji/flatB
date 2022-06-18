import React,{useEffect, useState} from 'react'; 
import axios from 'axios';
import Header from '../components/Header/Header';
import CompareOttItem from '../components/Item/CompareOttItem'
import CompareMusicItem from '../components/Item/CompareMusicItem'
import netfliximg from '../img/netflix_logo.jpg'
import watchaimg from '../img/watcha_logo.jpg'
import wavveimg from '../img/wavve_logo.jpg'
import tvingimg from '../img/tving_logo.jpg'
import laftelimg from '../img/laftel_logo.jpg'
import disneyimg from '../img/disneyPlus_logo.jpg'
import melonimg from '../img/melon_logo.jpg'
import youtubeimg from '../img/youtubeMusic_logo.jpg'
import floimg from '../img/flo_logo.jpg'
import spotifyimg from '../img/spotify_logo.jpg'
import bugsimg from '../img/bugs_logo.jpg'
import genieimg from '../img/genie_logo.jpg'
import "./comparePage.css" 

const ComparePage=(props) =>{ 
    const [netflix,setNetflix]=useState({items:[]});
    const [watcha,setWatcha]=useState({items:[]});
    const [wavve,setWavve]=useState({items:[]});
    const [disney,setDisney]=useState({items:[]});
    const [tving,setTving]=useState({items:[]});
    const [laftel,setLaftel]=useState({items:[]});
    const [melon,setMelon]=useState({items:[]});
    const [spotify,setSpotify]=useState({items:[]});
    const [flo,setFlo]=useState({items:[]});
    const [genie,setGenie]=useState({items:[]});
    const [youtube,setYoutube]=useState({items:[]});
    const [bugs,setBugs]=useState({items:[]});


    useEffect(() => {
        axios({method: "GET", url: '/compareOtt/netflix', responseType: "json" })
            .then((res) => { setNetflix({items:res.data.data}); });

        axios({method: "GET", url: '/compareOtt/watcha', responseType: "json" })
            .then((res) => { setWatcha({items:res.data.data}); });
        
        axios({method: "GET", url: '/compareOtt/wavve', responseType: "json" })
            .then((res) => { setWavve({items:res.data.data}); });

        axios({method: "GET", url: '/compareOtt/tving', responseType: "json" })
            .then((res) => { setTving({items:res.data.data}); });
        
        axios({method: "GET", url: '/compareOtt/disneyplus', responseType: "json" })
            .then((res) => { setDisney({items:res.data.data}); });
        
        axios({method: "GET", url: '/compareOtt/laftel', responseType: "json" })
            .then((res) => { setLaftel({items:res.data.data}); });
        
        axios({method: "GET", url: '/compareMusic/melon', responseType: "json" })
            .then((res) => { setMelon({items:res.data.data}); });

        axios({method: "GET", url: '/compareMusic/spotify', responseType: "json" })
            .then((res) => { setSpotify({items:res.data.data}); });

        axios({method: "GET", url: '/compareMusic/youtubemusic', responseType: "json" })
            .then((res) => { setYoutube({items:res.data.data}); });

        axios({method: "GET", url: '/compareMusic/flo', responseType: "json" })
            .then((res) => { setFlo({items:res.data.data}); });
        
        axios({method: "GET", url: '/compareMusic/genie', responseType: "json" })
            .then((res) => { setGenie({items:res.data.data}); });
        
        axios({method: "GET", url: '/compareMusic/bugs', responseType: "json" })
            .then((res) => { setBugs({items:res.data.data}); });
        
             
    },[])

    return ( 
        <> 
            <Header isLogin={props.isLogin} loginCallBack={props.loginCallBack}/> 
            <div className="compare_wrap">
            <div className="wrapper">
 
                    <div className="compare_item">
                        <div className="platform">
                        <img src={netfliximg} alt="test"/>
                            <div className="compare_contents">
                                <div className="compare_platform">NEFLIX</div>
                                <div className="compare_hidden">{netflix.items.map(item=><CompareOttItem key={item.id} item={item}/>)}</div>
                            </div>
                        </div>
                    </div>
                    <div className="compare_item">
                        <div className="platform">
                        <img src={watchaimg} alt="test"/>
                            <div className="compare_contents">
                                <div className="compare_platform">WATCHA</div>
                                <div className="compare_hidden">{watcha.items.map(item=><CompareOttItem key={item.id} item={item}/>)}</div>
                            </div>
                        </div>
                    </div>
                    <div className="compare_item">
                        <div className="platform">
                        <img src={wavveimg} alt="test"/>
                            <div className="compare_contents">
                                <div className="compare_platform">WAVVE</div>
                                <div className="compare_hidden">{wavve.items.map(item=><CompareOttItem key={item.id} item={item} />)}</div>
                            </div>
                        </div>
                    </div>
                    <div className="compare_item">
                        <div className="platform">
                        <img src={tvingimg} alt="test"/>
                            <div className="compare_contents">
                                <div className="compare_platform">TVING</div>
                                <div className="compare_hidden">{tving.items.map(item=><CompareOttItem key={item.id} item={item} />)}</div>
                            </div>
                        </div>
                    </div>
                    <div className="compare_item">
                        <div className="platform">
                        <img src={disneyimg} alt="test"/>
                            <div className="compare_contents">
                                <div className="compare_platform">DISNEYPLUS</div>
                                <div className="compare_hidden">{disney.items.map(item=><CompareOttItem key={item.id} item={item} type={"different"}/>)}</div>
                            </div>
                        </div>
                    </div>
                    <div className="compare_item">
                        <div className="platform">
                        <img src={laftelimg} alt="test"/>
                            <div className="compare_contents">
                                <div className="compare_platform">LAFTEL</div>
                                <div className="compare_hidden">{laftel.items.map(item=><CompareOttItem key={item.id} item={item} />)}</div>
                            </div>
                        </div>
                    </div>
                    
            </div>

            <div className="wrapper">
                
                <div className="compare_item">
                    <div className="platform">
                    <img src={spotifyimg} alt="test"/>
                        <div className="compare_contents">
                            <div className="compare_platform">SPOTIFY</div>
                            <div className="compare_hidden">{spotify.items.map(item=><CompareMusicItem key={item.id} item={item} type={"diffrent"}/>)}</div>
                        </div>
                    </div>
                </div>
                <div className="compare_item">
                    <div className="platform">
                    <img src={youtubeimg} alt="test"/>
                        <div className="compare_contents">
                            <div className="compare_platform">YOUTUBEMUSIC</div>
                            <div className="compare_hidden">{youtube.items.map(item=><CompareMusicItem key={item.id} item={item} type={"diffrent"}/>)}</div>
                        </div>
                    </div>
                </div>
                <div className="compare_item">
                    <div className="platform">
                    <img src={floimg} alt="test"/>
                        <div className="compare_contents">
                            <div className="compare_platform">FLO</div>
                            <div className="compare_hidden">{flo.items.map(item=><CompareMusicItem key={item.id} item={item} />)}</div>
                        </div>
                    </div>
                </div>
                <div className="compare_item">
                    <div className="platform">
                    <img src={bugsimg} alt="test"/>
                        <div className="compare_contents">
                            <div className="compare_platform">BUGS</div>
                            <div className="compare_hidden">{bugs.items.map(item=><CompareMusicItem key={item.id} item={item} />)}</div>
                        </div>
                    </div>
                </div>
                <div className="compare_item">
                    <div className="platform">
                    <img src={genieimg} alt="test"/>
                        <div className="compare_contents">
                            <div className="compare_platform">GENIE</div>
                            <div className="compare_hidden">{genie.items.map(item=><CompareMusicItem key={item.id} item={item} />)}</div>
                        </div>
                    </div>
                </div>
                <div className="compare_item">
                    <div className="platform">
                    <img src={melonimg} alt="test"/>
                        <div className="compare_contents">
                            <div className="compare_platform">MELON</div>
                            <div className="compare_hidden">{melon.items.map(item=><CompareMusicItem key={item.id} item={item} />)}</div>
                        </div>
                    </div>
                </div>
                
                </div>
        

            </div>
            
        </> 
    ); 
} 

export default ComparePage;
