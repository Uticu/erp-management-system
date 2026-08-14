import { instance } from "./api";
import type { bill } from "../types/bill";
import type { apiError } from "./apiError";
import axios from "axios";

export const getAllBills = async (): Promise<bill[]> => {
    try {
        const response = await instance.get<bill[]>("/api/bills");
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

export const getBillsById = async (id: number): Promise<bill> => {
    try {
        const response = await instance.get<bill>(`/api/bills/${id}`);
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

export const getBillsBetweenDates = async (minDate: string, maxDate: string): Promise<bill[]> => {
    try {
        const response = await instance.get<bill[]>("/api/bills/date", { params: { minDate: minDate, maxDate: maxDate } });
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

export const getBillsBySeriesAndNumber = async (series: string, number: number): Promise<bill> => {
    try {
        const response = await instance.get<bill>("/api/bills/seriesAndNumber", { params: { series: series, number: number } });
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

type billRequest = {
    orderID: number;
    billSeries: string;
}

export const insertBill = async (data: billRequest): Promise<bill> => {
    try {
        const response = await instance.post("/api/bills");
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

export const deleteBill = async (id: number): Promise<void> => {
    try {
        await instance.delete(`/api/bills/${id}`);
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