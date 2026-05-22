import { useEffect, useState } from "react";
import { NavLink, Link } from "react-router-dom";


function LocationView({ locations, setLocations }) {

    useEffect(() => {
        fetch("http://localhost:8080/api/locations")
        .then(response => response.json())
        .then(payload => setLocations(payload))
    }, [])


    return (
        <>
        <div className="card">
            <div className="card-header">
                <h1>Locations</h1>
            </div>
            
            <div className="card-body">
                <ul className="list-group">
                    {locations.map((loc) => (
                        <li className="list-group-item" key={loc.id}>
                            <Link className="d-inline-flex align-items-baseline" to={"/locations/" + loc.id}>
                                <h5>{loc.region + "," + loc.country}</h5>
                            </Link>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
            
        </>
    );
}

export default LocationView