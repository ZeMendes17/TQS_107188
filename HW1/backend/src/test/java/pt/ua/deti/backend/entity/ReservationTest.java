package pt.ua.deti.backend.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ReservationTest {
    @Test
    void gettersAndSettersReservationTest() {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setReservationToken("BR-PO-005_3B");
        reservation.setTripCode("BR-PO-005");
        reservation.setSeat("3B");
        reservation.setDate("2021-06-01");
        User user = new User("Ze", "ze@example.com");
        reservation.setUser(user);

        assertEquals(1, reservation.getId());
        assertEquals("BR-PO-005_3B", reservation.getReservationToken());
        assertEquals("BR-PO-005", reservation.getTripCode());
        assertEquals("3B", reservation.getSeat());
        assertEquals("2021-06-01", reservation.getDate());
        assertEquals(user, reservation.getUser());

        Reservation reservation2 = new Reservation("BR-PO-005_3B", "2021-06-01", user);
        
        assertEquals("BR-PO-005_3B", reservation2.getReservationToken());
        assertEquals("BR-PO-005", reservation2.getTripCode());
        assertEquals("3B", reservation2.getSeat());
        assertEquals("2021-06-01", reservation2.getDate());
        assertEquals(user, reservation2.getUser());
        assertEquals(user.getName(), reservation2.getUser().getName());
        assertEquals(user.getEmail(), reservation2.getUser().getEmail());
    }
}
