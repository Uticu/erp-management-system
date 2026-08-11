import { instance } from "./api";
import type { client } from "../types/client";
import type { apiError } from "./apiError";
import axios from "axios";

export const getClients = async (): Promise<client[]> => {
    try {
        const response = await instance.get<client[]>("/api/client");
        return response.data;
    } catch (error) {
        if (axios.isAxiosError<apiError>(error)) {
            console.error(error.response?.data.message);
            console.error(error.response?.data.status);
            console.error(error.response?.data.dateTime);
            throw new Error(error.response?.data.message || "Server not responding");
        } else {
            throw error;
        }
    }
}

export const getClientById = async (id: number): Promise<client> => {
    try {
        const response = await instance.get<client>(`/api/client/${id}`);
        return response.data;
    } catch (error) {
        if (axios.isAxiosError<apiError>(error)) {
            console.error(error.response?.data.message);
            console.error(error.response?.data.status);
            console.error(error.response?.data.dateTime);
            throw new Error(error.response?.data.message || "Server not responding");
        } else {
            throw error;
        }
    }
}

export const getClientByEmail = async (email: string): Promise<client> => {
    try {
        const response = await instance.get<client>("/api/client/search", { params: { email: email } });
        return response.data;
    } catch (error) {
        if (axios.isAxiosError<apiError>(error)) {
            console.error(error.response?.data.message);
            console.error(error.response?.data.status);
            console.error(error.response?.data.dateTime);
            throw new Error(error.response?.data.message || "Server not responding");
        } else {
            throw error;
        }
    }
}

export const getClientsByName = async (name: string): Promise<client[]> => {
    try {
        const response = await instance.get<client[]>("/api/client/search", { params: { name: name } });
        return response.data;
    } catch (error) {
        if (axios.isAxiosError<apiError>(error)) {
            console.error(error.response?.data.message);
            console.error(error.response?.data.status);
            console.error(error.response?.data.dateTime);
            throw new Error(error.response?.data.message || "Server not responding");
        } else {
            throw error;
        }
    }
}

export const getClientsStartingWithString = async (startWith: string): Promise<client[]> => {
    try {
        const response = await instance.get<client[]>("/api/client/search", { params: { nameStartWith: startWith } });
        return response.data;
    } catch (error) {
        if (axios.isAxiosError<apiError>(error)) {
            console.error(error.response?.data.message);
            console.error(error.response?.data.status);
            console.error(error.response?.data.dateTime);
            throw new Error(error.response?.data.message || "Server not responding");
        } else {
            throw error;
        }
    }
}

export const getClientsEndingWithString = async (endsWith: string): Promise<client[]> => {
    try {
        const response = await instance.get<client[]>("/api/client/search", { params: { nameEndsWith: endsWith } });
        return response.data;
    } catch (error) {
        if (axios.isAxiosError<apiError>(error)) {
            console.error(error.response?.data.message);
            console.error(error.response?.data.status);
            console.error(error.response?.data.dateTime);
            throw new Error(error.response?.data.message || "Server not responding");
        } else {
            throw error;
        }
    }
}

export const getClientByPhoneNumber = async (number: string): Promise<client> => {
    try {
        const response = await instance.get<client>("/api/client/search", { params: { phoneNumber: number } });
        return response.data;
    } catch (error) {
        if (axios.isAxiosError<apiError>(error)) {
            console.error(error.response?.data.message);
            console.error(error.response?.data.status);
            console.error(error.response?.data.dateTime);
            throw new Error(error.response?.data.message || "Server not responding");
        } else {
            throw error;
        }
    }
}

type ClientRequestDTO = {
    clientName: string;
    clientEmail: string;
    clientAddress: string;
    clientPhoneNumber: string;
}

export const insertClient = async (data: ClientRequestDTO): Promise<client> => {
    try {
        const response = await instance.post<client>("/api/client", data);
        return response.data;
    } catch (error) {
        if (axios.isAxiosError<apiError>(error)) {
            console.error(error.response?.data.message);
            console.error(error.response?.data.status);
            console.error(error.response?.data.dateTime);
            throw new Error(error.response?.data.message || "Server not responding");
        } else {
            throw error;
        }
    }
}

export const modifyClient = async (data: ClientRequestDTO, id: number): Promise<client> => {
    try {
        const response = await instance.put<client>(`/api/client/${id}`, data);
        return response.data;
    } catch (error) {
        if (axios.isAxiosError<apiError>(error)) {
            console.error(error.response?.data.message);
            console.error(error.response?.data.status);
            console.error(error.response?.data.dateTime);
            throw new Error(error.response?.data.message || "Server not responding");
        } else {
            throw error;
        }
    }
}

export const deleteClient = async (id: number): Promise<void> => {
    try {
        const response = await instance.delete(`/api/client/${id}`);
        return;
    } catch (error) {
        if (axios.isAxiosError<apiError>(error)) {
            console.error(error.response?.data.message);
            console.error(error.response?.data.status);
            console.error(error.response?.data.dateTime);
            throw new Error(error.response?.data.message || "Server not responding");
        } else {
            throw error;
        }
    }
}