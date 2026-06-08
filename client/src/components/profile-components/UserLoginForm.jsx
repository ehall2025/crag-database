import { useState } from "react"
import { useNavigate } from "react-router-dom"
import "../styles/Login.css";


function UserLoginForm({ setLoggedInUser }) {

    const navigate = useNavigate()
    const [errors, setErrors] = useState([])
    const [credentials, setCredentials] = useState({
        username: "",
        password: ""
    })
    
    function resetForm () {
        document.getElementById("loginEmailForm").value = ""
        document.getElementById("loginPasswordForm").value = ""
    }

    function handleChange (event) {
        setCredentials({ ...credentials, [event.target.name]: event.target.value })
    }

    async function handleSubmit (event) {
        event.preventDefault()

        const response = await fetch("http://localhost:8080/api/users/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(credentials)
        })

        if (response.status >= 200 && response.status < 300) {

            const loginData = await response.json();

            const existingLoggedInUser =
                JSON.parse(localStorage.getItem("loggedInUser"));

            const updatedLoggedInUser = {
                ...loginData,
                user: {
                    ...loginData.user,
                    profileImage:
                        existingLoggedInUser?.user?.profileImage || null
                }
            };

            localStorage.setItem(
                "loggedInUser",
                JSON.stringify(updatedLoggedInUser)
            );

            setLoggedInUser(updatedLoggedInUser);

            resetForm();
            navigate("/");

        } else {
            const payload = await response.json();
            setErrors(payload);
        }
    }

    return (
        
            <div className="login-page">
                <div className="overlay">

                    <div className="login-card">

                        <h1 className="login-title">Log In</h1>

                        <p className="login-subtitle">
                        Your climbing journal for every send.
                        </p>

                        <form onSubmit={handleSubmit}>

                            <div className="form-group">
                                <input id="loginEmailForm" name="username" type="text" placeholder="Email Address" onChange={handleChange}></input>
                            </div>

                            <div className="form-group">
                                <input id="loginPasswordForm" name="password" type="password" placeholder="Password" onChange={handleChange}></input>
                            </div>

                            <button className="login-btn" type="submit">Login</button>
                        </form>
                    </div>
                </div>
            </div>
    );
}

export default UserLoginForm