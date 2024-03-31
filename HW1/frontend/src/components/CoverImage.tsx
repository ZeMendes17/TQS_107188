import cover from '../assets/img/cover.jpg'

export default function CoverImage() {
    return (
        <img
            style={{
                marginTop: '55px',
                height: '400px',
                width: '100%',
                objectFit: 'cover',
            }}
            alt="Cover"
            src={cover}
        />
    );
}