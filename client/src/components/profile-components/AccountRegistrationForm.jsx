

function AccountRegistrationForm () {



    function handleSubmit () {
        event.preventDefault()
        console.log("handleSubmit called")

        const email = document.getElementById("email-form").innerText
        const password = document.getElementById("password-form").innerText
    }

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Email: </label>
                <input id="email-form" type="text" placeholder="Enter your email here"></input>
            </div>
            <div>
                <label>Password: </label>
                <input id="password-form" type="password" placeholder="Enter your password here"></input>
            </div>
            <button type="submit" onClick={handleSubmit}>Create Account</button>
        </form>
    );
}

export default AccountRegistrationForm