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
            <h1>Locations</h1>
            <div>
                {locations.map((loc) => {
                    return (
                        <div key={loc.id}>
                            <h3><Link to={"/locations/" + loc.id}>{loc.region + ", " + loc.country}</Link></h3>
                        </div>
                    )
                })}
            </div>
        </>
    );
}

export default LocationView