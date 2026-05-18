import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";


function AreaBio() {

    const { id } = useParams();
    const [area, setArea] = useState({
        id:0,
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
    }, [])

    return (
        <>
            <h2>{area.name}</h2>
            <p>{area.description}</p>
            {area.subareas.length !== 0 && area.subareas.map((subarea) => {
                return (
                    <div key={subarea.id}>
                        <Link to={"/locations/area/" + subarea.id}>{subarea.name}</Link>
                    </div>
                )
            })}
            {area.routes.length !== 0 && area.routes.map((routes) => {
                return (
                    <div key={routes.id}>
                        <Link to={"/locations/route/" + routes.id}>{routes.name}</Link>
                    </div>
                )
            })}
        </>
    );
}

export default AreaBio