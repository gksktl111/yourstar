import React, {useState} from 'react';
import {AiFillStar, AiOutlineStar} from "react-icons/ai";
import {BsThreeDots} from "react-icons/bs";
import "./PeedMoreComment.css"

const PeedMoreComment = () => {
    const [isLiked, setIsLiked] = useState(false);

    // 댓글 좋아요 클릭
    const handleLikeClick = () => {
        if (isLiked === true) {
            setIsLiked(false);
            return;
        }

        setIsLiked(true);
    };

    return (
        <div>
            <li>
                <div className="peed_more_context">
                    <img src="/assets/img/3.jpg" alt="오류"
                         className="peed_more_profile"/>
                    <p className="peed_comment_text">
                        <a href="http://localhost:3000/abcd"
                           className="peed_more_name">
                            jaejuna
                        </a>
                        와 이거 진짜 카카오 택배보다 이따 밤에 온데asdasdasdasdasdasdasdadasd
                        asdasdasdasdad.aaaa..aaaaaaaaaaaa
                    </p>

                    {/*좋아요를 구별할 방법을 생각해야됨*/}
                    <div className="comment_like">
                        <button
                            className={`comment_like_btn ${isLiked ? "clicked" : ""}`}
                            onClick={handleLikeClick}
                        >
                            {isLiked ? <AiFillStar/> : <AiOutlineStar/>}
                        </button>
                    </div>
                </div>
                <spna className="comment_footer">
                    <time className={"comment_time"}>10시간</time>
                    <span className={"comment_like_sum"}>좋아요 8개</span>
                    <span className={"comment_reply"}>답글 달기</span>

                    {/*다른 사람꺼면 신고,취소*/}
                    {/*본인꺼면 삭제,취소*/}
                    <span className={"comment_option"}>
                        <BsThreeDots style={{fontSize: "15px", marginBottom: "-3px"}}/>
                    </span>
                </spna>
            </li>
        </div>
    );
};

export default PeedMoreComment;