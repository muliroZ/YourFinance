import axiosInstance from "../api/axiosInstance.js";

export async function login(data) {
    const response = await axiosInstance.post("/auth/login", data)
    return response.data
}

export async function register(data) {
    const response = await axiosInstance.post("/auth/register", data)
    return response.data
}
