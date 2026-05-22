import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";


function LocationBio() {

    const { id } = useParams();
    const [location, setLocation] = useState({
        id:0,
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
        <>
            <div className="card">
                <div className="card-header">
                    <div className="d-inline-flex align-items-baseline">
                        <h2>{location.region}</h2>
                        <h6>, {location.country}</h6>
                    </div>
                </div>
                <div className="card-body">
                        <h5>Description</h5>
                    
                    <p>{location.description}</p>
                    <h5>Crags</h5>
                    <div className="card col-3">
                        <ul className="list-group list-group-flush">
                            {location.crags.map((crag) => {
                                return (
                                    <li key={crag.id} className="list-group-item">
                                            <Link to={"/locations/crag/" + crag.id}>{crag.name}</Link>
                                    </li>
                                )
                            })}
                        </ul>
                    </div>
                </div>
            </div>
        </>
    );
}

export default LocationBio