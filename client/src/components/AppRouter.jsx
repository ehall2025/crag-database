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
import CragBio from "./location-components/CragBio"
import AreaBio from "./location-components/AreaBio"
import RouteBio from "./location-components/Routebio"


function AppRouter() {
    const [loggedInUser, setLoggedInUser] = useState(null)
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
                            path: ":id",
                            element: <LocationBio/>
                        },
                        {
                            path: "crag/:id",
                            element: <CragBio/>
                        },
                        {
                            path: "area/:id",
                            element: <AreaBio/>
                        },
                        {
                            path: "route/:id",
                            element: <RouteBio/>
                        }
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