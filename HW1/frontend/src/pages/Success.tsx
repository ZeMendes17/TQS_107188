interface TripCardProps {
    code: string;
    name: string;
    email: string;
}

export default function Success({ code, name, email }: TripCardProps) {
    // get code, name and email from the local storage
    code = localStorage.getItem("code") || "";
    name = localStorage.getItem("name") || "";
    email = localStorage.getItem("email") || "";

    return (
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', gap: '20px', marginTop: '100px' }}>
            <h1>Payment Successful!</h1>
            <h2>{name}, your trip code is: <u>{code}</u></h2>
            <h2>The ticket has been sent to: {email}</h2>
        </div>
    )
}