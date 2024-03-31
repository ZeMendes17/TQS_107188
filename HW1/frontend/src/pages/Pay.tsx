import ResponsiveAppBar from "../components/ResponsiveAppBar";
import { useLocation } from 'react-router-dom';
import { Container } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import UserForm from "../components/UserForm";

export default function Pay() {
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const code = queryParams.get('code') || 'Unknown Code';
    // add code to local storage
    localStorage.setItem("code", code);

    const [time, setTime] = useState("");
    const [trip, setTrip] = useState({});
    useEffect(() => {
        axios.get(`http://localhost:8080/api/v1/trip/search/code?code=${code}`)
            .then((response) => {
                console.log("Trip: ", response.data);
                setTrip(response.data);
                setTime(response.data.time.split(":").slice(0, 2).join(":"));
            })
            .catch((error) => {
                console.error("Error fetching trip: ", error);
            });
    }, [code]);

    return (
        <>
            <ResponsiveAppBar />
            <Container>
            <div style={{marginTop: "100px", display: "flex", alignItems: "center", justifyContent: "center"}}>
                <h1>Trip Code: {code}</h1>
            </div>
            {/* Display Trip Info */}
            <div>
                <h2>From {trip.origin} to {trip.destination}</h2>
            </div>
            <div>
                <h3>Time: {time}</h3>
            </div>
            <div>
                <h3>Price: {trip.price} €</h3>
            </div>

            {/* User Info */}
            <div style={{marginTop: "20px", display: "flex", alignItems: "center", justifyContent: "center"}}>
                <h2>Enter your information</h2>
            </div>
            <div style={{marginTop: "10px", display: "flex", alignItems: "center", justifyContent: "center"}}>
                <UserForm />
            </div>
            </Container>
        </>
    )
}