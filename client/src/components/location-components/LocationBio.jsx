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
            <h2>{location.region}</h2>
            <p>{location.description}</p>
            {location.crags.map((crag) => {
                    return (
                        <div key={crag.id}>
                            <Link to={"/locations/crag/" + crag.id}>{crag.name}</Link>
                        </div>
                    )
                })}
        </>
    );
}

export default LocationBio