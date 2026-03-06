import styles from './KpiWidget.module.css'

function KpiWidget({ title, value, type = "neutral" }) {

    const valueClass = styles[type] || styles.neutral
    
    return (
        <div className={styles.widgetCard}>
            <h3 className={styles.title}>{title}</h3>
            <p className={`${styles.value} ${valueClass}`}>{value}</p>
        </div>
    )
}

export default KpiWidget