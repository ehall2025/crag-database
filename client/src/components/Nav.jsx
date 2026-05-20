import { Link, NavLink } from 'react-router-dom';

function Nav({ loggedInUser, setLoggedInUser }) {
    return (
        <nav className='navbar navbar-expand'>
            <div className='d-flex'>
                <ul className='navbar-nav'>
                    <li className='nav-item'>
                        <NavLink id="link" className='nav-link ' to='/'>
                            Home
                        </NavLink>
                    </li>
                    <li className='nav-item'>
                        <NavLink id="link" className='nav-link' to='/locations'>
                            Locations
                        </NavLink>
                    </li>

                    { loggedInUser === null ?
                        <>
                            {/* only available if logged out */}
                            <li className='nav-item'>
                                <NavLink id="link" className='nav-link' to='/users/register'>
                                    Create an account
                                </NavLink>
                            </li>
                            <li className='nav-item'>
                                <NavLink id="link" className='nav-link' to='/users/login'>
                                    Login
                                </NavLink>
                            </li>
                        </>
                        :
                        <>
                            {/* only available if logged in */}
                            <li className='nav-item'>
                                <NavLink id="link" className='nav-link' to='locations/post'>
                                    Post Route
                                </NavLink>
                            </li>
                            <li>
                                <NavLink id="link" className='nav-link' to='/users/profile'>
                                    Profile
                                </NavLink>
                            </li>
                            <li className='nav-item'>
                                <button id="link" className='nav-link' onClick={() => {
                                    setLoggedInUser(null)
                                }}>
                                    Logout
                                </button>
                            </li>
                        </>
                    }
                </ul>
            </div>
        </nav>
    )
}

export default Nav