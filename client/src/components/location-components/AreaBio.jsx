import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import "./AreaBio.css";

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
        <div className="area-bio-page">

            <div className="area-bio-card">

                <div className="area-title">
                    <h1>{area.name}</h1>
                </div>

                <div>
                    <h4 className="section-title">
                        Description
                    </h4>

                    <p className="area-description">
                        {area.description}
                    </p>
                </div>

                {area.subareas !== null &&
                    area.subareas.length !== 0 && (

                        <div className="content-section">

                            <h4 className="section-title">
                                Subareas
                            </h4>

                            <div className="item-grid">
                                {area.subareas.map((subarea) => (
                                    <Link
                                        key={subarea.id}
                                        className="explore-card"
                                        to={"/locations/area/" + subarea.id}
                                    >
                                        <h5>{subarea.name}</h5>
                                    </Link>
                                ))}
                            </div>

                        </div>
                    )}

                {area.routes !== null &&
                    area.routes.length !== 0 && (

                        <div className="content-section">

                            <h4 className="section-title">
                                Routes
                            </h4>

                            <div className="item-grid">
                                {area.routes.map((route) => (
                                    <Link
                                        key={route.id}
                                        className="explore-card route-card"
                                        to={"/locations/route/" + route.id}
                                    >
                                        <h5>{route.name}</h5>
                                    </Link>
                                ))}
                            </div>

                        </div>
                    )}

            </div>

        </div>
    );
}

export default AreaBio