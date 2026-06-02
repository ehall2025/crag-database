import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import LocationPage from "./generic-location-components/LocationPage";
import LocationGrid from "./generic-location-components/LocationGrid";
import "./location-style/LocationDetails.css";

function LocationBio() {

    const { id } = useParams();
    const [location, setLocation] = useState({
        id: 0,
        region: "",
        country: "",
        description: "",
        crags: []
    })

    useEffect(() => {
        fetch("http://localhost:8080/api/locations/" + id)
            .then(response => response.json())
            .then(payload => setLocation(payload))
    }, [])

    return (
        <LocationPage>
            <div className="location-title">
                <h1>{location.region}</h1>
                <span>{location.country}</span>
            </div>

            <h4 className="location-section-title">Description</h4>
            <p className="location-description">{location.description}</p>

            {location.crags?.length > 0 && (
                <div className="location-content-section">
                    <h4 className="location-section-title">Crags</h4>

                    <LocationGrid
                        items={location.crags}
                        getPath={(crag) => "/locations/crag/" + crag.id}
                    />
                </div>
            )}
        </LocationPage>
    );
}

export default LocationBio