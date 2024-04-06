import * as React from 'react';
import { FormControl, useFormControlContext } from '@mui/base/FormControl';
import { Input, inputClasses } from '@mui/base/Input';
import { styled } from '@mui/system';
import clsx from 'clsx';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import dayjs from 'dayjs';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useState } from 'react';

interface UserFormProps {
    code: string;
}

export default function UserForm({ code }: UserFormProps) {
    const today = new Date().toISOString().split('T')[0];
    const [name, setName] = React.useState("");
    const [email, setEmail] = React.useState("");
    const [address, setAddress] = React.useState("");
    const [city, setCity] = React.useState("");
    const [zipCode, setZipCode] = React.useState("");
    const [creditCardType, setCreditCardType] = React.useState("visa");
    const [creditCardNumber, setCreditCardNumber] = React.useState("");
    const [cvv, setCvv] = React.useState("");
    const [nameOnCard, setNameOnCard] = React.useState("");
    const [user, setUser] = useState({});

    const navigate = useNavigate();

    const purchaseTrip = () => {
        // verify the form
        if (name === "" || email === "" || address === "" || city === "" || zipCode === "" || creditCardNumber === "" || cvv === "" || nameOnCard === "") {
            alert("Please fill all the fields!");
            return;
        }

        // verify the email
        if (!email.includes("@")) {
            alert("Please enter a valid email!");
            return;
        }

        // add name and email to local storage
        localStorage.setItem("name", name);
        localStorage.setItem("email", email);

        // post the purchase to the backend
        axios.post("http://localhost:8080/api/v1/user", {
            name: name,
            email: email,
        })
        .then((response) => {
            console.log("User created: ", response.data);
            setUser(response.data);
            // make the reservation
            axios.post("http://localhost:8080/api/v1/reservation?code=" + code
            + "&date=" + localStorage.getItem("date") + "&userId=" + response.data.id)
            .then((response) => {
                console.log("Reservation created: ", response.data);
                // navigate to success page
                if (response.data === null) {
                    alert("The trip is already full!");
                    navigate("/failure");
                }
                navigate("/success?reservationToken=" + response.data.reservationToken);
            })
            .catch((error) => {
                console.error("Error creating reservation: ", error);
            });
        })
        .catch((error) => {
            console.error("Error creating user: ", error);
        });
    }

  return (
    <div style={{ display: 'flex', flexDirection: 'column', gap: '20px' }}>
        <FormControl defaultValue="" required>
            <Label>Name</Label>
            <StyledInput placeholder="Write your name here" onChange={(e) => setName(e.target.value)} id='nameInput' />
            <HelperText />
        </FormControl>

        <FormControl defaultValue="" required>
            <Label>Email</Label>
            <StyledInput placeholder="Write your email here" onChange={(e) => setEmail(e.target.value)} id='emailInput' />
            <HelperText />
        </FormControl>

        <FormControl defaultValue="" required>
            <Label>Address</Label>
            <StyledInput placeholder="Write your address here" onChange={(e) => setAddress(e.target.value)} id='addressInput' />
            <HelperText />
        </FormControl>

        <FormControl defaultValue="" required>
            <Label>City</Label>
            <StyledInput placeholder="Write your city here" onChange={(e) => setCity(e.target.value)} id='cityInput' />
            <HelperText />
        </FormControl>

        <FormControl defaultValue="" required>
            <Label>Zip Code</Label>
            <StyledInput placeholder="Write your zip code here" onChange={(e) => setZipCode(e.target.value)} id='zipCodeInput' />
            <HelperText />
        </FormControl>

        <FormControl defaultValue="" required>
            <Label>Credit Card Type</Label>
            <Select defaultValue="visa"
            sx={{ width: 350 }}
            id='creditCardTypeInput'
            >
                <MenuItem value="visa" onClick={() => setCreditCardType("visa")} id='creditCardVisa'>Visa</MenuItem>
                <MenuItem value="mastercard" onClick={() => setCreditCardType("mastercard")} id='creditCardMaster'>Mastercard</MenuItem>
                <MenuItem value="maestro" onClick={() => setCreditCardType("maestro")} id='creditCardMaestro'>Maestro</MenuItem>
            </Select>
            <HelperText />
        </FormControl>

        <FormControl defaultValue="" required>
            <Label>Credit Card Number</Label>
            <StyledInput placeholder="Write your credit card number here" onChange={(e) => setCreditCardNumber(e.target.value)} id='creditCardNumberInput' />
            <HelperText />
        </FormControl>

        <FormControl defaultValue="" required>
            <Label>Expiration Date</Label>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DatePicker defaultValue={dayjs(today)} sx={{width: 350}} />
            </LocalizationProvider>
            <HelperText />
        </FormControl>

        <FormControl defaultValue="" required>
            <Label>CVV</Label>
            <StyledInput placeholder="Write your CVV here" onChange={(e) => setCvv(e.target.value)} id='cvvInput' />
            <HelperText />
        </FormControl>

        <FormControl defaultValue="" required>
            <Label>Name on Card</Label>
            <StyledInput placeholder="Write your name on card here" onChange={(e) => setNameOnCard(e.target.value)} id='nameOnCardInput' />
            <HelperText />
        </FormControl>

        <Button variant="contained" sx={{backgroundColor: '#a2e53f', color: 'black', marginTop: "20px"}} onClick={purchaseTrip} id='purchaseButton'>
            Pay
        </Button>
    </div>
  );
}

const StyledInput = styled(Input)(
  ({ theme }) => `

  .${inputClasses.input} {
    width: 320px;
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