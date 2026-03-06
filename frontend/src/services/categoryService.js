import api from "../api/axiosInstance.js"

export async function createCategory(data) {
    const response = await api.post("/category", data)
    return response.data
}

export async function listCategories() {
    const response = await api.get("/category")
    return response.data
}