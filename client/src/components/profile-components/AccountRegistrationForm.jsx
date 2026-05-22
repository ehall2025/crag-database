import { useState } from "react"
import { useNavigate } from "react-router-dom"


function AccountRegistrationForm () {

    const navigate = useNavigate()
    const [errors, setErrors] = useState([])
    const [user, setUser] = useState({
        id:0,
        username: "",
        password: "",
        role: "ROLE_USER"
    })
    
    function resetForm () {
        document.getElementById("registerEmailForm").value = ""
        document.getElementById("registerPasswordForm").value = ""
    }

    function handleChange (event) {
        setUser({ ...user, [event.target.name]: event.target.value })
    }

    async function handleSubmit () {
        event.preventDefault()

        const response = await fetch("http://localhost:8080/api/users/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user)
        })

        if (response.status >= 200 && response.status < 300) {
            resetForm()
            navigate("/users/login")
        } else {
            const payload = await response.json()
            setErrors(payload)
        }
    }

    return (
        <>
            <h1>Create an Account</h1>
            <form onSubmit={handleSubmit}>

                {errors.length > 0 && <ul>
                    {errors.map(error => <li key={error}>{error}</li>)}    
                </ul>}

                <div>
                    <input id="registerEmailForm" name="username" type="text" placeholder="Email Address" onChange={handleChange}></input>
                </div>
                <div>
                    <input id="registerPasswordForm" name="password" type="password" placeholder="Password" onChange={handleChange}></input>
                </div>
                <button className="btn btn-primary" type="submit" onClick={handleSubmit}>Create Account</button>
            </form>
        </>
    );
}

export default AccountRegistrationForm