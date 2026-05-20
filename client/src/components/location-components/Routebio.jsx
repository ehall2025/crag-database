import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";


function RouteBio({ loggedInUser , setLoggedInUser }) {

    const { id } = useParams();
    const [route, setRoute] = useState({
        id:0,
        name: "",
        areaId: 0,
        description: "",
        startPosition: ""
    })

    async function handleAdd (event) {

        const response = await fetch("http://localhost:8080/api/profile", {
            "method": "POST",
            "headers": {
                "Authorization": "Bearer " + loggedInUser.jwt,
                "Content-Type": "application/json" ,
            },
            "body" : JSON.stringify({
                "listId": loggedInUser.user.tickList.id,
                "routeId": id
            })
        })

        if (response.status >= 200 && response.status < 300) {
            const payload = await response.json()
            updateUser(loggedInUser, payload)
        } else {
            const payload = await response.json()
            setErrors(payload)
        }
    }

    function updateUser (oldUser , newRouteList) {
        let newUser = {...oldUser}

        newUser.user.tickList.routes = newRouteList

        setLoggedInUser(newUser)
    }

    useEffect(() => {
        fetch("http://localhost:8080/api/locations/route/" + id)
        .then(response => response.json())
        .then(payload => setRoute(payload))
    }, [])

    return (
        <>
            <h2>{route.name}</h2>
            <button onClick={handleAdd}>Log Ascent</button>
            <h5>Description</h5>
            <p>{route.description}</p>
            <h5>Start Position</h5>
            <p>{route.startPosition}</p>
        </>
    );
}

export default RouteBio