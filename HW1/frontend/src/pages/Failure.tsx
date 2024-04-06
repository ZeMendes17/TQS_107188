import ResponsiveAppBar from "../components/ResponsiveAppBar";

export default function Failure() {
    return (
        <>
            <ResponsiveAppBar />
            <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', gap: '5px', marginTop: '100px' }}>
                <h1>Reservation Failed!</h1>
                <p>No seats left.</p>
            </div>
        </>
    )
}