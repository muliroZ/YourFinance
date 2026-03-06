import styles from './Transactions.module.css'
import Header from "../components/Header.jsx";
import Footer from "../components/Footer.jsx";
import {useEffect, useState} from "react";
import TransactionForm from "../components/TransactionForm.jsx";
import {listTransactions} from "../services/transactionService.js";
import {listCategories} from "../services/categoryService.js";

function Transactions() {
    const [transactions, setTransactions] = useState([])
    const [categories, setCategories] = useState([])
    const [isLoading, setIsLoading] = useState(true)
    const [isModalOpen, setIsModalOpen] = useState(false)
    const [refreshKey, setRefreshKey] = useState(0)
    const [categoryRefreshKey, setCategoryRefreshKey] = useState(0)
    const [filter, setFilter] = useState({
        type: "",
        categoryId: "",
        startDate: "",
        endDate: "",
        sortByAsc: false
    })

    useEffect(() => {
        async function fetchTransactions() {
            setIsLoading(true)
            try {
                const cleanFilter = {}
                for (const key in filter) {
                    if (filter[key] !== "") {
                        cleanFilter[key] = filter[key]
                    }
                }
                const data = await listTransactions(cleanFilter)
                setTransactions(data.transactions)
            } catch (err) {
                console.error("Erro ao carregar as transações", err)
            } finally {
                setIsLoading(false)
            }
        }
        fetchTransactions()
    }, [refreshKey, filter]);

    useEffect(() => {
        async function fetchCategories() {
            try {
                const data = await listCategories()
                setCategories(data.categories)
            } catch (err) {
                console.error("Erro ao carregar categorias", err)
            }
        }
        fetchCategories()
    }, [categoryRefreshKey]);

    const handleChange = (e) => {
        let value = e.target.value

        if (e.target.name === "sortByAsc") {
            value = value === "true"
        }

        setFilter({ ...filter, [e.target.name]: value })
    }

    function handleTransactionCreated() {
        setIsModalOpen(false)
        setRefreshKey(key => key + 1)
    }

    function handleCategoryCreated() {
        setCategoryRefreshKey(key => key + 1)
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

                <div className={styles.filterContainer}>
                    <h3>Filtros</h3>

                    <div className={styles.filterOptions}>
                        <div className={styles.filterGroup}>
                            <label htmlFor="type">Tipo</label>
                            <select id="type" name="type" value={filter.type} onChange={handleChange}>
                                <option value="">Todos</option>
                                <option value="INCOME">Receita</option>
                                <option value="EXPENSE">Despesa</option>
                            </select>
                        </div>

                        <div className={styles.filterGroup}>
                            <label htmlFor="categoryId">Categoria</label>
                            <select id="categoryId" name="categoryId" value={filter.categoryId} onChange={handleChange}>
                                <option value="">Todas</option>
                                {categories.map(cat => (
                                    <option key={cat.id} value={cat.id}>{cat.name}</option>
                                ))}
                            </select>
                        </div>

                        <div className={styles.filterGroup}>
                            <label htmlFor="startDate">Data Inicial</label>
                            <input type="date" id="startDate" name="startDate" value={filter.startDate} onChange={handleChange}/>
                        </div>

                        <div className={styles.filterGroup}>
                            <label htmlFor="endDate">Data Final</label>
                            <input type="date" id="endDate" name="endDate" value={filter.endDate} onChange={handleChange}/>
                        </div>

                        <div className={styles.filterGroup}>
                            <label htmlFor="sortByAsc">Ordenação</label>
                            <select id="sortByAsc" name="sortByAsc" value={filter.sortByAsc} onChange={handleChange}>
                                <option value="false">Mais Recentes</option>
                                <option value="true">Mais Antigas</option>
                            </select>
                        </div>

                        <button
                            className={styles.clearFiltersBtn}
                            onClick={() => setFilter({ type: "", categoryId: "", startDate: "", endDate: "", sortByAsc: false })}
                        >
                            Limpar
                        </button>
                    </div>
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
                                            <td>{new Date(tx.date).toLocaleDateString("pt-BR", { timeZone: 'UTC' })}</td>
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

                            <TransactionForm
                                onTransactionCreated={handleTransactionCreated}
                                onCategoryCreated={handleCategoryCreated}
                            />
                        </div>
                    </div>
                )}

            </main>
            <Footer/>
        </>
    )
}

export default Transactions