import { useState } from "react"
import { useNavigate } from "react-router-dom"


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

    async function handleSubmit () {
        event.preventDefault()

        const response = await fetch("http://localhost:8080/api/users/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(credentials)
        })

        if (response.status >= 200 && response.status < 300) {
            setLoggedInUser(await response.json())
            resetForm()
            navigate("/")
        } else {
            const payload = await response.json()
            setErrors(payload)
        }
    }

    return (
        <>
        <h1>Log In</h1>
         <form onSubmit={handleSubmit}>
            <div>
                <input id="loginEmailForm" name="username" type="text" placeholder="Email Address" onChange={handleChange}></input>
            </div>
            <div>
                <input id="loginPasswordForm" name="password" type="password" placeholder="Password" onChange={handleChange}></input>
            </div>
            <button className="btn btn-primary" type="submit" onClick={handleSubmit}>Login</button>
        </form>
         </>
    );
}

export default UserLoginForm