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
    }, [id])

    return (
        <>
            <div className="card">

                <div className="card-header">
                    <h2>{area.name}</h2>
                </div>
                
                <div className="card-body">
                    <h5>Description</h5>
                    <p>{area.description}</p>
                    {area.subareas !== null && area.subareas.length !== 0 && 
                        <div>
                            <h5>Subareas</h5>
                            <div className="card col-2">
                                <ul className="list-group">
                                    {area.subareas.map((subarea) => (
                                        <li key={subarea.id} className="list-group-item">
                                            <Link to={"/locations/area/" + subarea.id}>{subarea.name}</Link>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </div>
                    }
                    {area.routes !== null && area.routes.length !== 0 && 
                        <div>
                            <h5>Routes</h5>
                            <div className="card col-2">
                                <ul className="list-group">
                                    {area.routes.map((routes) => (
                                        <li key={routes.id} className="list-group-item">
                                            <Link to={"/locations/route/" + routes.id}>{routes.name}</Link>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </div>
                    }
                </div>
                
            </div>
        </>
    );
}

export default AreaBio