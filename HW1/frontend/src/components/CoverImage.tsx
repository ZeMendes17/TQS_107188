import Box from '@mui/material/Box';
import cover from '../assets/img/cover.jpg'

export default function CoverImage() {
    return (
        <Box
        component="img"
        sx={{
            marginTop: '55px',
            height: '400px',
            width: '100%',
            objectFit: 'cover',
        }}
        src={cover}
        />
    );
}