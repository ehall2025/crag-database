import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./StagedRoutes.css";

function StagedRoutes({ loggedInUser }) {

    const navigate = useNavigate
    const [stagedRoutes, setStagedRoutes] = useState([])
    const [errors, setErrors] = useState([])

    useEffect(() => {
        fetch("http://localhost:8080/api/post_route/admin", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + loggedInUser.jwt
            }
        })
            .then(response => response.json())
            .then(payload => setStagedRoutes(payload))
    }, [])

    function loadStagedRoutes() {
        fetch("http://localhost:8080/api/post_route/admin", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + loggedInUser.jwt
            }
        })
            .then(response => response.json())
            .then(payload => setStagedRoutes(payload))
    }

    async function handlePost(event) {
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

    async function handleDelete(event) {
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
        <div className="staged-page">
            <div className="staged-body">
                <div className="staged-header">
                    <h1>Staged Routes</h1>

                    <p>
                        Review and approve submitted routes.
                    </p>
                </div>

                {errors.length > 0 && (
                    <ul className="error-list">
                        {errors.map(error => (
                            <li key={error}>{error}</li>
                        ))}
                    </ul>
                )}

                <div className="staged-grid">

                    {stagedRoutes.map((route) => (

                        <div key={route.id} className="staged-card">

                            <div className="staged-card-header">
                                <h3>{route.name}</h3>
                            </div>

                            <div className="staged-card-body">

                                <div className="staged-section">
                                    <h5>Description</h5>
                                    <p>{route.description}</p>
                                </div>

                                <div className="staged-section">
                                    <h5>Start Position</h5>
                                    <p>{route.startPosition}</p>
                                </div>

                            </div>

                            <div className="staged-card-footer">

                                <button
                                    className="approve-button"
                                    value={route.id}
                                    onClick={handlePost}
                                >
                                    Approve
                                </button>

                                <button
                                    className="delete-button"
                                    value={route.id}
                                    onClick={handleDelete}
                                >
                                    Delete
                                </button>

                            </div>

                        </div>

                    ))}

                </div>

            </div>
            
        </div>
    );
}

export default StagedRoutes