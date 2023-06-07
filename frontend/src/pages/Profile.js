import React, {useEffect, useState} from 'react';
import "./Profile.css"
import {IoMdSettings} from "react-icons/io";
import axios from "axios";

const Profile = () => {
    // useState 추가
    const [profileInfo, setProfileInfo] = useState({});
    const [posts, setPosts] = useState([]);

    const [isPost, setIsPost] = useState(true);
    const [isSaved, setIsSaved] = useState(false);
    const [isPlaylist, setIsPlaylist] = useState(false);

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

    const loadProfile = async () => {
        console.log(localStorage.getItem("token"))

        await axios.post('/getUserprofile', {
            Authorization : localStorage.getItem("token"),
        }).then((response) => {
            if (response.data !== null) {
                console.log(response.data)
            } else {
                //setEmailSearchResult(<span style={style2}>존재하는 회원정보입니다.</span>);
            }
        }).catch(function (error) {
            console.log('실패함', error)
        })
    };

    // loadProfile를 호출하는 useEffect
    useEffect(() => {
        loadProfile();
    }, []);

    return (
        <>
            <header className="profile_header">
                <div className="profile_img_div">
                    <img className="profile_img"
                         src={profileInfo.profileImage || "/assets/img/basic_img.jpg"}
                         alt={"asd"}/>
                </div>
                <div className="profile_info">
                    <div className="profile_info_top">
                        {/*// 사용자 이름 출력*/}
                        <span className="profile_name">{profileInfo.userName}</span>
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
                            <span className="profile_num">{profileInfo.postCount}</span>
                        </a>
                        <a className="profile_follower">
                            팔로워
                            <span className="profile_num">{profileInfo.followerCount}</span>
                        </a>
                        <a className="profile_follow">
                            팔로우
                            <span className="profile_num">{profileInfo.followingCount}</span>
                        </a>
                    </div>
                    <div className="profile_info_bottom">
                        {/*글자제한있음*/}
                        {profileInfo.introduce}
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
            {/*<div className="profile_bottom_section">*/}
            {/*    /!*각 버튼에 따라서 다른 화면을 보여줘야함*!/*/}
            {/*    /!*포스트 부분*!/*/}
            {/*    /!*포스트 부분*!/*/}
            {/*    {isPost ? (<>*/}
            {/*            <div className="profile_card_list">*/}
            {/*                {Object.values(peedEx).map((item, index) => {*/}
            {/*                    return (*/}
            {/*                        <div className="profile_card" key={index}>*/}
            {/*                            <img className="profile_card_img"*/}
            {/*                                 src={item.imageUrl || `/assets/img/${index + 1}.jpg`}*/}
            {/*                                 alt={"asd"}/>*/}
            {/*                        </div>*/}
            {/*                    );*/}
            {/*                })}*/}
            {/*            </div>*/}
            {/*        </>*/}
            {/*    ) : null}*/}
            {/*    /!*저장됨 부분*!/*/}
            {/*    {isSaved ? (*/}
            {/*        <div className="profile_card_list">*/}
            {/*            {Object.values(savedEx).map((item, index) => {*/}
            {/*                return (*/}
            {/*                    <div className="profile_card" key={index}>*/}
            {/*                        <img className="profile_card_img"*/}
            {/*                             src={`/assets/img/${index + 5}.jpg`}*/}
            {/*                             alt={"asd"}/>*/}
            {/*                    </div>*/}
            {/*                );*/}
            {/*            })}*/}
            {/*        </div>*/}
            {/*    ) : null}*/}
            {/*    /!*플레이리스트 부분*!/*/}
            {/*    {isPlaylist ? (*/}
            {/*        <div className="profile_card_list">*/}
            {/*            {Object.values(playlistEx).map((item, index) => {*/}
            {/*                return (*/}
            {/*                    <div className="profile_card" key={index}>*/}
            {/*                        <img className="profile_card_img"*/}
            {/*                             src={`/assets/img/${index + 10}.jpg`}*/}
            {/*                             alt={"asd"}/>*/}
            {/*                    </div>*/}
            {/*                );*/}
            {/*            })}*/}
            {/*        </div>*/}
            {/*    ) : null}*/}
            {/*</div>*/}
        </>
    );
};

export default Profile;