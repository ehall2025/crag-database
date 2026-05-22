import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function StagedRoutes ({ loggedInUser }) {

    const navigate = useNavigate
    const [stagedRoutes, setStagedRoutes] = useState([])
    const [errors, setErrors] = useState([])

    useEffect(() => {
            fetch("http://localhost:8080/api/post_route/admin", {
                method: "GET",
                headers : {
                    "Authorization": "Bearer " + loggedInUser.jwt
                }
            })
            .then(response => response.json())
            .then(payload => setStagedRoutes(payload))
        }, [])

    function loadStagedRoutes () {
        fetch("http://localhost:8080/api/post_route/admin", {
                method: "GET",
                headers : {
                    "Authorization": "Bearer " + loggedInUser.jwt
                }
            })
            .then(response => response.json())
            .then(payload => setStagedRoutes(payload))
    }

        async function handlePost (event) {
            let newRoute = {}

            stagedRoutes.forEach((route) => {
                if (route.id = event.target.value) {
                    newRoute = {
                        id: parseInt(route.id),
                        areaId: route.areaId,
                        name: route.name,
                        description: route.description,
                        startPosition: route.startPosition
                    }
                }
            })

            const response = await fetch("http://localhost:8080/api/post_route/admin", {
                method: "POST",
                headers: {
                    "Authorization": "Bearer " + loggedInUser.jwt,
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(newRoute)
            })

            if (response.status >= 200 && response.status < 300) {
                loadStagedRoutes()
            } else {
                const payload = await response.json()
                setErrors(payload)
            }
        }

        async function handleDelete (event) {
            console.log(event.target.value)

            const response = await fetch("http://localhost:8080/api/post_route/admin/" + event.target.value, {
                method: "DELETE",
                headers: {
                    "Authorization": "Bearer " + loggedInUser.jwt
                }
            })

            if (response.status >= 200 && response.status < 300) {
                loadStagedRoutes()
            } else {
                const payload = await response.json()
                setErrors(payload)
            }
        }

    return (
        <>
            <h1>Staged Routes</h1>
            {errors.length > 0 ?
                <ul>{errors.map(error => <li key={error}>{error}</li>)}</ul>
                : null
            }
            <div className="d-inline-flex gap-5">
                {stagedRoutes.map((route) => (
                    <div key={route.id} className="card col-6">
                        <div class="card-header">
                            <h4>{route.name}</h4>
                        </div>
                        <div className="card-body">
                            <h6>Description</h6>
                            <p>{route.description}</p>
                            <h6>Start Position</h6>
                            <p>{route.startPosition}</p>
                        </div>
                        <div className="card-footer d-inline-flex gap-1">
                            <button className="btn btn-primary" value={route.id} onClick={handlePost}>Post</button>
                            <button className="btn btn-danger" value={route.id} onClick={handleDelete}>Delete</button>
                        </div>
                    </div>
                ))}
            </div>
        </>
    );
}

export default StagedRoutes