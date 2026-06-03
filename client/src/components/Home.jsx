import "./Home.css";
import HomeNotLoggedIn from "./HomeNotLoggedIn";

function Home({ loggedInUser }) {

    return (
        <div className="home-page">

            <div className="home-overlay">

                <div className="home-content">

                    <h1 className="home-title">
                        Welcome to CragBase
                    </h1>

                    <p className="home-subtitle">
                        Discover Your Next Climb
                    </p>

                    {loggedInUser !== null ? (
                        <div className="welcome-section">
                            <h2>
                                Welcome back, {loggedInUser.user.username}
                            </h2>
                        </div>
                    ) : (
                        <HomeNotLoggedIn />
                    )}

                </div>

            </div>

        </div>
    );
}

export default Home;