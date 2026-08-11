import axios, { AxiosError } from "axios";

export const instance = axios.create({
    baseURL: "http://localhost:8080",
    headers: { "Content-Type": "application/json" },
})