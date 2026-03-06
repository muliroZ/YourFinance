import {Link} from "react-router-dom"
import { useContext } from "react"
import { AuthContext } from "../auth/AuthContext"
import styles from "./Header.module.css"

function Header() {

    const {user, logout} = useContext(AuthContext)

    return (
        <header className={styles.header}>
            <div className={styles.logo}>
                <Link to={"/"} >
                    <img src={"https://placehold.co/50"} className={styles.logoImg} alt={"logo image"}/>
                </Link>
                <Link to={"/"} className={styles.logoText}>YourFinance</Link>
            </div>
            <nav className={styles.navLinks}>
                <Link to={"/"}>Início</Link>
                <Link to={"/transactions"}>Transações</Link>
                <Link to={"/categories"}>Categorias</Link>
                {user ? (
                    <button className={styles.logoutBtn} onClick={logout}>
                        Sair
                    </button>
                ) : (
                    <Link to={"/auth"}>Entrar</Link>
                )}
            </nav>
        </header>
    )
}

export default Header