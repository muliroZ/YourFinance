import { createContext, useState } from "react"

export const AuthContext = createContext()

export function AuthProvider({ children }) {
    const [user, setUser] = useState(() => {
        const token = localStorage.getItem("token")
        if (token) {
            return{ token }
        }
        return null
    })

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