import { useState } from "react";
import UserStats from "./statistics-components/UserStats";
import RouteListTable from "./RouteListTable";


function UserProfile ({ loggedInUser , setLoggedInUser }) {
    const [user, setUser] = useState(loggedInUser.user)
    const jwt = loggedInUser.jwt

    console.log(user)

    return (
        <>
            <h2>{user.displayName || user.username}</h2>
            <div>
                <UserStats/>
            </div>
            <div>
                <h5>ticks</h5>
                <RouteListTable routeList={user.tickList} setLoggedInUser={setLoggedInUser}/>
            </div>
            <div>
                <h5>to-dos</h5>
                <RouteListTable routeList={user.todoList} setLoggedInUser={setLoggedInUser}/>
            </div>
        </>
    );
}

export default UserProfile