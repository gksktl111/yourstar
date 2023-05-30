import React, {useCallback, useState} from 'react';
import "./PeedMore.css"
import {useDispatch} from "react-redux";
import {BsBookmarkDash, BsBookmarkDashFill, BsDot, BsThreeDots} from "react-icons/bs";
import {commentModalOff, optionModalOff, optionModalOn} from "../../store/Store";
import {AiFillStar, AiOutlineComment, AiOutlineShareAlt, AiOutlineStar} from "react-icons/ai";
import PeedMoreComment from "./PeedMoreComment";

const PeedMore = () => {
    const dispatch = useDispatch();

    // 팔로우 여부에따라 팔로우 버튼 on/off
    const [isFollow, setIsFollow] = useState(true);
    // 좋아요 여부
    const [isLiked, setIsLiked] = useState(false);
    // 피드 저장 여부
    const [isSaved, setIsSaved] = useState(false);
    // 댓글 적기 시작하면 "게시" div 보여주기
    const [saveComment, setSaveComment] = useState("");
    // 댓글 좋아요 클릭
    const handleLikeClick = () => {
        if (isLiked === true) {
            setIsLiked(false);
            return;
        }

        setIsLiked(true);
    };


    // 저장 클릭
    const handleSaveClick = () => {
        if (isSaved === true) {
            setIsSaved(false);
            return;
        }

        setIsSaved(true);
    };

    const handleModalOff = useCallback(
        (e) => {
            // 클릭이벤트가 모달 내부에서 발생한 경우(e.stopPropagation() 으로 버블링 차단한 경우)
            if (e.currentTarget !== e.target) {
                return;
            }

            // 클릭이벤트가 배경에서 발생한 경우
            dispatch(commentModalOff());
        },
        [dispatch],
    );

    return (
        <div className="peed_more_background"
        onClick={handleModalOff}>
            <div className="peed_more_container">
                <div className="peed_more_left">
                    <img src={'/assets/img/6.jpg'}
                         className="peed_more_media"
                         alt={"asd"}/>
                </div>
                <div className="peed_more_right">
                    <div className={"peed_more_top_section"}>
                        {/*두개 누르면 상대방 프로필로 넘어가기*/}
                        <img src="/assets/img/3.jpg" alt="오류" className="peed_more_profile"/>

                        {/*여기서 개인별 프로필 주소 */}
                        <a href="http://localhost:3000/aad" className="peed_more_name">
                            alsrb_1214
                        </a>

                        {/*팔로우 버튼 클릭시 팔로우 하고 사라짐*/}
                        <div style={{marginLeft: "5px"}}>
                            {isFollow ? <div style={{width: "80px"}}>
                                <BsDot/>
                                <span className="follow_btn">팔로우</span>
                            </div> : null}
                        </div>
                        <button className='peed_more_option'
                                onClick={() => {
                                    dispatch(optionModalOn())
                                }
                                }>
                            <BsThreeDots style={{marginRight: "20px"}}/>
                        </button>
                    </div>

                    {/*게시글 글 보여준 후 그 밑으로는 댓글 들 보여주기*/}
                    <div className={"peed_mor_middle_section"}>
                        {/*리스트 형태로 만듬
                         나중에는 데이터 받아와서 map으로 데이터 넣어서 반복 출력
                         여기서는 무한 스크롤 말고 아래 버튼 만들어서 불러오기*/}
                        <ul className="peed_comment_list">
                            <div className="peed_more_context">
                                {/*유저 프사*/}
                                <img src="/assets/img/3.jpg" alt="오류"
                                     className="peed_more_profile"/>
                                <p className="peed_more_text">
                                    {/*유저이름*/}
                                    <a href="http://localhost:3000/aad"
                                       className="peed_more_name">
                                        alsrb_1214
                                    </a>
                                    {/*여기에 글을 작성할겁니다여기에
                                    <br/> 은 따로 넣어야됨*/}
                                    여기에 글을 작성할겁니다여기에 <br/>글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을
                                    작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다
                                </p>
                            </div>

                            {/*다음 컴포넌트로 데이터 를 넘겨서 피드의 댓글을 띄움*/}
                            {/*state에 데이터 넣고 map으로 돌려서 출력하기*/}

                            <PeedMoreComment/>
                            <PeedMoreComment/>
                            <PeedMoreComment/>
                            <PeedMoreComment/>
                            <PeedMoreComment/>
                            <PeedMoreComment/>
                            <PeedMoreComment/>
                            <PeedMoreComment/>
                        </ul>
                    </div>

                    {/*바텀 섹션*/}

                    <div className="peed_more_bottom_section">
                        <button className={`peed_more_like_icon ${isLiked ? "clicked" : ""}`}
                                onClick={handleLikeClick}>
                            {isLiked ? <AiFillStar className='liked_star'/> : <AiOutlineStar/>}
                        </button>
                        <button className='peed_more_comment_icon'>
                            <AiOutlineComment/>
                        </button>
                        <button className='peed_more_share_icon'>
                            <AiOutlineShareAlt/>
                        </button>
                        <button className="peed_more_save_icon" onClick={handleSaveClick}>
                            {isSaved ?
                                <BsBookmarkDashFill/> :
                                <BsBookmarkDash/>}
                        </button>
                    </div>

                    <div className="peed_more_liked_user">
                    <span>
                        <span style={{fontWeight: "bold", cursor: "pointer"}}>qweas123</span>
                        <span> 님 외</span>
                        <span style={{fontWeight: "bold", cursor: "pointer"}}> 여러 명</span>
                        <span>이 좋아합니다.</span>
                    </span>
                    </div>

                    <div className="peed_more_time">
                        {/*게시된 시간 표시*/}
                        <span style={{marginLeft: "15px"}}>
                            2시간 전
                        </span>
                    </div>

                    <div className="peed_textarea_container">
                        <textarea className="peed_textarea"
                                 placeholder={"댓글 달기..."}
                                 onChange={(e) =>
                                    setSaveComment(e.target.value)}>
                        </textarea>
                        {/*댓글 작성할때 게시 버튼 나오도록 만듬*/}
                            {saveComment !== "" ?
                                <div className="peed_textarea_post"
                                >
                                    게시
                                </div> : null
                            }
                    </div>
                </div>

            </div>
        </div>
    );
};

export default PeedMore;