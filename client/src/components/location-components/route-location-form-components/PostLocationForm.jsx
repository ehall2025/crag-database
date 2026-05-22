import { useState } from "react";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";


function PostLocationForm ({ loggedInUser }) {

    const navigate = useNavigate();
    const [errors, setErrors] = useState([])
    const [areaForms, setAreaForms] = useState([{areaId: 0, subAreas: []}])
    const [locations, setLocations] = useState([])
    const [route, setRoute] = useState({
        "id":0,
        "name": "",
        "areaId": 0,
        "description": "",
        "startPosition": ""
    })

    function handleRouteChange (event) {
        setRoute({ ...route, [event.target.name]: event.target.value })
    }
    
    useEffect(() => {
        fetch("http://localhost:8080/api/locations")
        .then(response => response.json())
        .then(payload => setLocations(payload))
    }, [])

    async function handleLocationChange (event) {
        fetch("http://localhost:8080/api/locations/" + event.target.value)
        .then(response => response.json())
        .then(payload =>  setAreaForms([{
            areaId: event.target.value,
            subAreas: payload.crags
        }]))
    }

    async function handleAreaSelectChange (event) {

         //take areaId of selected area and fetch subAreas
        let fetchUrl = "http://localhost:8080/api/locations/"

        if (areaForms.length == 1) {
            fetchUrl += "crag/"
            fetch(fetchUrl + event.target.value)
            .then(response => response.json())
            .then(payload => {
                setAreaForms([...areaForms, {
                    areaId: event.target.value,
                    subAreas: payload.areas
                }])
            })
        } else {
            fetchUrl += "area/"
            fetch(fetchUrl + event.target.value)
            .then(response => response.json())
            .then(payload => {
                if (payload.subareas.length !== null && payload.subareas.length > 0) {
                    setAreaForms([...areaForms, {
                        areaId: event.target.value,
                        subAreas: payload.subareas
                    }])
                }
            })
        }

        setRoute({ ...route, ["areaId"]: parseInt(areaForms.at(-1).areaId) })
    }

    async function handleSubmit (event) {
        event.preventDefault()

        setRoute({ ...route, ["areaId"]: parseInt(areaForms.at(-1).areaId) })
        console.log(route)

        const response = await fetch("http://localhost:8080/api/post_route", {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + loggedInUser.jwt,
                "Content-Type": "application/json",
            },
            body: JSON.stringify(route)
        })

        if (response.status >= 200 && response.status < 300) {
            navigate("/")
        } else {
            const payload = await response.json()
            setErrors(payload)
        }
    }

    return (
        <>
            <form>
                {errors.length > 0 ?
                    <ul>{errors.map(error => <li key={error}>{error}</li>)}</ul>
                    : null
                }
                <div>
                    <label>Route Name</label>
                    <input name="name" id="name" type="text" onChange={handleRouteChange}/>
                </div>
                <div>
                    <label>Select Location</label>
                    <select id="locationId" onChange={handleLocationChange}>
                        <option>select location</option>
                        {locations.map((location) => (
                            <option key={location.id} value={location.id}>{location.region}</option>
                        ))}
                    </select>
                </div>
                {areaForms.map(({areaId, subAreas}, index) => (
                    <div key={index}>
                        <label>Select Area</label>
                        <select id="areaId" onChange={handleAreaSelectChange}>
                            <option>Select Area</option>
                            {subAreas.map((subArea) => (
                                <option key={subArea.id} value={subArea.id}>{subArea.name}</option>
                            ))}
                        </select>
                    </div>
                ))}
                <div>
                    <label>Route description</label>
                    <textarea name="description" id="description" onChange={handleRouteChange}/>
                </div>
                <div>
                    <label>Route start postition</label>
                    <textarea name="startPosition" id="startPosition" onChange={handleRouteChange}/>
                </div>
                <button type="submit" onClick={handleSubmit}>Submit</button>
            </form>
        </>
    );
}

export default PostLocationForm