import { useParams } from "react-router-dom";


function LocationBio({ locations }) {

    const { id } = useParams();

    const location = () => {
        locations.forEach(element => {
            if (element.id === id) return element;
        });
    }

    return (
        <>
            <h2>{location.region}</h2>
            <p>{location.description}</p>
        </>
    );
}

export default LocationBio