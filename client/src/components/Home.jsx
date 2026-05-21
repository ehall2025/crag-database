function Home({ loggedInUser }) {
    return (
        <>
            <div>Welcome to Cragbase</div>
            { loggedInUser !== null ? <h2>Welcome, {loggedInUser.user.username}!</h2> : null }
        </>
    )
}

export default Home