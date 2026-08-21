import { createBrowserRouter } from "react-router-dom";
import {Layout} from "../components/RootLayout";
import {Bill} from "../pages/BillPage";
import {Client} from "../pages/ClientPage";
import {Product} from "../pages/ProductPage";
import {Home} from "../pages/HomePage";
import {Order} from "../pages/OrderPage";

export const router = createBrowserRouter([
    {
        path: "/",
        Component: Layout,
        children: [
            {
                index: true,
                Component: Home,
            },
            {
                path: "clients",
                Component: Client,
            },
            {
                path: "products",
                Component: Product,
            },
            {
                path: "orders",
                Component: Order,
            },
            {
                path: "bills",
                Component: Bill,
            },
        ],
    },
]);