import { BrowserRouter, Routes, Route } from "react-router-dom"
import AuthPage from "../pages/AuthPage.jsx";
import Home from "../pages/Home.jsx";

function AppRoutes() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path={"/"} element={<Home />} />
                <Route path={"/auth"} element={<AuthPage />} />
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes