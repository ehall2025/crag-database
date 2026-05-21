import { useState } from "react"
import { createBrowserRouter, Navigate, RouterProvider } from "react-router-dom"

import Home from "./Home"
import Layout from "./Layout"
import ErrorBoundry from "./ErrorBoundry"

import UserLayout from "./profile-components/UserLayout"
import AccountRegistrationForm from "./profile-components/AccountRegistrationForm"
import UserLoginForm from "./profile-components/UserLoginForm"
import UserProfile from "./profile-components/UserProfile"

import LocationLayout from "./location-components/LocationLayout"
import LocationView from "./location-components/LocationView"
import LocationBio from "./location-components/LocationBio"
import CragBio from "./location-components/CragBio"
import AreaBio from "./location-components/AreaBio"
import RouteBio from "./location-components/Routebio"
import PostLocationForm from "./location-components/route-location-form-components/PostLocationForm"

import StagedRoutes from "./profile-components/admin-components/StagedRoutes"


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
                            element: <RouteBio loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}/>
                        },
                        {
                            path: "post",
                            element: loggedInUser !== null ? 
                                <PostLocationForm/> 
                                : 
                                <Navigate to="/" state={{ message: "You are not logged in" }} />
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
                                <Navigate to="/" state={{ message: "You are already logged in" }} />
                        },
                        {
                            path: "login",
                            element: loggedInUser === null ? 
                                <UserLoginForm setLoggedInUser={setLoggedInUser} /> 
                                : 
                                <Navigate to="/" state={{ message: "You are already logged in" }} />
                        },
                        {
                            path: "profile",
                            element: loggedInUser !== null ? 
                                <UserProfile loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} /> 
                                : 
                                <Navigate to="/" state={{ message: "You are not logged in" }} />
                        }
                    ]
                },
                {
                    path: "admin/staged",
                    element: loggedInUser !== null ? 
                                <StagedRoutes loggedInUser={loggedInUser}/>
                                : 
                                <Navigate to="/" state={{ message: "You are not logged in" }} />
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