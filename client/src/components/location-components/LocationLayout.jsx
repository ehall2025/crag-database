import { Outlet } from "react-router-dom";

function LocationLayout() {
    return (
        <>
            <h3>You are now working with the location resource</h3>
            <Outlet />
        </>
    )
}

export default LocationLayout