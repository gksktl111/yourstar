import './App.css';
import "./fonts/font.css";
import LoginPage from "./routes/login/LoginPage.js";
import MainPage from "./routes/main/MainPage";
import {Route,Routes} from 'react-router-dom';

function App () {

    return (
        <>
            <Routes>
                <Route path={"/"} element={<LoginPage/>}/>
                <Route exact path = {"/main"} element={<MainPage/>}/>
            </Routes>
        </>
    )
}

export default App;