import styles from './ItemPanel.module.css'

function ItemPanel({ items, noItemsMessage }) {
    return (
        <div className={styles.panel}>
            {items.length === 0 && <p className={styles.noItems}>{noItemsMessage}</p>}

            {items.map((item, index) => (
                <div key={index} className={styles.panelItem}>
                    {item.name}
                </div>
            ))}
        </div>
    )
}

export default ItemPanel