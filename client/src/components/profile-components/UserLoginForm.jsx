


function UserLoginForm() {

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Email: </label>
                <input id="loginEmailForm" type="text" placeholder="Enter your email here"></input>
            </div>
            <div>
                <label>Password: </label>
                <input id="loginPasswordForm" type="password" placeholder="Enter your password here"></input>
            </div>
            <button type="submit" onClick={handleSubmit}>Login</button>
        </form>
    );
}

export default UserLoginForm