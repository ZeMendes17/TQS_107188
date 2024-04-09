package pt.ua.deti.backend.service;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import pt.ua.deti.backend.entity.Seat;
import pt.ua.deti.backend.entity.Trip;
import pt.ua.deti.backend.repository.SeatRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SeatServiceTest {
    
    @Mock(lenient = true)
    private SeatRepo seatRepo;

    @InjectMocks
    private SeatService seatService;

    @Test
    void whenValidSeat_thenSeatShouldBeDeleted() {
        Mockito.doNothing().when(seatRepo).deleteBySeatNumberAndTripId("B3", 1);
        seatService.deleteBySeatNumberAndTripId("B3", 1);
        
        verify(seatRepo, times(1)).deleteBySeatNumberAndTripId("B3", 1);   
    }

    @Test
    void whenValidTrip_thenSeatsShouldBeReturned() {
        Trip trip = new Trip("PO-LI-001", "Porto", "Lisboa", "10:10", 12.0f);
        Seat seat1 = new Seat("B3", trip);
        Seat seat2 = new Seat("B4", trip);
        Seat seat3 = new Seat("A1", trip);
        Seat seat4 = new Seat("A2", trip);
        Seat seat5 = new Seat("A3", trip);
        Seat seat6 = new Seat("A4", trip);
        Seat seat7 = new Seat("C1", trip);
        Mockito.when(seatRepo.findByTripId(1)).thenReturn(List.of(seat1, seat2, seat3, seat4, seat5, seat6, seat7));

        List<Seat> seats = seatService.getSeatsByTripId(1);

        assertEquals(7, seats.size());
        verify(seatRepo, times(1)).findByTripId(1);
    }
}
