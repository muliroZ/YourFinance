import Header from "../components/Header.jsx";
import Footer from "../components/Footer.jsx";
import styles from "./Home.module.css"

function Home() {
    return (
        <>
            <Header/>
            <main className={styles.mainContainer}>
                <section className={styles.welcomeSection}>
                    <h1>Resumo Financeiro</h1>
                    <p>Acompanhe o saldo das suas carteiras e seus últimos extratos.</p>
                </section>

                <section className={styles.dashboardGrid}>
                    <div className={styles.card}>
                        <div className={styles.cardHeader}>
                            <h2>Minhas Carteiras</h2>
                            <button className={styles.actionBtn}>+ Nova</button>
                        </div>
                        <div className={styles.walletItem}>
                            <span>Conta Principal</span>
                            <strong>R$ 3.450,00</strong>
                        </div>
                        <div className={styles.walletItem}>
                            <span>Poupança</span>
                            <strong>R$ 1.200,00</strong>
                        </div>
                    </div>

                    <div className={styles.card}>
                        <div className={styles.cardHeader}>
                            <h2>Últimas Transações</h2>
                            <a href="#" className={styles.link}>Ver extrato completo</a>
                        </div>
                        <ul className={styles.transactionList}>
                            <li>
                                <span className={styles.transactionDesc}>Mercado</span>
                                <span className={styles.expense}>- R$ 150,00</span>
                            </li>
                            <li>
                                <span className={styles.transactionDesc}>Salário</span>
                                <span className={styles.income}>+ R$ 4.000,00</span>
                            </li>
                        </ul>
                    </div>
                </section>
            </main>
            <Footer/>
        </>
    )
}

export default Home;