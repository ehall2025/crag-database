import { Link } from "react-router-dom"
import HomeNotLoggedIn from "./HomeNotLoggedIn"

function Home({ loggedInUser }) {
    return (
        <>
            <h1>Welcome to Cragbase</h1>
            { loggedInUser !== null ? <h2>Welcome, {loggedInUser.user.username}!</h2> : <HomeNotLoggedIn/> }
        </>
    )
}

export default Home