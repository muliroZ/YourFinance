import api from "../api/axiosInstance.js"

export async function create(data) {
    const response = await api.post("/category", data)
    return response.data
}

export async function list() {
    const response = await api.get("/category")
    return response.data
}