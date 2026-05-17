import { useState } from "react"
import { createBrowserRouter, Navigate, RouterProvider } from "react-router-dom"

import Home from "./Home"
import Layout from "./Layout"
import ErrorBoundry from "./ErrorBoundry"

import UserLayout from "./profile-components/UserLayout"
import AccountRegistrationForm from "./profile-components/AccountRegistrationForm"
import UserLoginForm from "./profile-components/UserLoginForm"

import LocationLayout from "./location-components/LocationLayout"
import LocationView from "./location-components/LocationView"
import LocationBio from "./location-components/LocationBio"

function AppRouter() {
    const [loggedInUser, setLoggedInUser] = useState(JSON.parse(localStorage.getItem("loggedInUser"))) // TODO set logged in user to an object that has the jwt
    const [locations, setLocations] = useState([])

    const routes = [
        {
            path: "",
            element: <Layout loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} />,
            children: [
                {
                    path: "/",
                    element: <Home />
                },
                {
                    path: "locations",
                    element: <LocationLayout/>,
                    children: [
                        {
                            path: "",
                            element: <LocationView locations={locations} setLocations={setLocations}/>
                        },
                        {
                            path: "{id}",
                            element: <LocationBio locations={locations}/>
                        },
                    ]
                },
                {
                    path: "users",
                    element: <UserLayout />,
                    children: [
                        {
                            path: "register",
                            element: loggedInUser === null ? 
                                <AccountRegistrationForm /> 
                                : 
                                <Navigate to="/" state={{ message: "You you are already logged in" }} />
                        },
                        {
                            path: "login",
                            element: loggedInUser === null ? 
                                <UserLoginForm setLoggedInUser={setLoggedInUser} /> 
                                : 
                                <Navigate to="/" state={{ message: "You you are already logged in" }} />
                        }
                    ]
                },
                {
                    path: "*",
                    element: <ErrorBoundry/>
                }
            ]
        }
    ]

    const router = createBrowserRouter(routes)

    return <RouterProvider router={router} />
}

export default AppRouter