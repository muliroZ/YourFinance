import axiosInstance from "../api/axiosInstance.js";

export async function login(email, password) {
    const response = await axiosInstance.post("/auth/login", {
        email,
        password
    })

    return response.data
}

export async function register(data) {
    const response = await axiosInstance.post("/auth/register", data)
    return response.data
}
