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
            <div className="d-inline-flex p-2 gap-5">
                {locations.map((loc) => (
                        <div className="card " key={loc.id}>
                            <div className="card=body">
                                <Link to={"/locations/" + loc.id}>
                                    <h3><div>{loc.region}</div></h3>
                                    <div>{"," + loc.country}</div>
                                </Link>
                            </div>
                        </div>
                    )
                )}
            </div>
        </>
    );
}

export default LocationView