import { useState } from "react";
import RouteListTable from "./RouteListTable";
import "../styles/UserProfile.css";


function UserProfile ({ loggedInUser , setLoggedInUser }) {
    const [user, setUser] = useState(loggedInUser.user)
    const jwt = loggedInUser.jwt

    return (
    <div className="profile-page">

        <div className="profile-hero">

            <div className="profile-overlay">

                <div className="profile-info">

                    <div className="profile-avatar">
                        {user.username.charAt(0).toUpperCase()}
                    </div>

                    <div>
                        <h1 className="profile-name">
                            {user.displayName || user.username}
                        </h1>

                        <p className="profile-subtitle">
                            Tracking sends and projects
                        </p>
                    </div>

                </div>
            </div>
        </div>

        <div className="profile-content">

            <div className="route-section">

                <h2>Ticks</h2>

                <RouteListTable
                    routeList={user.tickList}
                    loggedInUser={loggedInUser}
                    setLoggedInUser={setLoggedInUser}
                />

            </div>

            <div className="route-section">

                <h2>Projects</h2>

                <RouteListTable
                    routeList={user.todoList}
                    loggedInUser={loggedInUser}
                    setLoggedInUser={setLoggedInUser}
                />

            </div>

        </div>
    </div>
)
}

export default UserProfile