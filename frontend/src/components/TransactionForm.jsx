import {useEffect, useState} from "react"
import {list} from "../services/categoryService.js"
import {create} from "../services/transactionService.js"
import styles from "./TransactionForm.module.css"
import CategoryForm from "./CategoryForm.jsx";

function TransactionForm({ onTransactionCreated }) {
    const [isModalOpen, setIsModalOpen] = useState(false)
    const [categories, setCategories] = useState([])
    const [form, setForm] = useState({
        amount: "",
        type: "EXPENSE",
        date: new Date().toISOString().split('T')[0],
        description: "",
        categoryId: ""
    })

    useEffect(() => {
        async function fetchCategories() {
            try {
                const data = await list()
                setCategories(data.categories)
            } catch (err) {
                console.error("Erro ao carregar categorias", err)
            }
        }
        fetchCategories()
    }, []);

    function handleCategoryCreated() {
        setIsModalOpen(false)
    }

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value })
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            const payload = {
                ...form,
                amount: parseFloat(form.amount),
            }

            await create(payload)
            alert("Transação criada com sucesso")

            if (onTransactionCreated) onTransactionCreated()

        } catch (err) {
            alert("Erro ao criar transação")
            console.error(err)
        }
    }

    return (
        <>
            <form onSubmit={handleSubmit} className={styles.formContainer}>
                <h3 className={styles.formTitle}>Nova Transação</h3>

                <div className={styles.formFields}>
                    <div className={styles.inputGroup}>
                        <label htmlFor={"description"}>Descrição</label>
                        <input type="text" id="description" name="description" placeholder="Descrição" value={form.description} onChange={handleChange} required />
                    </div>

                    <div className={styles.formRow}>
                        <div className={styles.inputGroup}>
                            <label htmlFor={"amount"}>Valor (R$)</label>
                            <input type="number" step="0.01" id="amount" name="amount" placeholder="Valor" value={form.amount} onChange={handleChange} required />
                        </div>
                        <div className={styles.inputGroup}>
                            <label htmlFor={"date"}>Data</label>
                            <input type="date" id="date" name="date" value={form.date} onChange={handleChange} required />
                        </div>
                    </div>

                    <div className={styles.formRow}>
                        <div className={styles.inputGroup}>
                            <label htmlFor={"type"}>Tipo</label>
                            <select id="type" name="type" value={form.type} onChange={handleChange}>
                                <option value="EXPENSE">Despesa</option>
                                <option value="INCOME">Receita</option>
                            </select>
                        </div>
                        <div className={styles.inputGroup}>
                            <label htmlFor={"categoryId"}>Categoria</label>
                            <select id={"categoryId"} name="categoryId" value={form.categoryId} onChange={handleChange} required>
                                <option value="">Selecione a Categoria</option>
                                {categories.map(cat => (
                                    <option key={cat.id} value={cat.id}>{cat.name}</option>
                                ))}
                            </select>
                            <button
                                type="button"
                                className={styles.newCategoryBtn}
                                onClick={() => setIsModalOpen(true)}
                            >
                                + Crie uma categoria
                            </button>
                        </div>
                    </div>

                    <button type="submit" className={styles.submitBtn}>Salvar Transação</button>
                </div>
            </form>

            {isModalOpen && (
                <div className={styles.modalOverlay}>
                    <div className={styles.modalContent}>
                        <button
                            className={styles.closeModalBtn}
                            onClick={() => setIsModalOpen(false)}
                        >
                            &times;
                        </button>

                        <CategoryForm onCategoryCreated={handleCategoryCreated}/>
                    </div>
                </div>
            )}
        </>
    )
}

export default TransactionForm