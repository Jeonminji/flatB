import React from 'react'; 
import Header from '../components/Header/Header';
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
                                <div className="compare_hidden"></div>
                            </div>
                        </div>
                    </div>
                    <div className="compare_item">
                        <div className="platform">
                        <img src={watchaimg} alt="test"/>
                            <div className="compare_contents">
                                <div className="compare_platform">WATCHA</div>
                                <div className="compare_hidden"></div>
                            </div>
                        </div>
                    </div>
                    <div className="compare_item">
                        <div className="platform">
                        <img src={wavveimg} alt="test"/>
                            <div className="compare_contents">
                                <div className="compare_platform">WAVVE</div>
                                <div className="compare_hidden"></div>
                            </div>
                        </div>
                    </div>
                    <div className="compare_item">
                        <div className="platform">
                        <img src={tvingimg} alt="test"/>
                            <div className="compare_contents">
                                <div className="compare_platform">TVING</div>
                                <div className="compare_hidden"></div>
                            </div>
                        </div>
                    </div>
                    <div className="compare_item">
                        <div className="platform">
                        <img src={disneyimg} alt="test"/>
                            <div className="compare_contents">
                                <div className="compare_platform">DISNEYPLUS</div>
                                <div className="compare_hidden"></div>
                            </div>
                        </div>
                    </div>
                    <div className="compare_item">
                        <div className="platform">
                        <img src={laftelimg} alt="test"/>
                            <div className="compare_contents">
                                <div className="compare_platform">LAFTEL</div>
                                <div className="compare_hidden"></div>
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
                            <div className="compare_hidden"></div>
                        </div>
                    </div>
                </div>
                <div className="compare_item">
                    <div className="platform">
                    <img src={youtubeimg} alt="test"/>
                        <div className="compare_contents">
                            <div className="compare_platform">YOUTUBEMUSIC</div>
                            <div className="compare_hidden"></div>
                        </div>
                    </div>
                </div>
                <div className="compare_item">
                    <div className="platform">
                    <img src={floimg} alt="test"/>
                        <div className="compare_contents">
                            <div className="compare_platform">FLO</div>
                            <div className="compare_hidden"></div>
                        </div>
                    </div>
                </div>
                <div className="compare_item">
                    <div className="platform">
                    <img src={bugsimg} alt="test"/>
                        <div className="compare_contents">
                            <div className="compare_platform">BUGS</div>
                            <div className="compare_hidden"></div>
                        </div>
                    </div>
                </div>
                <div className="compare_item">
                    <div className="platform">
                    <img src={genieimg} alt="test"/>
                        <div className="compare_contents">
                            <div className="compare_platform">GENIE</div>
                            <div className="compare_hidden"></div>
                        </div>
                    </div>
                </div>
                <div className="compare_item">
                    <div className="platform">
                    <img src={melonimg} alt="test"/>
                        <div className="compare_contents">
                            <div className="compare_platform">MELON</div>
                            <div className="compare_hidden"></div>
                        </div>
                    </div>
                </div>
                
                </div>
        

            </div>
            
        </> 
    ); 
} 

export default ComparePage;
