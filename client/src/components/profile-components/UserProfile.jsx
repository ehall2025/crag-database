import { useState } from "react";
import RouteListTable from "./RouteListTable";


function UserProfile ({ loggedInUser , setLoggedInUser }) {
    const [user, setUser] = useState(loggedInUser.user)
    const jwt = loggedInUser.jwt

    return (
        <>
            <h1>Profile</h1>
            <div className="card">
                <div className="card-header">
                    <h2>{user.displayName || user.username}</h2>
                </div>

                <div className="card-body d-inline-flex gap-5">
                    <div className="card">
                        <div className="card-header">
                            <h5>Ticks</h5>
                        </div>
                        <div className="card-body">
                            <RouteListTable routeList={user.tickList} loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}/>
                        </div>
                    </div>
                    <div className="card">
                        <div className="card-header">
                            <h5>To-dos</h5>
                        </div>
                        <div className="card-body">
                            <RouteListTable routeList={user.todoList} loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}/>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default UserProfile