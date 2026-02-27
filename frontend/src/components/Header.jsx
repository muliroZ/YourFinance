import {Link} from "react-router-dom"
import styles from "./Header.module.css"

function Header() {

    return (
        <header className={styles.header}>
            <div className={styles.logo}>
                <Link to={"/"} >
                    <img src={"https://placehold.co/50"} className={styles.logoImg} alt={"logo image"}/>
                </Link>
                <Link to={"/"} className={styles.logoText}>YourFinance</Link>
            </div>
            <nav className={styles.navLinks}>
                <Link to={"/"}>Home</Link>
                <Link to={"/auth"}>Login</Link>
            </nav>
        </header>
    )
}

export default Header