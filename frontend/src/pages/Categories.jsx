import styles from './Categories.module.css'
import Header from "../components/Header.jsx";
import Footer from "../components/Footer.jsx";
import {useEffect, useState} from "react";
import {listCategories} from "../services/categoryService.js";
import CategoryForm from "../components/CategoryForm.jsx";

function Categories() {
    const [categories, setCategories] = useState([])
    const [isLoading, setIsLoading] = useState(true)
    const [isModalOpen, setIsModalOpen] = useState(false)
    const [refreshKey, setRefreshKey] = useState(0)

    useEffect(() => {
        async function fetchCategories() {
            setIsLoading(true)
            try {
                const data = await listCategories()
                setCategories(data.categories)
            } catch (err) {
                console.error("Erro ao carregar categorias", err)
            } finally {
                setIsLoading(false)
            }
        }
        fetchCategories()
    }, [refreshKey]);

    function handleCategoryCreated() {
        setIsModalOpen(false)
        setRefreshKey(key => key + 1)
    }

    return (
        <>
            <Header/>
            <main className={styles.mainContainer}>
                <div className={styles.pageHeader}>
                    <div>
                        <h1>Suas Categorias</h1>
                        <p>Crie, edite e exclua suas categorias livremente.</p>
                    </div>
                    <button
                        className={styles.newBtn}
                        onClick={() => setIsModalOpen(true)}
                    >
                        + Nova Categoria
                    </button>
                </div>

                <div className={styles.tableContainer}>
                    {isLoading ? (
                        <div className={styles.loadingState}>Carregando as categorias...</div>
                    ) : (
                        <table className={styles.dataTable}>
                            <thead>
                                <tr>
                                    <th>Nome</th>
                                    <th className={styles.actionsColumn}>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                {categories.length === 0 ? (
                                    <tr>
                                        <td colSpan={2} className={styles.emptyState}>
                                            Nenhuma categoria registrada ainda.
                                        </td>
                                    </tr>
                                ) : (
                                    categories.map(c => (
                                        <tr key={c.id}>
                                            <td>{c.name}</td>
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

                        <CategoryForm onCategoryCreated={handleCategoryCreated}/>
                    </div>
                </div>
            )}

            <Footer/>
        </>
    )
}

export default Categories