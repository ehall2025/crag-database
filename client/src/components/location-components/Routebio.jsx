import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./RouteBio.css";

const INITIAL_RATING_FORM = {
    difficulty: "",
    quality: "",
    danger: ""
};

const EMPTY_ROUTE_SUMMARY = {
    difficultyRating: 0,
    qualityRating: 0,
    dangerRating: 0
}

function RouteBio({ loggedInUser, setLoggedInUser }) {

    const [addSuccess, setAddSuccess] = useState()
    const { id } = useParams();
    const [errors, setErrors] = useState([]);
    const [route, setRoute] = useState({
        id: 0,
        name: "",
        areaId: 0,
        description: "",
        startPosition: ""
    });

    // =========================
    // Tracks the current user's rating inputs
    // =========================
    const [ratingForm, setRatingForm] = useState(INITIAL_RATING_FORM);


    const [routeSummary, setRouteSummary] = useState(EMPTY_ROUTE_SUMMARY);

    const averageDifficulty = routeSummary.difficultyRating;
    const averageQuality = routeSummary.qualityRating;
    const averageDanger = routeSummary.dangerRating;

    async function fetchRouteSummary() {
        try {
            const response = await fetch(`http://localhost:8080/api/route-summaries/${id}`,
            {
                headers: {
                    "Authorization": "Bearer " + loggedInUser.jwt
                }
            });

            if (!response.ok) {
                setRouteSummary(EMPTY_ROUTE_SUMMARY);
                return;
            }

            const payload = await response.json();

            setRouteSummary(payload);
        
        } catch {
            setRouteSummary(EMPTY_ROUTE_SUMMARY);
        }
    }


    // Update a rating field whenver a user changes an input in the rating form
    function handleRatingChange(e) {
        const { name, value } = e.target;

        setRatingForm(prev => ({
            ...prev,
            [name]: value
        }));
    }

    // Submits a new route rating 
    async function submitRating() {

        setErrors([]);

        const response = await fetch("http://localhost:8080/api/route-summaries",
        {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + loggedInUser.jwt,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                routeId: Number(id),
                userId: loggedInUser.user.id,
                difficultyRating: Number(ratingForm.difficulty),
                qualityRating: Number(ratingForm.quality),
                dangerRating: Number(ratingForm.danger)
            })
        }
        );

         if (response.ok) {
            setAddSuccess("Rating submitted");
            
            await fetchRouteSummary();

            setRatingForm(INITIAL_RATING_FORM);

         } else {
            const payload = await response.text();
            setErrors([payload]);
         }
    }

    // Load all rating suggestions whenever route id changes
    useEffect(() => {
        fetchRouteSummary();
    }, [id]);

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
    }, [id])

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

                <div className="route-bio-section">
                    
                    <h4 className="section-title">Route Statistics</h4>
                    
                    <div className="stats-grid">
                        
                        <div className="stats-card">
                            <span className="stats-number">
                                {averageDifficulty}
                            </span>
                            <span className="stats-label">
                                Avg Difficulty
                            </span>
                        </div>

                        <div className="stats-card">
                            <span className="stats-number">
                                {averageQuality}
                            </span>
                            <span className="stats-label">
                                Avg Quality
                            </span>
                        </div>

                        <div className="stats-card">
                            <span className="stats-number">
                                {averageDanger}
                            </span>
                            <span className="stats-label">
                                Max Danger
                            </span>
                        </div>
                    </div>
                </div>

                {loggedInUser && (
                    <div className="route-bio-section rating card">

                        <h4 className="section-title">Submit Rating</h4>

                        <div className="rating-field">
                            <label>Difficulty (1-10)</label>
                            <input type="number" min="1" max="10" name="difficulty" placeholder="Difficulty" value={ratingForm.difficulty} onChange={handleRatingChange} />
                        </div>

                        <div className="rating-field">
                            <label>Quality (1-5)</label>
                            <input type="number" min="1" max="5" name="quality" placeholder="Quality" value={ratingForm.quality} onChange={handleRatingChange} />
                        </div>

                        <div className="rating-field">
                            <label>Danger (1-5)</label>
                            <input type="number" min="1" max="5" name="danger" placeholder="Danger" value={ratingForm.danger} onChange={handleRatingChange} />
                        </div>

                            <button className="route-button primary rating-submit" onClick={submitRating}>Submit Rating</button>
                    </div>
                )}

                

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

                {errors.length > 0 && (
                    <div className="route-errors">
                        {errors.map((error, index) => (
                            <p key={index}>{error}</p>
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
}

export default RouteBio