import { BrowserRouter, Routes, Route } from "react-router-dom"
import PrivateRoute from "../components/PrivateRoute.jsx"
import AuthPage from "../pages/AuthPage.jsx";
import Home from "../pages/Home.jsx";

function AppRoutes() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path={"/auth"} element={<AuthPage />} />
                <Route path={"/"} element={
                    <PrivateRoute>
                        <Home />
                    </PrivateRoute>
                    } />
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes