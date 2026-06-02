import { Link } from "react-router-dom";

function HomeNotLoggedIn() {

    return (
        <div className="home-buttons">

            <Link
                className="hero-button primary"
                to="/users/login"
            >
                Log In
            </Link>

            <Link
                className="hero-button secondary"
                to="/users/register"
            >
                Sign Up
            </Link>

        </div>
    );
}

export default HomeNotLoggedIn;