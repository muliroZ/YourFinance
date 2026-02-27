import { useContext } from "react"
import {AuthContext} from "../auth/AuthContext.jsx";
import {Navigate} from "react-router-dom";

function PrivateRoute({ children }) {
    const { user } = useContext(AuthContext)

    if (!user) {
        return <Navigate to={"/"} />
    }

    return children
}

export default PrivateRoute