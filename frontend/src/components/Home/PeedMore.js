import React, {useState} from 'react';
import "./PeedMore.css"
import {useDispatch} from "react-redux";
import {BsDot, BsThreeDots} from "react-icons/bs";
import {optionModalOn} from "../../store/Store";

const PeedMore = () => {
    const dispatch = useDispatch();

    const [isFollow, setIsFollow] = useState(true);

    return (
        <div className="peed_more_background">
            <div className="peed_more_container">
                <div className="peed_more_left">
                    <img src={'/assets/img/1.jpg'}
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
                            <BsThreeDots/>
                        </button>
                    </div>
                    {/*게시글 글 보여준 후 그 밑으로는 댓글 들 보여주기*/}
                    <div className={"peed_mor_middle_section"}>
                        <div className="peed_comment_area">
                            <ul className="peed_comment_list">

                                <div className="peed_more_context">
                                    <img src="/assets/img/3.jpg" alt="오류" className="peed_more_profile"/>
                                    <p className="peed_more_text">
                                        <a href="http://localhost:3000/aad" className="peed_more_name">
                                            alsrb_1214
                                        </a>
                                        {/*여기에 글을 작성할겁니다여기에 <br/> 은 따로 넣어야됨*/}
                                        여기에 글을 작성할겁니다여기에 <br/>글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다여기에 글을 작성할겁니다
                                    </p>
                                </div>

                                <li>
                                    <div className="peed_more_context">
                                        <img src="/assets/img/3.jpg" alt="오류" className="peed_more_profile"/>
                                        <p className="peed_comment_text">
                                            <a href="http://localhost:3000/abcd" className="peed_comment_name">
                                                jaejuna
                                            </a>
                                            와 이거 진짜 카카오 택배보다 이따 밤에 온데...
                                        </p>
                                    </div>
                                </li>
                                <li>
                                    <img src="/assets/img/3.jpg" alt="오류" className="peed_more_profile"/>
                                    <a href="http://localhost:3000/efgh" className="peed_comment_name">
                                        thakorea
                                    </a>
                                    <p className="peed_comment_text">싹쓸이 2주년이네요~ 축하합니다!</p>
                                </li>
                                <li>
                                    <img src="/assets/img/3.jpg" alt="오류" className="peed_more_profile"/>
                                    <a href="http://localhost:3000/ijkl" className="peed_comment_name">
                                        kenan_kl
                                    </a>
                                    <p className="peed_comment_text">
                                        갖고싶다 짜장면 디자인 이사하기전에 사서 아쉬웠는데
                                    </p>
                                </li>

                            </ul>
                        </div>
                    </div>

                    <div className="peed_comment_form">
                        <input type="text" placeholder="댓글 달기..."/>
                        <button>게시</button>
                    </div>
                </div>

            </div>
        </div>
    );
};

export default PeedMore;