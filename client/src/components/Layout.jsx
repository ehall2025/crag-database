import { Link, Outlet, useLocation } from "react-router-dom"

import Nav from "./Nav"

function Layout({ loggedInUser, setLoggedInUser }) {
	const location = useLocation()

	const message = location.state && location.state.message

    return (
        <div className='container'>
			<header className='mb-3'>
				<Nav loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} />
			</header>
			<main>
				<h1 className='mb-3'>Cragbase</h1>
				{message && <p>{message}</p>}
				{ loggedInUser !== null ? <h2>Welcome, {loggedInUser.email}!</h2> : null }
				<Outlet />
			</main>
		</div>
    )
}

export default Layout