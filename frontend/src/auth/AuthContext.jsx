import { createContext, useState, useEffect } from "react"

export const AuthContext = createContext()

function decodeJwt(token) {
    try {
        const base64Payload = token.split('.')[1]
        const payload = atob(base64Payload)
        return JSON.parse(payload)
    } catch (e) {
        return null
    }
}

export function AuthProvider({ children }) {
    const [user, setUser] = useState(() => {
        const token = localStorage.getItem("token")
        if (token) {
            const decoded = decodeJwt(token)
            if (decoded && decoded.exp * 1000 < Date.now()) {
                localStorage.removeItem("token")
                return null
            }
            return { token }
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

    useEffect(() => {
        if (user && user.token) {
            const decoded = decodeJwt(user.token)

            if (decoded && decoded.exp) {
                const expirationTime = decoded.exp * 1000
                const currentTime = Date.now()
                const timeUntilExpiration = expirationTime - currentTime

                if (timeUntilExpiration > 0) {
                    const timer = setTimeout(() => {
                        alert("Sua sessão expirou. Por favor, faça login novamente.")
                        logout()
                    }, timeUntilExpiration)

                    return () => clearTimeout(timer)
                } else {
                    logout()
                }
            }
        }
    }, [user])

    return (
        <AuthContext.Provider value={{ user, loginUser, logout }}>
            {children}
        </AuthContext.Provider>
    )
}