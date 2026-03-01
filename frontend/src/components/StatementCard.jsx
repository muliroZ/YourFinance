import { useState, useEffect } from "react"
import { list } from "../services/transactionService.js";
import styles from './StatementCard.module.css'

function StatementCard({ refreshTrigger }) {
    const [transactions, setTransactions] = useState([])
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        async function fetchTransactions() {
            setLoading(true)
            try {
                const data = await list({ sortByAsc: false })
                setTransactions(data.transactions)
            } catch (error) {
                console.error("Erro ao buscar extratos:", error)
            } finally {
                setLoading(false)
            }
        }
        fetchTransactions()
    }, [refreshTrigger]);

    return (
        <div className={styles.card}>
            <div className={styles.cardHeader}>
                <h2>Últimos Extratos</h2>
                <a href="#" className={styles.link}>Ver todos</a>
            </div>

            {loading ? (
                <p>Carregando extratos...</p>
            ) : (
                <ul className={styles.transactionList}>
                    {transactions.length === 0 && <p>Nenhuma transação encontrada.</p>}

                    {transactions.map((tx) => (
                        <li key={tx.id} className={styles.transactionItem}>
                            <div className={styles.transactionInfo}>
                                <span className={styles.description}>{tx.description}</span>
                                <span className={styles.date}>
                                    {new Date(tx.date).toLocaleDateString('pt-BR')}
                                </span>
                            </div>
                            <span className={`${styles.amount} ${tx.type === 'EXPENSE' ? styles.expense : styles.income}`}>
                                {tx.type === 'EXPENSE' ? '- ' : '+ '}
                                R$ {Number(tx.amount).toFixed(2).replace('.', ',')}
                            </span>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default StatementCard;