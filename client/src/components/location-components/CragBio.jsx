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
            <h2>{crag.name}</h2>
            <p>{crag.description}</p>
            {crag.areas.map((area) => {
                    return (
                        <div key={area.id}>
                            <Link to={"/locations/area/" + area.id}>{area.name}</Link>
                        </div>
                    )
                })}
        </>
    );
}

export default CragBio