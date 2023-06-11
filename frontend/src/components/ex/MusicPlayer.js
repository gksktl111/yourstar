import React, { useState } from "react";
import "./MusicPlayer.css";
import { IoRepeat, IoShuffle } from "react-icons/io5";
import { MdSkipPrevious, MdPlayArrow, MdSkipNext } from "react-icons/md";
import {VscChromeClose} from "react-icons/vsc";

function MusicPlayer() {
    const [isOpen, setIsOpen] = useState(false);
    const [currentImage, setCurrentImage] = useState("/assets/img/14.jpg");

    const handleSongClick = (img) => {
        setCurrentImage(img);
    };

    const toggleMusicPlayer = () => {
        setIsOpen(!isOpen);
    };

    const songs = [
        { title: "노래1", artist: "가수1", img: "/assets/img/1.jpg" },
        { title: "노래2", artist: "가수2", img: "/assets/img/2.jpg" },
        { title: "노래3", artist: "가수3", img: "/assets/img/3.jpg" },
        { title: "노래4", artist: "가수4", img: "/assets/img/4.jpg" },
        { title: "노래5", artist: "가수5", img: "/assets/img/5.jpg" },
        { title: "노래6", artist: "가수6", img: "/assets/img/6.jpg" },
        { title: "노래7", artist: "가수7", img: "/assets/img/7.jpg" },
        { title: "노래8", artist: "가수8", img: "/assets/img/8.jpg" },
        { title: "노래9", artist: "가수9", img: "/assets/img/9.jpg" },
        { title: "노래10", artist: "가수10", img: "/assets/img/10.jpg" },
        { title: "노래11", artist: "가수11", img: "/assets/img/11.jpg" },
        { title: "노래12", artist: "가수12", img: "/assets/img/12.jpg" },
        { title: "노래13", artist: "가수13", img: "/assets/img/13.jpg" },
        { title: "노래14", artist: "가수14", img: "/assets/img/14.jpg" },
        { title: "노래15", artist: "가수15", img: "/assets/img/15.jpg" },
        { title: "노래16", artist: "가수16", img: "/assets/img/16.jpg" },
        { title: "노래17", artist: "가수17", img: "/assets/img/17.jpg" },
        { title: "노래18", artist: "가수18", img: "/assets/img/18.jpg" },
        { title: "노래19", artist: "가수19", img: "/assets/img/19.jpg" },
        { title: "노래20", artist: "가수20", img: "/assets/img/20.jpg" },
    ];


    return (
        <>
            <div className={`aa ${isOpen ? "open" : ""}`}>
                <div className="music-img">
                    {/*// 현재 이미지 소스를 업데이트합니다.*/}
                    <img src={currentImage} />
                </div>
                <div className="music-list">
                    <div className="music-list-title">
                        <h4>현재재생목록</h4>
                    </div>
                    {songs.map((song, index) => (
                        <div key={index}
                             className="song-item"
                             onClick={() => handleSongClick(song.img)}>
                            <img
                                src={song.img}
                                alt={song.title}
                                style={{ width: "50px", height: "50px" }}
                            />
                            <div className="song-info">
                                <span>{song.title}</span>
                                <p style={{color : "grey"}}>{song.artist}</p>
                            </div>
                            <div className = "song-delete">
                                <VscChromeClose/>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
            <div className="musicPlayer" onClick={toggleMusicPlayer}>
                <div className="shuffle">
                    <IoShuffle />
                </div>
                <div className="skipPrevious">
                    <MdSkipPrevious />
                </div>
                <div className="play">
                    <MdPlayArrow />
                </div>
                <div className="skipNext">
                    <MdSkipNext />
                </div>
                <div className="repeat">
                    <IoRepeat />
                </div>
            </div>
        </>
    );
}

export default MusicPlayer;
