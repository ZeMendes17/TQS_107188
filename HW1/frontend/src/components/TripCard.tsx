import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";

interface TripCardProps {
    origin: string;
    destination: string;
    time: string;
    code: string;
    price: number;
}


export default function TripCard({ origin, destination, time, code, price }: TripCardProps) {
    // time is a string in the format "08:00:00", remove the seconds
    time = time.split(":").slice(0, 2).join(":");

  return (
    <div>
      <Card sx={{ width: "1000px", marginBottom: "15px" }}>
        <CardContent>
          <Typography variant="h3" gutterBottom>
            From <u>{origin}</u> to <u>{destination}</u>
          </Typography>
          <Typography variant="h4" component="div">
            Trip Code: <u>{code}</u>
          </Typography>
          <Typography variant="body1" sx={{marginTop: "10px"}}>
            {time}
            <br />
          </Typography>
          <Typography variant="body1" sx={{marginTop: "10px"}}>
            {price} €
            <br />
          </Typography>
        </CardContent>
        <CardActions>
          <Button size="small" variant="outlined"
                sx={{ backgroundColor: '#a2e53f', color: 'black'}}
                onClick={() => alert("Buy " + code)}
                >Buy</Button>
        </CardActions>
      </Card>
    </div>
  );
}