import { Container } from "@mui/material";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import { useEffect, useState } from "react";
import axios from "axios";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import Button from "@mui/material/Button";
import dayjs from "dayjs";
import { useNavigate } from 'react-router-dom';


export default function Finder() {
    // get the cities from the backend
    const [cities, setCities] = useState([]);
    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/city")
            .then((response) => {
                console.log("Cities: ", response.data);
                setCities(response.data);
            })
            .catch((error) => {
                console.error("Error fetching cities: ", error);
            });
    }, []);

    const navigate = useNavigate();
    const [origin, setOrigin] = useState("");
    const [destination, setDestination] = useState("");

    // todays date
    const today = new Date().toISOString().split('T')[0];

    // search trips
    const searchTrips = () => {
        console.log("Searching trips from ", origin, " to ", destination);
        navigate(`/trips?origin=${origin}&destination=${destination}`);
    }

    return (
        <Container
            sx={{ display: "flex", justifyContent: "center", marginTop: "20px"}}
        >
            <FormControl
                sx={{ minWidth: 120 }}
                style={{ margin: "10px" }}
            >
                <InputLabel id="demo-simple-select-label">From</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    label="City"
                >
                    {cities.map((city) => (
                        <MenuItem value={city}
                        onClick={() => setOrigin(city)}
                        >
                            {city}
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>
            <FormControl
                sx={{ minWidth: 120 }}
                style={{ margin: "10px" }}
            >
                <InputLabel id="demo-simple-select-label">To</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    label="City"
                >
                    {cities.map((city) => (
                        <MenuItem value={city}
                        onClick={() => setDestination(city)}
                        >
                            {city}
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DatePicker defaultValue={dayjs(today)} sx={{margin: "10px"}} />  
            </LocalizationProvider>

            <Button variant="outlined" style={{ margin: "10px" }}
                sx={{ backgroundColor: '#a2e53f', color: 'black'}}
                onClick={searchTrips}
            >
                Search
            </Button>
        </Container>
    )
}