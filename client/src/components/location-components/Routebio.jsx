import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import "./RouteBio.css";

function RouteBio({ loggedInUser, setLoggedInUser }) {

    const [addSuccess, setAddSuccess] = useState()
    const { id } = useParams();
    const [route, setRoute] = useState({
        id: 0,
        name: "",
        areaId: 0,
        description: "",
        startPosition: ""
    })

    async function handleAdd(event) {

        const response = await fetch("http://localhost:8080/api/profile", {
            "method": "POST",
            "headers": {
                "Authorization": "Bearer " + loggedInUser.jwt,
                "Content-Type": "application/json",
            },
            "body": JSON.stringify({
                "listId": loggedInUser.user.tickList.id,
                "routeId": id
            })
        })

        if (response.status >= 200 && response.status < 300) {
            const payload = await response.json()
            updateUser(loggedInUser, payload)
            setAddSuccess("Successfully added to ticks list")
        } else {
            const payload = await response.json()
            setErrors(payload)
        }
    }

    async function handleAddTodo(event) {

        const response = await fetch("http://localhost:8080/api/profile", {
            "method": "POST",
            "headers": {
                "Authorization": "Bearer " + loggedInUser.jwt,
                "Content-Type": "application/json",
            },
            "body": JSON.stringify({
                "listId": loggedInUser.user.todoList.id,
                "routeId": id
            })
        })

        if (response.status >= 200 && response.status < 300) {
            const payload = await response.json()
            updateUser(loggedInUser, payload)
            setAddSuccess("Successfully added to todo list")
        } else {
            const payload = await response.json()
            setErrors(payload)
        }
    }

    function updateUser(oldUser, newRouteList) {
        let newUser = { ...oldUser }

        newUser.user.tickList.routes = newRouteList

        setLoggedInUser(newUser)
    }

    useEffect(() => {
        fetch("http://localhost:8080/api/locations/route/" + id)
            .then(response => response.json())
            .then(payload => setRoute(payload))
    }, [])

    return (
        <div className="route-bio-page">
            <div className="route-bio-card">

                <div className="route-header">
                    <h1>{route.name}</h1>
                </div>

                <div className="route-bio-section">
                    <h4 className="section-title">Description</h4>
                    <p className="route-description">{route.description}</p>
                </div>

                <div className="route-bio-section">
                    <h4 className="section-title">Start Position</h4>
                    <p className="route-start">{route.startPosition}</p>
                </div>

                {loggedInUser && (
                    <div className="route-actions">
                        <button className="route-button primary" onClick={handleAdd}>
                            Log Ascent
                        </button>

                        <button className="route-button secondary" onClick={handleAddTodo}>
                            Add to Todo
                        </button>
                    </div>
                )}

                {addSuccess && (
                    <p className="route-success">
                        {addSuccess}
                    </p>
                )}

            </div>
        </div>
    );
}

export default RouteBio