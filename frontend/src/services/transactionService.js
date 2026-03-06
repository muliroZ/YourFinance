import api from "../api/axiosInstance.js"

export async function createTransaction(data) {
    const response = await api.post("/transaction", data, {
        headers: {
            "Idempotency-Key": crypto.randomUUID()
        }
    })
    return response.data
}

export async function listTransactions(params) {
    const response = await api.get("/transaction", { params })
    return response.data
}