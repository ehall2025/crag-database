import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import LocationPage from "./generic-location-components/LocationPage";
import LocationGrid from "./generic-location-components/LocationGrid";
import "./location-style/LocationDetails.css";

function CragBio() {

    const { id } = useParams();
    const [crag, setCrag] = useState({
        id: 0,
        name: "",
        locationId: 0,
        description: "",
        areas: []
    })

    useEffect(() => {
        fetch("http://localhost:8080/api/locations/crag/" + id)
            .then(response => response.json())
            .then(payload => setCrag(payload))
    }, [])

    return (
        <LocationPage>
            <div className="location-title">
                <h1>{crag.name}</h1>
            </div>

            <h4 className="location-section-title">Description</h4>
            <p className="location-description">{crag.description}</p>

            {crag.areas?.length > 0 && (
                <div className="location-content-section">
                    <h4 className="location-section-title">Areas</h4>

                    <LocationGrid
                        items={crag.areas}
                        getPath={(area) => "/locations/area/" + area.id}
                    />
                </div>
            )}
        </LocationPage>
    );
}

export default CragBio