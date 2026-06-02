import "../location-style/LocationPage.css";

function LocationPage({ children }) {
    return (
        <div className="location-page">
            <div className="location-card">
                {children}
            </div>
        </div>
    );
}

export default LocationPage;