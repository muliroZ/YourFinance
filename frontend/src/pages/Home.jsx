import Header from "../components/Header.jsx";
import Footer from "../components/Footer.jsx";
import styles from "./Home.module.css"
import KpiWidget from "../components/KpiWidget.jsx";
import WalletCard from "../components/WalletCard.jsx";
import StatementCard from "../components/StatementCard.jsx";
import {useState} from "react";
import TransactionForm from "../components/TransactionForm.jsx";

function Home() {
    const [isModalOpen, setIsModalOpen] = useState(false)
    const [refreshKey, setRefreshKey] = useState(0)

    function handleTransactionCreated() {
        setIsModalOpen(false)
        setRefreshKey(key => key + 1)
    }

    return (
        <>
            <Header/>
            <main className={styles.mainContainer}>
                <section className={styles.welcomeSection}>
                    <div className={styles.welcomeText}>
                        <h1>Resumo Financeiro</h1>
                        <p>Acompanhe o saldo das suas carteiras e seus últimos extratos.</p>
                    </div>
                    <button
                        className={styles.newTransactionBtn}
                        onClick={() => setIsModalOpen(true)}
                    >
                        + Nova Transação
                    </button>
                </section>

                <section className={styles.kpiSection}>
                    <KpiWidget title="Saldo Total" value="R$ 4.650,00" type="neutral" />
                    <KpiWidget title="Receitas / Mês" value="R$ 4.000,00" type="positive" />
                    <KpiWidget title="Despesas / Mês" value="R$ 150,00" type="negative" />
                </section>

                <section className={styles.dashboardGrid}>
                    <WalletCard/>
                    <StatementCard refreshTrigger={refreshKey}/>
                </section>
            </main>

            {isModalOpen && (
                <div className={styles.modalOverlay}>
                    <div className={styles.modalContent}>
                        <button
                            className={styles.closeModalBtn}
                            onClick={() => setIsModalOpen(false)}
                        >
                            &times;
                        </button>

                        <TransactionForm onTransactionCreated={handleTransactionCreated}/>
                    </div>
                </div>
            )}

            <Footer/>
        </>
    )
}

export default Home;