import Header from "../components/Header.jsx";
import Footer from "../components/Footer.jsx";
import styles from "./Home.module.css"
import KpiWidget from "../components/KpiWidget.jsx";
import WalletCard from "../components/WalletCard.jsx";
import StatementCard from "../components/StatementCard.jsx";

function Home() {
    return (
        <>
            <Header/>
            <main className={styles.mainContainer}>
                <section className={styles.welcomeSection}>
                    <h1>Resumo Financeiro</h1>
                    <p>Acompanhe o saldo das suas carteiras e seus últimos extratos.</p>
                </section>

                <section className={styles.kpiSection}>
                    <KpiWidget title="Saldo Total" value="R$ 4.650,00" type="neutral" />
                    <KpiWidget title="Receitas / Mês" value="R$ 4.000,00" type="positive" />
                    <KpiWidget title="Despesas / Mês" value="R$ 150,00" type="negative" />
                </section>

                <section className={styles.dashboardGrid}>
                    <WalletCard/>
                    <StatementCard/>
                </section>
            </main>
            <Footer/>
        </>
    )
}

export default Home;