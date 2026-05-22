import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";


function CragBio() {

    const { id } = useParams();
    const [crag, setCrag] = useState({
        id:0,
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
        <>
            <div className="card">
                <div className="card-header">
                    <h2>{crag.name}</h2>
                </div>
                <div className="card-body">
                    <h5>Description</h5>
                    <p>{crag.description}</p>
                    <h5>Areas</h5>
                    <div className="card col-2">
                        <ul className="list-group">
                            {crag.areas.map((area) => (
                                <li key={area.id} className="list-group-item">
                                    <Link to={"/locations/area/" + area.id}>{area.name}</Link>
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
            </div>
        </>
    );
}

export default CragBio