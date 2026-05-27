import { useEffect, useState } from "react";
import { NavLink, Link } from "react-router-dom";
import "./LocationView.css";

function LocationView({ locations, setLocations }) {

    useEffect(() => {
        fetch("http://localhost:8080/api/locations")
            .then(response => response.json())
            .then(payload => setLocations(payload))
    }, [])


    return (
            <div className="locations-page">
                <div className="locations-card">

                    <div className="locations-header">
                        <h1>Locations</h1>
                    </div>

                    <ul className="locations-list">
                        {locations.map((loc) => (
                            <li className="location-item" key={loc.id}>

                                <Link
                                    className="location-link"
                                    to={"/locations/" + loc.id}
                                >
                                    <h5>
                                        {loc.region}, {loc.country}
                                    </h5>
                                </Link>

                            </li>
                        ))}
                    </ul>

                </div>
            </div>
    );
}

export default LocationView