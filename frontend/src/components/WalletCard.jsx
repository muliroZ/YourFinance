import styles from './WalletCard.module.css'

function WalletCard() {
    return (
        <div className={styles.card}>
            <div className={styles.cardHeader}>
                <h2>Minhas Carteiras</h2>
                <button className={styles.actionBtn}>+ Nova</button>
            </div>

            <div className={styles.walletList}>
                <div className={styles.walletItem}>
                    <span className={styles.walletName}>Conta Principal</span>
                    <strong className={styles.walletBalance}>R$ 3.450,00</strong>
                </div>
                <div className={styles.walletItem}>
                    <span className={styles.walletName}>Poupança</span>
                    <strong className={styles.walletBalance}>R$ 1.200,00</strong>
                </div>
            </div>
        </div>
    )
}

export default WalletCard