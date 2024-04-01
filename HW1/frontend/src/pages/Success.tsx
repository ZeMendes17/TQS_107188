import axios from "axios";
import { useState, useEffect } from "react";
import ResponsiveAppBar from "../components/ResponsiveAppBar";
import { Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function Success() {
    // get reservationToken from local storage
    const reservationToken = localStorage.getItem("reservationToken");
    const name = localStorage.getItem("name");
    const [reservation, setReservation] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        // get the reservation from the backend
        axios.get("http://localhost:8080/api/v1/reservation?token=" + reservationToken)
        .then((response) => {
            console.log("Reservation found: ", response.data);
            setReservation(response.data);
        })
        .catch((error) => {
            console.error("Error finding reservation: ", error);
        });
    }, []);

    const navToReservations = () => {
        navigate("/reservations");
    }

    return (
        <>
            <ResponsiveAppBar />

            <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', gap: '5px', marginTop: '100px' }}>
                <h1>Payment Successful!</h1>
                <p>{name}, your trip token is: <b>{reservationToken}</b>.
                You can check your reservations by clicking the button below</p>

                <Button variant="contained" sx={{backgroundColor: '#a2e53f', color: 'black', marginTop: "20px"}} onClick={navToReservations}>
                    Check Reservations
                </Button>
            </div>
        </>
    )
}