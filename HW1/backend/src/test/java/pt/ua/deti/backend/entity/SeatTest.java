package pt.ua.deti.backend.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SeatTest {
    @Test
    void gettersAndSettersSeatTest() {
        Seat seat = new Seat();
        seat.setId(1);
        seat.setSeatNumber("3B");
        Trip trip = new Trip("BR-PO-005", "Porto", "Braga", "2024-04-01", 12.5f);
        seat.setTrip(trip);

        assertEquals(1, seat.getId());
        assertEquals("3B", seat.getSeatNumber());
        assertEquals(trip, seat.getTrip());

        Seat seat2 = new Seat("3B", trip);
        
        assertEquals("3B", seat2.getSeatNumber());
        assertEquals(trip, seat2.getTrip());
        assertEquals(trip.getTripCode(), seat2.getTrip().getTripCode());
    }
}
