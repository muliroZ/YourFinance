import { BrowserRouter, Routes, Route } from "react-router-dom"
import AuthPage from "../pages/AuthPage.jsx";

function AppRoutes() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path={"/login"} element={<AuthPage />} />
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes