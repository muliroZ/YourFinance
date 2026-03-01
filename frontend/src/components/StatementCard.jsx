import styles from './StatementCard.module.css';

function StatementCard() {
    return (
        <div className={styles.card}>
            <div className={styles.cardHeader}>
                <h2>Últimos Extratos</h2>
                <a href="#" className={styles.link}>Ver todos</a>
            </div>

            <ul className={styles.transactionList}>
                <li className={styles.transactionItem}>
                    <div className={styles.transactionInfo}>
                        <span className={styles.description}>Supermercado</span>
                        <span className={styles.date}>27 Fev 2026</span>
                    </div>
                    <span className={`${styles.amount} ${styles.expense}`}>- R$ 150,00</span>
                </li>
                <li className={styles.transactionItem}>
                    <div className={styles.transactionInfo}>
                        <span className={styles.description}>Salário</span>
                        <span className={styles.date}>25 Fev 2026</span>
                    </div>
                    <span className={`${styles.amount} ${styles.income}`}>+ R$ 4.000,00</span>
                </li>
            </ul>
        </div>
    );
}

export default StatementCard;