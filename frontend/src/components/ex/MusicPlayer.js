import React from "react";
import './MusicPlayer.css'
import {IoRepeat, IoShuffle} from "react-icons/io5";
import {MdSkipPrevious, MdPlayArrow, MdSkipNext} from "react-icons/md";

function MusicPlayer() {
    return (
        <div className="musicPlayer">
            <div className = 'shuffle'>
                <IoShuffle/>
            </div>
            <div className = "skipPrevious">
                <MdSkipPrevious/>
            </div>
            <div className = "play">
                <MdPlayArrow/>
            </div>
            <div className = "skipNext">
                <MdSkipNext/>
            </div>
            <div className = "repeat">
                <IoRepeat/>
            </div>
        </div>
    )
}

export default MusicPlayer;