import { Outlet } from "react-router-dom";

function UserLayout() {
    return (
        <>
            <h3>You are now working with the users resource</h3>
            <Outlet />
        </>
    )
}

export default UserLayout