import { Link } from "react-router-dom"

function HomeNotLoggedIn () {
    return (
        <>
        <div className="container inline-flex">
            <div className="row">
                <div className="col-1">
                    <h2><Link className="btn btn-primary">Log in</Link></h2>
                </div>
                <div className="col-1">
                    <p>or</p>
                </div>
                <div className="col-1">
                    <h2><Link className="btn btn-primary">Sign up</Link></h2>
                </div>
            </div>
        </div>
        </>
    )
}

export default HomeNotLoggedIn