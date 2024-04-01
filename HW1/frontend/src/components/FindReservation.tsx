import React from "react";
import { Container } from "@mui/material";
import { FormControl, useFormControlContext } from '@mui/base/FormControl';
import { Input, inputClasses } from '@mui/base/Input';
import { styled } from '@mui/system';
import clsx from 'clsx';
import { useState } from "react";
import axios from "axios";
import Button from "@mui/material/Button";


export default function FindReservation() {
    const [email, setEmail] = useState("");
    const [reservationToken, setReservationToken] = useState("");
    const [ticketInfo, setTicketInfo] = useState(false)
    const [error, setError] = useState(false)
    const [reservation, setReservation] = useState({});
    const [date, setDate] = useState("");
    const getReservation = () => {
        axios.get("http://localhost:8080/api/v1/reservation?token=" + reservationToken)
            .then((response) => {
                console.log("Reservation found: ", response.data);
                if (email === response.data.userEmail) {
                    // display ticket info
                    setTicketInfo(true);
                    setError(false);
                    setReservation(response.data)
                    // date is like Sun, 07 Apr 2024 23:00:00 GM, remove hours
                    const parts = response.data.date.split(" ");
                    const formattedDate = parts.slice(1, 4).join(" ");
                    setDate(formattedDate)
                } else {
                    // no tickets wit this token for that email
                    setError(true);
                    setTicketInfo(false);
                }
            })
            .catch((error) => {
                console.error("Error finding reservation: ", error);
                setError(true);
                setTicketInfo(false);
            });
    }

    return (
        <>
            <Container
                sx={{ display: "flex", justifyContent: "center", marginTop: "20px"}}
            >
                <FormControl defaultValue="" required>
                    <Label>Email</Label>
                    <StyledInput placeholder="Write your email here" onChange={(e) => setEmail(e.target.value)} />
                    <HelperText />
                </FormControl>
                <FormControl defaultValue="" required>
                    <Label>Token</Label>
                    <StyledInput placeholder="Write your reservation token here" onChange={(e) => setReservationToken(e.target.value)} />
                    <HelperText />
                </FormControl>

                <Button variant="outlined" style={{ margin: "30px" }}
                    sx={{ backgroundColor: '#a2e53f', color: 'black', padding: "10px 12px", borderRadius: "8px", fontSize: "0.875rem", fontWeight: 400, lineHeight: 1.5 }}
                    onClick={getReservation}
                >
                    Get Reservation
                </Button>
            </Container>
            <Container
                sx={{ marginTop: "20px"}}
            >
                {ticketInfo && 
                    <div style={{marginBottom: "50px"}}>
                        <h1>Ticket Information:</h1>
                        <p>Ticket in the name of: <b>{reservation.userName}</b></p>
                        <p>With the Email: <b>{reservation.userEmail}</b></p>
                        <p>Reservation Token: <b>{reservationToken}</b></p>
                        <p>Date: <b>{date}</b></p>
                        <p>Time: <b>{reservation.time}h</b></p>
                        <p>Trip Code: <b>{reservation.tripCode}</b></p>
                        <p>Seat: <b>{reservation.seat}</b></p>
                    </div>
                }

                {error && 
                <div style={{marginBottom: "50px"}}>
                    <p style={{color: "red"}}> No Such Ticket Was Found</p>
                </div>    
                }
            </Container>
        </>
    )
}

const StyledInput = styled(Input)(
    ({ theme }) => `
  
    .${inputClasses.input} {
      width: 320px;
      margin-left: 10px;
      font-family: 'IBM Plex Sans', sans-serif;
      font-size: 0.875rem;
      font-weight: 400;
      line-height: 1.5;
      padding: 8px 12px;
      border-radius: 8px;
      color: ${theme.palette.mode === 'dark' ? grey[300] : grey[900]};
      background: ${theme.palette.mode === 'dark' ? grey[900] : '#fff'};
      border: 1px solid ${theme.palette.mode === 'dark' ? grey[700] : grey[200]};
      box-shadow: 0px 2px 2px ${theme.palette.mode === 'dark' ? grey[900] : grey[50]};
  
      &:hover {
        border-color: ${blue[400]};
      }
  
      &:focus {
        outline: 0;
        border-color: ${blue[400]};
        box-shadow: 0 0 0 3px ${theme.palette.mode === 'dark' ? blue[600] : blue[200]};
      }
    }
  `,
  );
  
  const Label = styled(
    ({ children, className }: { children?: React.ReactNode; className?: string }) => {
      const formControlContext = useFormControlContext();
      const [dirty, setDirty] = React.useState(false);
  
      React.useEffect(() => {
        if (formControlContext?.filled) {
          setDirty(true);
        }
      }, [formControlContext]);
  
      if (formControlContext === undefined) {
        return <p>{children}</p>;
      }
  
      const { error, required, filled } = formControlContext;
      const showRequiredError = dirty && required && !filled;
  
      return (
        <p className={clsx(className, error || showRequiredError ? 'invalid' : '')}>
          {children}
          {required ? ' *' : ''}
        </p>
      );
    },
  )`
    font-family: 'IBM Plex Sans', sans-serif;
    font-size: 0.875rem;
    margin-bottom: 4px;
  
    &.invalid {
      color: red;
    }
  `;
  
  const HelperText = styled((props: {}) => {
    const formControlContext = useFormControlContext();
    const [dirty, setDirty] = React.useState(false);
  
    React.useEffect(() => {
      if (formControlContext?.filled) {
        setDirty(true);
      }
    }, [formControlContext]);
  
    if (formControlContext === undefined) {
      return null;
    }
  
    const { required, filled } = formControlContext;
    const showRequiredError = dirty && required && !filled;
  
    return showRequiredError ? <p {...props}>This field is required.</p> : null;
  })`
    font-family: 'IBM Plex Sans', sans-serif;
    font-size: 0.875rem;
  `;
  
  const blue = {
    100: '#DAECFF',
    200: '#b6daff',
    400: '#3399FF',
    500: '#007FFF',
    600: '#0072E5',
    900: '#003A75',
  };
  
  const grey = {
    50: '#F3F6F9',
    100: '#E5EAF2',
    200: '#DAE2ED',
    300: '#C7D0DD',
    400: '#B0B8C4',
    500: '#9DA8B7',
    600: '#6B7A90',
    700: '#434D5B',
    800: '#303740',
    900: '#1C2025',
  };