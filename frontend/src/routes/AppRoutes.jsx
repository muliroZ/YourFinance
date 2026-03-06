import { BrowserRouter, Routes, Route } from "react-router-dom"
import PrivateRoute from "../components/PrivateRoute.jsx"
import AuthPage from "../pages/AuthPage.jsx";
import Home from "../pages/Home.jsx";
import Transactions from "../pages/Transactions.jsx";
import Categories from "../pages/Categories.jsx";

function AppRoutes() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path={"/auth"} element={<AuthPage />} />
                <Route path={"/"} element={
                    <PrivateRoute><Home/></PrivateRoute>
                }/>
                <Route path={"/transactions"} element={
                    <PrivateRoute><Transactions/></PrivateRoute>
                }/>
                <Route path={"/categories"} element={
                    <PrivateRoute><Categories/></PrivateRoute>
                }/>
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes