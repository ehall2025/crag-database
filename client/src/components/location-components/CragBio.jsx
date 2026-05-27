import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import "./CragBio.css";

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
        <div className="crag-bio-page">
            <div className="crag-bio-card">

                <div className="crag-title">
                    <h1>{crag.name}</h1>
                </div>

                <div>
                    <h4 className="section-title">Description</h4>

                    <p className="crag-description">
                        {crag.description}
                    </p>
                </div>

                <div>
                    <h4 className="section-title">Areas</h4>

                    <div className="area-grid">
                        {crag.areas.map((area) => (
                            <Link
                                key={area.id}
                                className="area-card"
                                to={"/locations/area/" + area.id}
                            >
                                <h5>{area.name}</h5>
                            </Link>
                        ))}
                    </div>
                </div>

            </div>
        </div>
    );
}

export default CragBio