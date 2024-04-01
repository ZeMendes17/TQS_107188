import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import { useState, useEffect } from 'react';

interface TripCardProps {
    code: string;
}


export default function TripCard({ code }: TripCardProps) {

    const [trip, setTrip] = useState({});
    const [time, setTime] = useState("");
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

    const navigate = useNavigate();

    const buyTrip = () => {
        navigate(`/pay?code=${code}`);
    }

  return (
    <div>
      <Card sx={{ width: "1000px", marginBottom: "15px" }}>
        <CardContent>
          <Typography variant="h3" gutterBottom>
            From <u>{trip.origin}</u> to <u>{trip.destination}</u>
          </Typography>
          <Typography variant="h4" component="div">
            Trip Code: <u>{code}</u>
          </Typography>
          <Typography variant="body1" sx={{marginTop: "10px"}}>
            {time}
            <br />
          </Typography>
          <Typography variant="body1" sx={{marginTop: "10px"}}>
            {trip.price} €
            <br />
          </Typography>
        </CardContent>
        <CardActions>
          <Button size="small" variant="outlined"
                sx={{ backgroundColor: '#a2e53f', color: 'black'}}
                onClick={buyTrip}
                >Buy</Button>
        </CardActions>
      </Card>
    </div>
  );
}