import { Link } from "react-router-dom";
import "./RouteListTable.css";


function RouteListTable({ routeList, loggedInUser, setLoggedInUser }) {

    async function handleDelete(event) {

        const response = await fetch("http://localhost:8080/api/profile", {
            "method": "DELETE",
            "headers": {
                "Authorization": "Bearer " + loggedInUser.jwt,
                "Content-Type": "application/json",
            },
            "body": JSON.stringify({
                "listId": routeList.id,
                "routeId": event.target.attributes.routeId.value
            })
        })

        if (response.status >= 200 && response.status < 300) {
            const payload = await response.json()
            updateUser(loggedInUser, payload)
        } else {
            const payload = await response.json()
            setErrors(payload)
        }
    }

    function updateUser(oldUser, newRouteList) {
        let newUser = { ...oldUser }

        if (routeList.name == 'todo') {
            newUser.user.todoList.routes = newRouteList
        } else {
            newUser.user.tickList.routes = newRouteList
        }

        setLoggedInUser(newUser)
    }

    return (
        <ul className="route-list">

            {routeList.routes.map(route => (

                <li
                    key={route.id}
                    className="route-list-item"
                >

                    <Link
                        className="route-link"
                        to={"/locations/route/" + route.id}
                    >
                        {route.name}
                    </Link>

                    <button
                        className="remove-route-button"
                        routeId={route.id}
                        onClick={handleDelete}
                    >
                        Remove
                    </button>

                </li>

            ))}

        </ul>
    );
}

export default RouteListTable