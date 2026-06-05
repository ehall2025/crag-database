import { useState } from "react";
import RouteListTable from "./RouteListTable";
import "../styles/UserProfile.css";

function UserProfile({ loggedInUser, setLoggedInUser }) {

    const [user, setUser] = useState(loggedInUser.user);
    const jwt = loggedInUser.jwt;

    const [profileImage, setProfileImage] = useState(
        loggedInUser.user.profileImage || null
    );

    const handleImageUpload = (e) => {
        const file = e.target.files[0];

        if (!file) {
            return;
        }

        const reader = new FileReader();

        reader.onloadend = () => {
            const imageData = reader.result;

            const updatedLoggedInUser = {
                ...loggedInUser,
                user: {
                    ...loggedInUser.user,
                    profileImage: imageData
                }
            };

            localStorage.setItem(
                "loggedInUser",
                JSON.stringify(updatedLoggedInUser)
            );

            setLoggedInUser(updatedLoggedInUser);
            setUser(updatedLoggedInUser.user);
            setProfileImage(imageData);
        };

        reader.readAsDataURL(file);
    };

    return (
        <div className="profile-page">

            <div className="profile-hero">

                <div className="profile-overlay">

                    <div className="profile-info">

                        <div className="profile-avatar">
                            {profileImage ? (
                                <img
                                    src={profileImage}
                                    alt="Profile"
                                    className="profile-avatar-image"
                                />
                            ) : (
                                user.username.charAt(0).toUpperCase()
                            )}
                        </div>

                        <div>
                            <h1 className="profile-name">
                                {user.displayName || user.username}
                            </h1>

                            <p className="profile-subtitle">
                                Tracking sends and projects
                            </p>

                            <label className="upload-avatar-btn">
                                Change Photo
                                <input
                                    type="file"
                                    accept="image/*"
                                    onChange={handleImageUpload}
                                    hidden
                                />
                            </label>
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
    );
}

export default UserProfile;