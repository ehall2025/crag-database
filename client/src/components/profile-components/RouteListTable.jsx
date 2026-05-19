import { Link } from "react-router-dom";



function RouteListTable ({ routeList , setLoggedInUser }) {


    function handleDelete () {
        //send delete to 
        
    }

    return (
        <>
            {routeList.routes.map(route => {
                return (
                    <div key={route.id}>
                        <Link to={"/locations/route/" + route.id}>{route.name}</Link>
                        <button onClick={handleDelete}>Delete</button>
                    </div>
                )
            })}
        </>
    );
}

export default RouteListTable