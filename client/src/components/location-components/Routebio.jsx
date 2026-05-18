import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";


function RouteBio() {

    const { id } = useParams();
    const [route, setRoute] = useState({
        id:0,
        name: "",
        areaId: 0,
        description: "",
        startPosition: ""
    })

    useEffect(() => {
        fetch("http://localhost:8080/api/locations/route/" + id)
        .then(response => response.json())
        .then(payload => setRoute(payload))
    }, [])

    return (
        <>
            <h2>{route.name}</h2>
            <h5>Description</h5>
            <p>{route.description}</p>
            <h5>Start Position</h5>
            <p>{route.startPosition}</p>
        </>
    );
}

export default RouteBio