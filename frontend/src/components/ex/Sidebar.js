import React, {useState} from 'react';
import './Sidebar.css';
import {BiHomeAlt} from "react-icons/bi";
import {FiMail, FiSettings} from "react-icons/fi"
import {CgProfile} from "react-icons/cg"
import {MdOutlineQueueMusic} from "react-icons/md";
import {NavLink} from "react-router-dom";
import {AiOutlinePlusCircle} from "react-icons/ai";
import NewPeed from "../NewPeed/NewPeed";
import {useDispatch, useSelector} from "react-redux";
import {newPeedModalOn} from "../../store/Store";

const Sidebar = ({children}) => {
    let state =  useSelector((state) => {return state});
    const dispatch = useDispatch();

    const menuItem = [
        {
            path: "/",
            name: "홈",
            icon: <BiHomeAlt/>
        },
        {
            path: "/profile",
            name: "프로필",
            icon: <CgProfile/>
        },
        {
            path: "/message",
            name: "메시지",
            icon: <FiMail/>
        },
        {
            path: "/playList",
            name: "플레이리스트",
            icon: <MdOutlineQueueMusic/>
        },
        {
            path: "/newPeed",
            name: "만들기",
            icon: <AiOutlinePlusCircle/>
        },
        {
            path: "/setting",
            name: "옵션",
            icon: <FiSettings/>
        }
    ]


    const NewPeedButton = ({ onClick }) => (
        <div
            onClick={onClick}
            className="link newPeedBtn"
        >
            <div className="icon"><AiOutlinePlusCircle/></div>
            <div className="link_text">만들기</div>
        </div>
    );

    // 버튼에 대한 클릭 이벤트 처리를 구현하세요
    const handleNewPeedClick = () => {
        dispatch(newPeedModalOn())
    };


    return (
        <div className="container">
            <div className="sidebar">
                <div className="top_section">
                    <img src="/assets/img/urstar.png" alt="로고" className="logo_img" />
                    <h1 className="logo">URSTAR</h1>
                </div>
                {menuItem.map((item, index) => {
                    if (item.name === '만들기') {
                        return (
                            <NewPeedButton key={index} onClick={handleNewPeedClick} />
                        );
                    } else {
                        return (
                            <NavLink
                                to={item.path}
                                key={index}
                                className="link"
                                activeClassName="active"
                            >
                                <div className="icon">{item.icon}</div>
                                <div className="link_text">{item.name}</div>
                            </NavLink>
                        );
                    }
                })}
            </div>
            {state.isNewPeedModalOpen === true ? <NewPeed/> : null}
            <main>{children}</main>
        </div>
    );
};


export default Sidebar;