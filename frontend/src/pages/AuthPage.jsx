import {useContext, useState} from "react";
import {AuthContext} from "../auth/AuthContext.jsx";
import {useNavigate} from "react-router-dom";
import {login, register} from "../auth/authService.js";
import styles from "./AuthPage.module.css"
import Header from "../components/Header.jsx";
import Footer from "../components/Footer.jsx";

function AuthPage() {
    const [isLogin, setIsLogin] = useState(true)
    const [form, setForm] = useState({ name: "" })

    const { loginUser } = useContext(AuthContext)
    const navigate = useNavigate()

    function handleChange(e) {
        setForm({...form, [e.target.name]: e.target.value})
    }

    async function handleSubmit(e) {
        e.preventDefault()

        try {
            if (isLogin) {
                const data = await login({
                    email: form.email,
                    password: form.password
                })
                loginUser(data.token)
                navigate("/")
            } else {
                await register({
                    name: form.name,
                    email: form.email,
                    password: form.password
                })
                setIsLogin(true)
            }
        } catch (err) {
            alert(err.message)
        }
    }

    return (
        <>
            <Header/>
            <div className={styles.authContainer}>
                <div className={styles.authCard}>
                    <h2 className={styles.cardTitle}>{isLogin ? "Login" : "Cadastro"}</h2>
                    <hr/>
                    <form onSubmit={handleSubmit} className={styles.authForm}>
                        {!isLogin && (
                            <>
                                <label htmlFor={"name"}>Nome</label>
                                <input id={"name"} name={"name"} placeholder={"Seu nome aqui"} onChange={handleChange}/>
                            </>
                        )}

                        <label htmlFor={"email"}>Email</label>
                        <input id={"email"} name={"email"} placeholder={"yourfinance@email.com"} onChange={handleChange}/>

                        <label htmlFor={"password"}>Senha</label>
                        <input id={"password"} name={"password"} type={"password"} placeholder={"******"} onChange={handleChange}/>

                        <button type={"submit"}>
                            {isLogin ? "Entrar" : "Cadastrar"}
                        </button>
                    </form>

                    <hr/>

                    <button className={styles.authButton} onClick={() => setIsLogin(!isLogin)}>
                        {isLogin ? "Criar conta" : "Já tenho uma conta"}
                    </button>
                </div>
            </div>
            <Footer/>
        </>
    )
}

export default AuthPage