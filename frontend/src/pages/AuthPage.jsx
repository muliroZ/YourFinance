import {useContext, useState} from "react";
import {AuthContext} from "../auth/AuthContext.jsx";
import {useNavigate} from "react-router-dom";
import {login, register} from "../auth/authService.js";
import "./AuthPage.css"

function AuthPage() {
    const [isLogin, setIsLogin] = useState(true)
    const [form, setForm] = useState({ name: "" })

    const { loginUser } = useContext(AuthContext)
    const navigate = useNavigate()

    function handleChange(e) {
        setForm({...form, [e.target.name]: [e.target.value]})
    }

    async function handleSubmit(e) {
        e.preventDefault()

        try {
            if (isLogin) {
                const data = await login(form.email, form.password)
                loginUser(data.token)
                navigate("/")
            } else {
                await register(form)
                setIsLogin(true)
            }
        } catch (err) {
            alert(err.message)
        }
    }

    return (
        <div className={"auth-card"}>
            <h2>{isLogin ? "Login" : "Register"}</h2>
            <hr/>
            <form onSubmit={handleSubmit} className={"auth-form"}>
                {!isLogin && (
                    <>
                        <label htmlFor={"name"}>Name</label>
                        <input id={"name"} name={"name"} placeholder={"My name here"} onChange={handleChange}/>
                    </>
                )}

                <label htmlFor={"email"}>Email</label>
                <input id={"email"} name={"email"} placeholder={"yourfinance@email.com"} onChange={handleChange}/>

                <label htmlFor={"password"}>Password</label>
                <input id={"password"} name={"password"} type={"password"} placeholder={"******"} onChange={handleChange}/>

                <button type={"submit"}>
                    {isLogin ? "Login" : "Register"}
                </button>
            </form>

            <hr/>

            <button className={"auth-button"} onClick={() => setIsLogin(!isLogin)}>
                {isLogin ? "Create account" : "I have an account"}
            </button>
        </div>
    )
}

export default AuthPage