import { useLocation } from 'react-router-dom';
import ResponsiveAppBar from "../components/ResponsiveAppBar";
import axios from 'axios';
import TripCard from '../components/TripCard';
import { useEffect, useState } from 'react';
import { Container } from "@mui/material";

export default function Trips() {
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const origin = queryParams.get('origin') || 'Unknown Origin';
    const destination = queryParams.get('destination') || 'Unknown Destination';

    const [trips, setTrips] = useState([]);
    useEffect(() => {
        axios.get(`http://localhost:8080/api/v1/trip/search?origin=${origin}&destination=${destination}`)
            .then((response) => {
                console.log("Trips: ", response.data);
                setTrips(response.data);
            })
            .catch((error) => {
                console.error("Error fetching trips: ", error);
            });
    }
    , [origin, destination]);


    return (
        <>
            <ResponsiveAppBar />
            <div style={{marginTop: "100px", display: "flex", alignItems: "center", justifyContent: "center"}}>
                <h1>{origin} to {destination}</h1>
            </div>
            <Container style={{display: "flex", justifyContent: "center", flexWrap: "wrap"}}>
                {trips.map((trip) => (
                    <TripCard
                        origin={trip.origin}
                        destination={trip.destination}
                        time={trip.time}
                        code={trip.tripCode}
                        price={trip.price}
                    />
                ))}
            </Container>
        </>
    )
}