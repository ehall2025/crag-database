import { Link } from "react-router-dom";
import "../location-style/LocationGrid.css";

function LocationGrid({ items, getPath, getLabel, variant = "" }) {
    return (
        <div className="location-grid">
            {items.map((item) => (
                <Link
                    key={item.id}
                    className={`location-grid-card ${variant}`}
                    to={getPath(item)}
                >
                    <h5>{getLabel ? getLabel(item) : item.name}</h5>
                </Link>
            ))}
        </div>
    );
}

export default LocationGrid;