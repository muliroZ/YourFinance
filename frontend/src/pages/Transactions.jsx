import styles from './Transactions.module.css'
import Header from "../components/Header.jsx";
import Footer from "../components/Footer.jsx";
import {useEffect, useState} from "react";
import TransactionForm from "../components/TransactionForm.jsx";
import {list} from "../services/transactionService.js";

function Transactions() {
    const [transactions, setTransactions] = useState([])
    const [isLoading, setIsLoading] = useState(true)
    const [isModalOpen, setIsModalOpen] = useState(false)
    const [refreshKey, setRefreshKey] = useState(0)

    useEffect(() => {
        async function fetchTransactions() {
            setIsLoading(true)
            try {
                const data = await list()
                setTransactions(data.transactions)
            } catch (err) {
                console.error("Erro ao carregar as transações", err)
            } finally {
                setIsLoading(false)
            }
        }
        fetchTransactions()
    }, [refreshKey]);

    function handleTransactionCreated() {
        setIsModalOpen(false)
        setRefreshKey(key => key + 1)
    }

    return (
        <>
            <Header/>
            <main className={styles.mainContainer}>
                <div className={styles.pageHeader}>
                    <div>
                        <h1>Gestão de Transações</h1>
                        <p>Consulte, filtre e gerencie seu histórico financeiro</p>
                    </div>
                    <button
                        className={styles.newBtn}
                        onClick={() => setIsModalOpen(true)}
                    >
                        + Nova Transação
                    </button>
                </div>

                <div className={styles.tableContainer}>
                    {isLoading ? (
                        <div className={styles.loadingState}>Carregando transações...</div>
                    ) : (
                        <table className={styles.dataTable}>
                            <thead>
                                <tr>
                                    <th>Data</th>
                                    <th>Descrição</th>
                                    <th>Tipo</th>
                                    <th>Valor (R$)</th>
                                    <th className={styles.actionsColumn}>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                {transactions.length === 0 ? (
                                    <tr>
                                        <td colSpan="5" className={styles.emptyState}>
                                            Ainda não tem transações registradas.
                                        </td>
                                    </tr>
                                ) : (
                                    transactions.map(tx => (
                                        <tr key={tx.id}>
                                            <td>{new Date(tx.date).toLocaleDateString("pt-BR")}</td>
                                            <td className={styles.descCell}>{tx.description}</td>
                                            <td>
                                                <span className={tx.type === 'EXPENSE' ? styles.badgeExpense : styles.badgeIncome}>
                                                    {tx.type === 'EXPENSE' ? "Despesa" : "Receita"}
                                                </span>
                                            </td>
                                            <td>
                                                <span className={tx.type === 'EXPENSE' ? styles.expenseAmount : styles.incomeAmount}>
                                                    {tx.type === 'EXPENSE' ? '- ' : '+ '}
                                                    {Number(tx.amount).toFixed(2).replace('.', ',')}
                                                </span>
                                            </td>
                                            <td className={styles.actionsColumn}>
                                                <button className={styles.editBtn}>Editar</button>
                                                <button className={styles.deleteBtn}>Excluir</button>
                                            </td>
                                        </tr>
                                    ))
                                )}
                            </tbody>
                        </table>
                    )}
                </div>

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

            </main>
            <Footer/>
        </>
    )
}

export default Transactions