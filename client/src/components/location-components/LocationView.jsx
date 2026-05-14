import { useEffect, useState } from "react";
import { NavLink, Link } from "react-router-dom";


function LocationView() {

    const [locations, setLocations] = useState([])

    useEffect(() => {
        fetch("http://localhost:8080/api/locations")
        .then(response => response.json())
        .then(payload => setLocations(payload))
    }, [])


    return (
        <>
            <div>location view</div>
            <div>
                {locations.map((loc) => {
                    return (
                        <div>
                            <Link to={"/locations/" + loc.id}>{loc.region}</Link>
                        </div>
                    )
                })}
            </div>
        </>
    );
}

export default LocationView