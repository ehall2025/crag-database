import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import "./LocationBio.css";

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
            <div className="location-bio-page">

                <div className="location-bio-card">

                    <div className="location-title">
                        <h1>{location.region}</h1>
                        <span>{location.country}</span>
                    </div>

                    <div>
                        <h4 className="section-title">
                            Description
                        </h4>

                        <p className="location-description">
                            {location.description}
                        </p>
                    </div>

                    <div>
                        <h4 className="section-title">
                            Crags
                        </h4>

                        <div className="crag-grid">

                            {location.crags.map((crag) => (
                                <Link
                                    key={crag.id}
                                    className="crag-card"
                                    to={"/locations/crag/" + crag.id}
                                >
                                    <h5>{crag.name}</h5>
                                </Link>
                            ))}

                        </div>
                    </div>

                </div>

            </div>
    );
}

export default LocationBio