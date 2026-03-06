import {createCategory, listCategories} from "../services/categoryService.js";
import {useEffect, useState} from "react";
import ItemPanel from "./ItemPanel.jsx";
import styles from './CategoryForm.module.css'

function CategoryForm({ onCategoryCreated }) {
    const [categories, setCategories] = useState([])
    const [form, setForm] = useState({
        name: ""
    })

    useEffect(() => {
        async function fetchCategories() {
            try {
                const data = await listCategories()
                setCategories(data.categories)
            } catch (error) {
                console.error("Erro ao carregar categorias", error)
            }
        }
        fetchCategories()
    }, []);

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value })
    }

    const handleSubmit = async (e) => {
        e.preventDefault()

        try {
            const payload = { ...form }

            await createCategory(payload)
            alert("Categoria criada com sucesso!")

            if (onCategoryCreated) onCategoryCreated()

        } catch (err) {
            alert("Erro ao criar categoria")
            console.error(err)
        }
    }

    return (
        <form onSubmit={handleSubmit} className={styles.formContainer}>
            <h3 className={styles.formTitle}>Nova Categoria</h3>

            <div className={styles.formFields}>
                <div className={styles.formPanel}>
                    <strong>Categorias existentes</strong>
                    <ItemPanel items={categories} noItemsMessage={"Crie sua primeira categoria"}/>
                </div>
                <div className={styles.inputGroup}>
                    <label htmlFor={"name"}>Nome</label>
                    <input id={"name"} name={"name"} placeholder={"Nome"} value={form.name} onChange={handleChange} required/>
                </div>
            </div>
            <button type={"submit"} className={styles.submitBtn}>Criar</button>
        </form>
    )
}

export default CategoryForm