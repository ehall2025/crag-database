import { useState } from "react";
import RouteListTable from "./RouteListTable";
import "./UserProfile.css";

function UserProfile({ loggedInUser, setLoggedInUser }) {
    const [user, setUser] = useState(loggedInUser.user)
    const jwt = loggedInUser.jwt

    return (
        <div className="profile-page">

            <div className="profile-header">
                <h1>Profile</h1>
                <p>Your saved climbs and climbing history.</p>
            </div>

            <div className="profile-card">

                <div className="profile-user-header">
                    <h2>{user.displayName || user.username}</h2>
                </div>

                <div className="profile-lists">

                    <div className="profile-list-card">
                        <div className="profile-list-header">
                            <h3>Ticks</h3>
                        </div>

                        <RouteListTable
                            routeList={user.tickList}
                            loggedInUser={loggedInUser}
                            setLoggedInUser={setLoggedInUser}
                        />
                    </div>

                    <div className="profile-list-card">
                        <div className="profile-list-header">
                            <h3>To-dos</h3>
                        </div>

                        <RouteListTable
                            routeList={user.todoList}
                            loggedInUser={loggedInUser}
                            setLoggedInUser={setLoggedInUser}
                        />
                    </div>

                </div>

            </div>

        </div>
    );
}

export default UserProfile