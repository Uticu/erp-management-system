import type { orderDetails } from "./orderDetails";

export interface order {
    orderID: number;
    clientName: string;
    orderDate: string;
    orderStatus: string;
    orderDeliveryAddress: string;
    clientPhoneNumber: string;
    orderDetailsDTOList: orderDetails[];
}