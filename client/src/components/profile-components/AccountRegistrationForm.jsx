import { useState } from "react"
import { useNavigate } from "react-router-dom"


function AccountRegistrationForm () {

    const navigate = useNavigate()
    const [errors, setErrors] = useState([])
    const [user, setUser] = useState({
        id:0,
        username: "",
        password: "",
        role: "USER"
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
            navigate("/user/login")
        } else {
            const payload = await response.json()
            setErrors(payload)
        }
    }

    return (
        <form onSubmit={handleSubmit}>

            {errors.length > 0 && <ul>
                {errors.map(error => <li key={error}>{error}</li>)}    
            </ul>}

            <div>
                <label>Email: </label>
                <input id="registerEmailForm" name="username" type="text" placeholder="Enter your email here" onChange={handleChange}></input>
            </div>
            <div>
                <label>Password: </label>
                <input id="registerPasswordForm" name="password" type="password" placeholder="Enter your password here" onChange={handleChange}></input>
            </div>
            <button type="submit" onClick={handleSubmit}>Create Account</button>
        </form>
    );
}

export default AccountRegistrationForm