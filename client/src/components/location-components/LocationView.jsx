import { useEffect, useState } from "react";
import { NavLink, Link } from "react-router-dom";
import LocationPage from "./generic-location-components/LocationPage";
import LocationGrid from "./generic-location-components/LocationGrid";
import "./location-style/LocationDetails.css";

function LocationView({ locations, setLocations }) {

    useEffect(() => {
        fetch("http://localhost:8080/api/locations")
            .then(response => response.json())
            .then(payload => setLocations(payload))
    }, [])


    return (
        <LocationPage>
            <div className="location-title">
                <h1>Locations</h1>
            </div>

            <LocationGrid
                items={locations}
                getPath={(loc) => "/locations/" + loc.id}
                getLabel={(loc) => loc.region + ", " + loc.country}
            />
        </LocationPage>
    );
}

export default LocationView