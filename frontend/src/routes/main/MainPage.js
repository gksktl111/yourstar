import styled, {keyframes} from 'styled-components';
import {useEffect, useState} from "react";

let Nav = styled.div`
  position: fixed;
  width: 200px;
  height: 90%;
  background-color: white;
  border-right: 1px solid black;
`

let Content = styled.div`
  position: absolute;
  width: 100%;
  height: 90%;
  background-color: black;
`


let MusicPlayer = styled.div`
  position: fixed;
  width: 100%;
  height: 20%;
  top: 90%;
  background-color: #0854f6;
`

function MainPage(){
    return(
        <>
            <Content></Content>
            <Nav>

            </Nav>
            <MusicPlayer></MusicPlayer>
        </>
    )
}

export default MainPage;