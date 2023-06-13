import React, {useEffect, useState} from 'react';
import "./Profile.css"
import {IoMdSettings} from "react-icons/io";
import axios from "axios";
import {GrAddCircle} from "react-icons/gr";

const Profile = () => {
        // useState 추가
        const [profileInfo, setProfileInfo] = useState({});
        const [posts, setPosts] = useState([]);
        const [postPage, setPostPage] = useState(0);

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

        // 프로필 데이터
        const loadProfile = async () => {
            await axios.post('/getUserprofile', {}, {
                headers: {authorization: localStorage.getItem("token")},
            }).then((response) => {
                // response data를 객체로 저장
                const dataList = {
                    profileImage: response.data.profileImage,
                    userName: response.data.userName,
                    postCount: response.data.postCount,
                    followingCount: response.data.followingCount,
                    followerCount: response.data.followerCount,
                    introduce: response.data.introduce,
                };

                setProfileInfo(dataList);
            }).catch(function (error) {
                console.log('실패함', error);
            })
        };

        // 미디어 타입 구분
        function getMediaType(metaData) {
            const magicNumbers = [
                {type: 'image/jpeg', magic: '/9j/4'},
                {type: 'image/png', magic: 'iVB'},
                {type: 'video/mp4', magic: 'AAAA'},
            ];

            const contentType = magicNumbers.find((content) => metaData.startsWith(content.magic));

            if (contentType) {
                return contentType.type.split('/')[0];
            } else {
                return 'unknown';
            }
        }

        // 내 포스트 데이터
        const loadPost = async (page) => {
            console.log("현재 페이지 값", postPage)
            await axios.post('/getUserPost', {
                page
            }, {
                headers: {authorization: localStorage.getItem("token")},
            }).then((response) => {
                console.log(response.data);
                // response data를 배열로 변환하고 포스트 데이터를 추가하여 상태를 업데이트합니다.

                // response data를 내림차순으로 정렬합니다.
                const sortedData = response.data.sort((a, b) => b.id - a.id);

                const newPosts = sortedData.map((post) => {
                    const mediaType = getMediaType(post.meta);

                    return {
                        id: post.id,
                        mediaType: mediaType,
                        data: post.meta,
                    };
                });

                setPosts([...posts, ...newPosts]);
            }).catch(function (error) {
                console.log("실패함", error);
            })
        };

        // 프로필 데이터들을 호출하는 useEffect
        useEffect(() => {
            loadProfile();
            loadPost(postPage);
        }, []);

        const morePost = () => {
            const nextPage = postPage + 1;

            // 서버에 nextPage 값을 전달하고 포스트를 가져옵니다.
            loadPost(nextPage);

            setPostPage(nextPage);
        };

        return (
            <>

                {/*프로필 헤더*/}
                <header className="profile_header">
                    <div className="profile_img_div">
                        <img className="profile_img"
                             src={profileInfo.profileImage ? `data:image/jpeg;base64,${profileInfo.profileImage}` : "/assets/img/profile.png"}
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
                {/*포스트 저장됨 플레리스트 선택 섹션*/}
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
                            {posts.map((item) => (
                                <div className="profile_card" key={item.id}>
                                    {item.mediaType === "video" ? (
                                        <video className="profile_card_video"
                                               id={item.id} controls>
                                            <source src={`data:video/mp4;base64,${item.data}`} type="video/mp4"/>
                                        </video>
                                    ) : (
                                        <img
                                            className="profile_card_img"
                                            id={item.id}
                                            src={`data:image/jpeg;base64,${item.data}`}
                                            alt="불러오기 실패 ㅜㅠ"
                                        />
                                    )}
                                </div>
                            ))}
                        </div>
                    ) : null}
                    <GrAddCircle
                        className={"data_load_btn"}
                        onClick={morePost}
                    />
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
                </div>
            </>
        )
            ;
    }
;

export default Profile;