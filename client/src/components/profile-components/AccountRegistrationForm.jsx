import { useState } from "react"
import { useNavigate } from "react-router-dom"
import "../styles/UserRegister.css";


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

    async function handleSubmit (event) {
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
        <div className="register-page">

            <div className="overlay">
                
                <div className="register-card">
                    
                    <h1 className="register-title">Create an Account</h1>

                    <p className="register-subtitle">Start logging your climbs and projects.</p>
                    
                    {errors.length > 0 && (
                        <ul className="error-list">
                            {errors.map(error => (
                                <li key={error}>{error}</li>
                            ))}    
                        </ul>
                    )}

                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <input id="registerEmailForm" name="username" type="text" placeholder="Email Address" onChange={handleChange} />
                        </div>

                        <div className="form-group">
                            <input id="registerPasswordForm" name="password" type="password" placeholder="Password" onChange={handleChange} />
                        </div>

                        <button className="register-btn" type="submit">Create Account</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default AccountRegistrationForm