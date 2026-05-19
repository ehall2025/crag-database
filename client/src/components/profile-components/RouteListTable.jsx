import { Link } from "react-router-dom";



function RouteListTable ({ routeList , loggedInUser , setLoggedInUser }) {


    async function handleDelete () {
        //send delete to 
        const response = await fetch("http://localhost:8080/api/profile", {
            "method": "DELETE",
            "headers": {
                "Authorization": "Bearer " + loggedInUser.jwt,
                "Content-Type": "application/json" ,
            },
            "body" : JSON.stringify({
                "listId": 1,
                "routeId": 1
            })
        })

        const payload = await response.json;
    }

    return (
        <>
            {routeList.routes.map(route => {
                return (
                    <div key={route.id}>
                        <Link to={"/locations/route/" + route.id}>{route.name}</Link>
                        <button onClick={handleDelete}>remove from list</button>
                    </div>
                )
            })}
        </>
    );
}

export default RouteListTable