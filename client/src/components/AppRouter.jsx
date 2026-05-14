import { useState } from "react"
import { createBrowserRouter, Navigate, RouterProvider } from "react-router-dom"

import Home from "./Home"
import Layout from "./Layout"
import ErrorBoundry from "./ErrorBoundry"

import UserLayout from "./user-components/UserLayout"
import UserForm from "./profile-components/CreateUserForm"
import UserLoginForm from "./user-components/UserLoginForm"

import LocationView from "./location-components/LocationView"

function AppRouter() {
    const [loggedInUser, setLoggedInUser] = useState(JSON.parse(localStorage.getItem("loggedInUser")))

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
                    path: "/locations",
                    element: <LocationView/>
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