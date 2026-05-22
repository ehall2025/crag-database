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
				{message && <p>{message}</p>}
				<Outlet />
			</main>
		</div>
    )
}

export default Layout