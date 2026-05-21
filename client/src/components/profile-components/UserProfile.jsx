import { useState } from "react";
import RouteListTable from "./RouteListTable";


function UserProfile ({ loggedInUser , setLoggedInUser }) {
    const [user, setUser] = useState(loggedInUser.user)
    const jwt = loggedInUser.jwt

    console.log(user)

    return (
        <>
            <h2>{user.displayName || user.username}</h2>
            <div>
                <h5>ticks</h5>
                <RouteListTable routeList={user.tickList} loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}/>
            </div>
            <div>
                <h5>to-dos</h5>
                <RouteListTable routeList={user.todoList} loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}/>
            </div>
        </>
    );
}

export default UserProfile