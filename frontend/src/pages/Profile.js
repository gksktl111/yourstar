import React, {useState} from 'react';
import "./Profile.css"
import {IoMdSettings} from "react-icons/io";

const Profile = () => {

    const [isPost, setIsPost] = useState(true);
    const [isSaved, setIsSaved] = useState(false);
    const [isPlaylist, setIsPlaylist] = useState(false);

    const post = 10;
    const Follower = 420;
    const Follow = 113;

    const handlePostClick = () => {

        setIsPost(true);
        setIsSaved(false);
        setIsPlaylist(false);
    }

    const handleSavedClick = () => {

        setIsPost(false);
        setIsSaved(true);
        setIsPlaylist(false);
    }

    const handlePlaylistClick = () => {

        setIsPost(false);
        setIsSaved(false);
        setIsPlaylist(true);
    }

    const peedEx = {
        0: "첫번째입니다",
        1: "두번째입니다",
        2: "세번째입니다",
        3: "네번째입니다",
        4: "다섯번째입니다",
        5: "여섯번째입니다",
        6: "일곱번째입니다",
        7: "여덟번째입니다",
        8: "아홉번째입니다",
        9: "열번째입니다",
    };

    const savedEx = {
        5: "첫번째입니다",
        6: "두번째입니다",
        7: "세번째입니다",
        8: "네번째입니다",
        9: "다섯번째입니다",
    };

    const playlistEx = {
        5: "첫번째입니다",
        6: "두번째입니다",
        7: "세번째입니다",
        8: "네번째입니다",
        9: "다섯번째입니다",
    };


    return (
        <>
            <header className="profile_header">
                <div className="profile_img_div">
                    <img className="profile_img"
                         src="/assets/img/3.jpg"
                         alt={"asd"}/>
                </div>
                <div className="profile_info">
                    <div className="profile_info_top">
                            <span className="profile_name">
                                alsrb_1214
                            </span>
                        <button className="profile_edit">
                            프로필 편집
                        </button>
                        <button className="profile_option">
                            <IoMdSettings/>
                        </button>
                    </div>
                    <div className="profile_info_middle">
                        <a>
                            게시물
                            <span className="profile_num">{post}</span>
                        </a>
                        <a className="profile_follower">
                            팔로워
                            <span className="profile_num">{Follower}</span>
                        </a>
                        <a className="profile_follow">
                            팔로우
                            <span className="profile_num">{Follow}</span>
                        </a>
                    </div>
                    <div className="profile_info_bottom">
                        {/*글자제한있음*/}
                        여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에
                        소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글여기에 소개글
                    </div>
                </div>
            </header>

            <div className="profile_middle_section">
                <a className={`profile_my_post ${isPost ? "clicked" : ""}`}
                   onClick={() => {
                       handlePostClick()
                   }}>
                    게시물
                </a>
                <a className={`profile_my_saved ${isSaved ? "clicked" : ""}`}
                   onClick={() => {
                       handleSavedClick()
                   }}>
                    저장됨
                </a>
                <a className={`profile_my_playlist ${isPlaylist ? "clicked" : ""}`}
                   onClick={() => {
                       handlePlaylistClick()
                   }}>
                    플레이리스트
                </a>
            </div>

            <div className="profile_bottom_section">
                {/*각 버튼에 따라서 다른 화면을 보여줘야함*/}
                {/*포스트 부분*/}
                {/*포스트 부분*/}
                {isPost ? (
                    <div className="profile_card_list">
                        {Object.values(peedEx).map((item, index) => {
                            return (
                                <div className="profile_card" key={index}>
                                    <img className="profile_card_img"
                                         src={`/assets/img/${index+1}.jpg`}
                                         alt={"asd"}/>
                                </div>
                            );
                        })}
                    </div>
                ) : null}
                {/*저장됨 부분*/}
                {isSaved ? (
                    <div className="profile_card_list">
                        {Object.values(savedEx).map((item, index) => {
                            return (
                                <div className="profile_card" key={index}>
                                    <img className="profile_card_img"
                                         src={`/assets/img/${index+5}.jpg`}
                                         alt={"asd"}/>
                                </div>
                            );
                        })}
                    </div>
                ) : null}
                {/*플레이리스트 부분*/}
                {isPlaylist ? (
                    <div className="profile_card_list">
                        {Object.values(playlistEx).map((item, index) => {
                            return (
                                <div className="profile_card" key={index}>
                                    <img className="profile_card_img"
                                         src={`/assets/img/${index+10}.jpg`}
                                         alt={"asd"}/>
                                </div>
                            );
                        })}
                    </div>
                ) : null}
            </div>
        </>
    );
};

export default Profile;