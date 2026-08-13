import { instance } from "./api";
import type { product } from "../types/product";
import type { apiError } from "./apiError";
import axios from "axios";

export const getProducts = async (): Promise<product[]> => {
    try {
        const response = await instance.get<product[]>("/api/products");
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

export const getProductById = async (id: number): Promise<product> => {
    try {
        const response = await instance.get<product>(`/api/products/${id}`);
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

export const getProductByName = async (productName: string): Promise<product> => {
    try {
        const response = await instance.get<product>("/api/products/search", { params: { name: productName } });
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

export const getProductsContainingString = async (productNameContains: string): Promise<product[]> => {
    try {
        const response = await instance.get<product[]>("/api/products/search", { params: { nameContains: productNameContains } });
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

export const getProductsStartingWithString = async (nameStarts: string): Promise<product[]> => {
    try {
        const response = await instance.get<product[]>("/api/products/search", { params: { nameStartsWith: nameStarts } });
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

export const getProductsEndingWithString = async (nameEnds: string): Promise<product[]> => {
    try {
        const response = await instance.get<product[]>("/api/products/search", { params: { nameEndsWith: nameEnds } });
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

export const getProductsInStock = async (productsInStock: string): Promise<product[]> => {
    try {
        const response = await instance.get<product[]>("/api/products/search", { params: { inStock: productsInStock } });
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

export const getProductsOutOfStock = async (productsOutOfStock: string): Promise<product[]> => {
    try {
        const response = await instance.get<product[]>("/api/products/search", { params: { outOfStock: productsOutOfStock } });
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

export const getProductsLessThanPrice = async (priceLessThan: number): Promise<product[]> => {
    try {
        const response = await instance.get<product[]>("/api/products/search", { params: { lessThan: priceLessThan } });
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

export const getProductsEqualsPrice = async (priceEquals: number): Promise<product[]> => {
    try {
        const response = await instance.get<product[]>("/api/products/search", { params: { equal: priceEquals } });
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

export const getProductsGreaterThanPrice = async (priceGreater: number): Promise<product[]> => {
    try {
        const response = await instance.get<product[]>("/api/products/search", { params: { greaterThan: priceGreater } });
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
export const getProductsBetweenPrice = async (minimum: number, maximum: number): Promise<product[]> => {
    try {
        const response = await instance.get<product[]>("/api/products/search", { params: { minPrice: minimum, maxPrice: maximum } });
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

export const deleteProductById = async (id: number): Promise<void> => {
    try {
        await instance.delete(`/api/products/${id}`);
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

type productRequest = {
    productName: string;
    productPrice: number;
    productStock: number;
}

export const insertProduct = async (data: productRequest): Promise<product> => {
    try {
        const response = await instance.post<product>("/api/products", data);
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

export const modifyProduct = async (data: productRequest, id: number): Promise<product> => {
    try {
        const response = await instance.put<product>(`/api/products/${id}`, data);
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