import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import LocationPage from "./generic-location-components/LocationPage";
import LocationGrid from "./generic-location-components/LocationGrid";
import "./location-style/LocationDetails.css";

function AreaBio() {

    const { id } = useParams();
    const [area, setArea] = useState({
        id: 0,
        name: "",
        cragId: 0,
        superAreaId: 0,
        description: "",
        subareas: [],
        routes: []
    })

    useEffect(() => {
        fetch("http://localhost:8080/api/locations/area/" + id)
            .then(response => response.json())
            .then(payload => setArea(payload))
    }, [id])

    return (
        <LocationPage>
            <div className="location-title">
                <h1>{area.name}</h1>
            </div>

            <h4 className="location-section-title">Description</h4>
            <p className="location-description">{area.description}</p>

            {area.subareas?.length > 0 && (
                <div className="location-content-section">
                    <h4 className="location-section-title">Subareas</h4>

                    <LocationGrid
                        items={area.subareas}
                        getPath={(subarea) => "/locations/area/" + subarea.id}
                    />
                </div>
            )}

            {area.routes?.length > 0 && (
                <div className="location-content-section">
                    <h4 className="location-section-title">Routes</h4>

                    <LocationGrid
                        items={area.routes}
                        getPath={(route) => "/locations/route/" + route.id}
                        variant="route-card"
                    />
                </div>
            )}
        </LocationPage>
    );
}


export default AreaBio