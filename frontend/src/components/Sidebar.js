import React, {useState} from 'react';
import '../App.css';
import {BiHomeAlt} from "react-icons/bi";
import {FiMail, FiSettings} from "react-icons/fi"
import {CgProfile} from "react-icons/cg"
import {FaBars} from "react-icons/fa";
import {MdOutlineQueueMusic} from "react-icons/md";
import {NavLink} from "react-router-dom";


const Sidebar = ({children}) => {
    const [isOpen,setIsOpen] = useState(false);
    const toggle = () => setIsOpen((!isOpen));
    const menuItem = [
        {
            path: "/",
            name: "home",
            icon: <BiHomeAlt/>
        },
        {
            path: "/profile",
            name: "Profile",
            icon: <CgProfile/>
        },
        {
            path: "/mail",
            name: "Mail",
            icon: <FiMail/>
        },
        {
            path: "/playList",
            name: "PlayList",
            icon: <MdOutlineQueueMusic/>
        },
        {
            path: "/setting",
            name: "Setting",
            icon: <FiSettings/>
        },
    ]

    return (
        <div className="container">
            <div style={{width: isOpen ? "300px" : "50px"}} className="sidebar">
                <div className="top_section">
                    <h1 style={{display: isOpen ? "block" : "none"}} className="logo">Logo</h1>
                    <div style={{marginLeft: isOpen ? "150px" : "-2px"}} className="bars">
                        <FaBars onClick={toggle}/>
                    </div>
                </div>
                {
                    menuItem.map((item, index) => (
                        <NavLink to={item.path} key={index}
                                 className="link" activeClassName="active">
                            <div className = "icon">{item.icon}</div>

                            <div style={{display: isOpen ? "block" : "none"}} className = "link_text">{item.name}</div>
                        </NavLink>
                    ))
                }
            </div>
            <main>{children}</main>
        </div>
    );
};

export default Sidebar;