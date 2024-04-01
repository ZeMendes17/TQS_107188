import ResponsiveAppBar from "../components/ResponsiveAppBar";
import { useLocation } from 'react-router-dom';
import { Container } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import UserForm from "../components/UserForm";
import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";

export default function Pay() {
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const code = queryParams.get('code') || 'Unknown Code';
    // add code to local storage
    localStorage.setItem("code", code);

    const [time, setTime] = useState("");
    const [trip, setTrip] = useState({});
    const [price, setPrice] = useState(0);
    useEffect(() => {
        axios.get(`http://localhost:8080/api/v1/trip/search/code?code=${code}`)
            .then((response) => {
                console.log("Trip: ", response.data);
                setTrip(response.data);
                setTime(response.data.time.split(":").slice(0, 2).join(":"));
                setPrice(response.data.price);
            })
            .catch((error) => {
                console.error("Error fetching trip: ", error);
            });
    }, [code]);

    const [coins, setCoins] = useState([]);
    const [currency, setCurrency] = useState("EUR");

    useEffect(() => {
        axios.get(`http://localhost:8080/api/v1/exchange-rate/coins`)
            .then((response) => {
                console.log("Coins: ", response.data);
                setCoins(response.data);
            })
            .catch((error) => {
                console.error("Error fetching coins: ", error);
            });
    }, []);

    

    const chooseCurrency = (coin: string) => {
        // change the price
        alert(`You have chosen ${coin}`);
    }

    return (
        <>
            <ResponsiveAppBar />
            <Container>
            <div style={{marginTop: "100px", display: "flex", alignItems: "center", justifyContent: "center"}}>
                <h1>Trip Code: {code}</h1>
            </div>
            {/* Display Trip Info */}
            <div>
                
                <div>
                    <h2>From {trip.origin} to {trip.destination}</h2>
                </div>
                <div>
                    <h3>Time: {time}</h3>
                </div>
                <div style={{ display: "flex", alignItems: "center" }}>
                    <div style={{ marginRight: "10px" }}>
                        <h3>Price: {price}</h3>
                    </div>
                    <div>
                        <FormControl
                            sx={{ minWidth: 120 }}
                            style={{ margin: "10px" }}
                        >
                            <InputLabel id="demo-simple-select-label">Choose Currency</InputLabel>
                            <Select
                                labelId="demo-simple-select-label"
                                id="demo-simple-select"
                                label="Currency"
                                defaultValue={"EUR"}
                            >
                                {coins.map((coin) => (
                                    <MenuItem value={coin} onClick={() => chooseCurrency(coin)}>
                                        {coin}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                    </div>
                </div>

            </div>

            {/* User Info */}
            <div style={{marginTop: "20px", display: "flex", alignItems: "center", justifyContent: "center"}}>
                <h2>Enter your information</h2>
            </div>
            <div style={{marginTop: "10px", display: "flex", alignItems: "center", justifyContent: "center"}}>
                <UserForm code={code} />
            </div>
            </Container>
        </>
    )
}