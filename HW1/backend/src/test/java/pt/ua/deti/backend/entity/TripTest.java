package pt.ua.deti.backend.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TripTest {
    @Test
    void gettersAndSettersTripTest() {
        Trip trip = new Trip();
        trip.setId(1);
        trip.setTripCode("BR-PO-005");
        trip.setOrigin("Porto");
        trip.setDestination("Braga");
        trip.setTime("2024-04-01");
        trip.setPrice(12.5f);

        assertEquals(1, trip.getId());
        assertEquals("BR-PO-005", trip.getTripCode());
        assertEquals("Porto", trip.getOrigin());
        assertEquals("Braga", trip.getDestination());
        assertEquals("2024-04-01", trip.getTime());
        assertEquals(12.5f, trip.getPrice());

        Trip trip2 = new Trip("BR-PO-005", "Porto", "Braga", "2024-04-01", 12.5f);
        
        assertEquals("BR-PO-005", trip2.getTripCode());
        assertEquals("Porto", trip2.getOrigin());
        assertEquals("Braga", trip2.getDestination());
        assertEquals("2024-04-01", trip2.getTime());
        assertEquals(12.5f, trip2.getPrice());
    }
}
