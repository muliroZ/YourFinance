import { createContext, useState, useEffect } from "react"

export const AuthContext = createContext()

export function AuthProvider({ children }) {
    const [user, setUser] = useState(null)

    useEffect(() => {
        const token = localStorage.getItem("token")
        if (token) {
            // eslint-disable-next-line react-hooks/set-state-in-effect
            setUser({ token })
        }
    }, []);

    function loginUser(token) {
        localStorage.setItem("token", token)
        setUser({ token })
    }

    function logout() {
        localStorage.removeItem("token")
        setUser(null)
    }

    return (
        <AuthContext.Provider value={{ user, loginUser, logout }}>
            {children}
        </AuthContext.Provider>
    )
}