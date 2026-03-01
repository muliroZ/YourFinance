import api from "../api/axiosInstance.js"

export async function create(data) {
    const response = await api.post("/transaction", data, {
        headers: {
            "Idempotency-Key": crypto.randomUUID()
        }
    })
    return response.data
}

export async function list(params) {
    const response = await api.get("/transaction", { params })
    return response.data
}