import { NavLink, Outlet } from 'react-router-dom';

export const Layout = () => {
    return (
        <div className= "root-layout" >
            <nav>
                <NavLink to = "clients"> Client </NavLink>
                <NavLink to = "products"> Product </NavLink>
                <NavLink to = "orders"> Order </NavLink>
                <NavLink to = "bills"> Bill </NavLink>
            </nav>
            <Outlet/>
        </div>
    )
}