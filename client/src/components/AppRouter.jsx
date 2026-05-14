import { useState } from "react"
import { createBrowserRouter, Navigate, RouterProvider } from "react-router-dom"

import Home from "./Home"
import Layout from "./Layout"
import ErrorBoundry from "./ErrorBoundry"

import UserLayout from "./user-components/UserLayout"
import UserForm from "./profile-components/CreateUserForm"
import UserLoginForm from "./user-components/UserLoginForm"

import LocationLayout from "./location-components/LocationLayout"
import LocationView from "./location-components/LocationView"
import LocationBio from "./location-components/LocationBio"

function AppRouter() {
    const [loggedInUser, setLoggedInUser] = useState(JSON.parse(localStorage.getItem("loggedInUser")))
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
                            path: "add",
                            element: loggedInUser === null ? 
                                <UserForm /> 
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