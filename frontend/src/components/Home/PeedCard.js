import React, {useEffect, useState} from 'react';
import "./PeedCard.css";
import {BsBookmarkDash, BsBookmarkDashFill, BsDot, BsSave, BsThreeDots} from "react-icons/bs";
import {AiFillStar, AiOutlineComment, AiOutlineShareAlt, AiOutlineStar} from "react-icons/ai";
import PeedOption from "./PeedOption";
import {useDispatch, useSelector} from "react-redux";
import {commentModalOn, optionModalOn} from "../../store/Store";
import PeedMore from "./PeedMore";

const PeedCard = () => {
    // 게시글의 게시 시간
    // 국내 시간으로 변환 할려면 9시간 더해야됨
    const publishedTime = new Date('2023-05-20T12:34:56Z');

    // 현재 시간
    const now = new Date();

    // 경과 시간 계산
    const elapsedTime = Math.floor((now.getTime() - publishedTime.getTime()) / 1000);
    const elapsedHours = Math.floor(elapsedTime / 3600);

    // 좋아요 여부
    const [isLiked, setIsLiked] = useState(false);
    // 피드 저장 여부
    const [isSaved, setIsSaved] = useState(false);
    // "더보기" 클릭 여부
    const [showFullText, setShowFullText] = useState(false);
    const [isLongText, setIsLongText] = useState(false);
    // 댓글 적기 시작하면 "게시" div 보여주기
    const [saveComment, setSaveComment] = useState("");

    // 피드 옵션 관련
    let state = useSelector((state) => {return state});
    let dispatch = useDispatch();

    // 좋아요 클릭
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

    // 본문 더보기 클릭
    const handleToggleText = () => {
        setShowFullText(!showFullText);
    };

    // 피드텍스트 길이 계산
    const getTextHeight = (text) => {
        const lineHeight = 20;
        const lines = text.split('\n').length;
        const extraHeight = 10;
        return lineHeight * lines + extraHeight;
    };

    // 본문 예시
    const peed_text = "이 밴드 \n정말 좋아요! 있습니다. 이 밴드 정말 좋아요! 추천합니다! 여러 줄의 본문이 길어질 수 있습니다. 이 밴드 정말 좋아요! 추천합니다! 여러 줄의 본문이 길어질 수 있습니다.";

    // 피드텍스트 길이 계산 후 isLongText 상태 업데이트
    useEffect(() => {
        if (peed_text.length > 20) {
            setIsLongText(true);
        }
    }, [peed_text]);

    useEffect(() => {
        const element = document.querySelector('.peed_text');
        setIsLongText(peed_text.split("\n").length > 1 || element.scrollHeight > 60);
    }, []);


    return (
        <div className="peed_container">
            <div className='peed'
                 // 본문의 길이 따라서 피드의 길이가 정해짐
                 style={{
                     height: showFullText
                         ? getTextHeight(peed_text) + 670
                         : 670,
                 }}>
                {/*피드 상단*/}
                <div className="peed_top_section">
                    <img src="/assets/img/3.jpg" alt="오류" className="peed_profile"/>
                    <span className="user_name">alsrb_12</span>
                    <BsDot/>
                    <time dateTime={publishedTime.toISOString()}>
                        {/*피드 올린후 경과시간 시간,일,주,달,년 으로 구분해야됨*/}
                        {elapsedHours}
                    </time>
                    <button className='peed_option'
                            onClick={() => {
                                dispatch(optionModalOn())
                            }
                            }>
                        <BsThreeDots style={{paddingLeft: "35px"}}/>
                    </button>
                </div>
                {/*피드의 이미지나 영상 파트*/}
                <div className='peed_media_section'>
                    {/*서버에서 이미지 가져와서 넣기*/}
                    <img src='/assets/img/1.jpg' className='peed_media'></img>
                </div>
                {/*좋아요 댓글 공유 저장등 아이콘 모음*/}
                <div className='peed_icon_section'>
                    <button className={`peed_like_icon ${isLiked ? "clicked" : ""}`}
                            onClick={handleLikeClick}>
                        {isLiked ? <AiFillStar className='liked_star'/> : <AiOutlineStar/>}
                    </button>
                    <button className='peed_comment_icon'
                            onClick={() => {
                                dispatch(commentModalOn())
                            }}>
                        <AiOutlineComment/>
                    </button>
                    <button className='peed_share_icon'>
                        <AiOutlineShareAlt/>
                    </button>
                    <button className="peed_save_icon" onClick={handleSaveClick}>
                        {isSaved ?
                            <BsBookmarkDashFill style={{paddingLeft: "11px"}}/> :
                            <BsBookmarkDash style={{paddingLeft: "11px"}}/>}
                    </button>
                </div>

                {/*좋아요를 인원 표시*/}
                <div className="liked_user">
                    <span>
                        <span style={{fontWeight: "bold", cursor: "pointer"}}>qweas123</span>
                        <span> 님 외</span>
                        <span style={{fontWeight: "bold", cursor: "pointer"}}> 여러 명</span>
                        <span>이 좋아합니다.</span>
                    </span>
                </div>


                <div className="peed_text"
                     style={{
                         maxHeight: showFullText || !isLongText ? 'none' : '2.7em'}}>
                    {/* 본문의 일부만 표시 시 */}
                    {!showFullText && (
                        <div style={{overflow: "hidden"}}>
                            {peed_text.split("\n").map((text, index) =>
                                <p key={index}>{text}</p>
                            )}

                        </div>
                    )}

                    {/* 전체 본문 표시 */}
                    {peed_text.split('\n').map((text, index) => (
                        <p key={index} style={{ marginBottom: '0' }}>
                            {text}
                        </p>
                    ))}


                </div>
                {/* 더보기/접기 버튼 */}
                {isLongText && (
                    <span className={"text_more"}
                          onClick={handleToggleText}
                    >
                            {showFullText ? "...접기" : "...더보기"}
                        </span>
                )}
                {/*댓글 볼수있음 피드를 모달창으로 띄어줌*/}
                <div>
                    <span className="peed_comment"
                          onClick={() => {
                              dispatch(commentModalOn())
                          }}>
                        댓글 24개 모두 보기
                    </span>
                </div>

                {/*엔터누르면 댓글 전송되도록 만들기*/}
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

                {state.isOptionModalOpen === true ? <PeedOption/> : null}
                {state.isCommentModalOpen === true ? <PeedMore/> : null}
            </div>
        </div>
    );
};

export default PeedCard;
