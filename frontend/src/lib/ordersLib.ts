import { instance } from "./api";
import type { order } from "../types/order";
import type { product } from "../types/product";
import type { orderDetails } from "../types/orderDetails";
import type { apiError } from "./apiError";
import axios from "axios";

export const getOrders = async (): Promise<order[]> => {
    try {
        const response = await instance.get<order[]>("/api/orders");
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

export const getOrderById = async (id: number): Promise<order> => {
    try {
        const response = await instance.get<order>(`/api/orders/${id}`);
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

export const getOrdersByClientId = async (id: number): Promise<order[]> => {
    try {
        const response = await instance.get<order[]>(`/api/orders/client/${id}`);
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

export const getOrdersProductsByClientId = async (id: number): Promise<product[]> => {
    try {
        const response = await instance.get<product[]>(`/api/orders/client/products/${id}`);
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

export const getOrdersBetweenDates = async (minDate: string, maxDate: string): Promise<order[]> => {
    try {
        const response = await instance.get<order[]>("/api/orders/date", { params: { minDate: minDate, maxDate: maxDate } });
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

type orderRequest = {
    clientID: number;
    orderDeliveryAddress: string;
    cartItems: orderDetails[];
}

type status = "PENDING" | "PROCESSING" | "SHIPPED" | "COMPLETED" | "CANCELLED";

export const getOrdersByStatus = async (orderStatus: status): Promise<order[]> => {
    try {
        const response = await instance.get<order[]>("/api/orders/status", { params: { orderStatus: orderStatus } });
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

export const getOrdersByDeliveryAddress = async (address: string): Promise<order[]> => {
    try {
        const response = await instance.get<order[]>("/api/orders/deliveryAddress", { params: { address: address } });
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

export const deleteOrderById = async (id: number): Promise<void> => {
    try {
        await instance.delete(`/api/orders/${id}`);
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

export const cancelOrderById = async (id: number): Promise<order> => {
    try {
        const response = await instance.put<order>(`/api/orders/cancel/${id}`);
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

export const modifyOrder = async (data: orderRequest, id: number): Promise<order> => {
    try {
        const response = await instance.put<order>(`/api/orders/modify/${id}`, data);
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

export const modifyOrderStatus = async (id: number, orderStatus: status): Promise<order> => {
    try {
        const response = await instance.put<order>(`/api/orders/status/${id}`, orderStatus);
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

export const insertOrder = async (data: orderRequest): Promise<order> => {
    try {
        const response = await instance.post<order>("/api/orders", data);
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