import React, {useState} from 'react';
import './Sidebar.css';
import {BiHomeAlt} from "react-icons/bi";
import {FiMail, FiSettings} from "react-icons/fi"
import {CgProfile} from "react-icons/cg"
import {MdOutlineQueueMusic} from "react-icons/md";
import {NavLink} from "react-router-dom";

const Sidebar = ({children}) => {
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
            path: "/mail",
            name: "메시지",
            icon: <FiMail/>
        },
        {
            path: "/playList",
            name: "플레이리스트",
            icon: <MdOutlineQueueMusic/>
        },
        {
            path: "/setting",
            name: "옵션",
            icon: <FiSettings/>
        },
    ]

    return (
        <div className="container">
            <div className="sidebar">
                <div className="top_section">
                    <img src="/assets/img/urstar.png" alt="로고" className="logo_img" />
                    <h1 className="logo">URSTAR</h1>
                </div>
                {
                    menuItem.map((item, index) => (
                        <NavLink to={item.path} key={index}
                                 className="link" activeclassname="active">
                            <div className = "icon">{item.icon}</div>
                            <div className = "link_text">{item.name}</div>
                        </NavLink>
                    ))
                }
            </div>
            <main>{children}</main>
        </div>
    );
};

export default Sidebar;