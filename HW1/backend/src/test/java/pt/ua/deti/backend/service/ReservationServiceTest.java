package pt.ua.deti.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.deti.backend.entity.Reservation;
import pt.ua.deti.backend.entity.User;
import pt.ua.deti.backend.repository.ReservationRepo;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    
    @Mock(lenient = true)
    private ReservationRepo reservationRepo;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void whenValidReservation_thenReservationShouldBeSaved() {
        User user = new User("ze", "ze@example.com");
        Reservation reservation = new Reservation("PO-LI-001_B3", "04-04-2024", user);
        Mockito.when(reservationRepo.save(reservation)).thenReturn(reservation);
        

        Reservation savedReservation = reservationService.save(reservation);

        assertEquals(reservation.getReservationToken(), savedReservation.getReservationToken());
        verify(reservationRepo, times(1)).save(reservation);
    }

    @Test
    void whenReservationLimitReached_thenReservationShouldNotBeSaved() {
        User user = new User("ze", "ze@example.com");
        Reservation reservation1 = new Reservation("PO-LI-001_B3", "04-04-2024", user);
        Reservation reservation2 = new Reservation("PO-LI-001_B4", "04-04-2024", user);
        Reservation reservation3 = new Reservation("PO-LI-001_A1", "04-04-2024", user);
        Reservation reservation4 = new Reservation("PO-LI-001_A2", "04-04-2024", user);
        Reservation reservation5 = new Reservation("PO-LI-001_A3", "04-04-2024", user);
        Reservation reservation6 = new Reservation("PO-LI-001_A4", "04-04-2024", user);
        Reservation reservation7 = new Reservation("PO-LI-001_A5", "04-04-2024", user);

        Mockito.when(reservationRepo.save(reservation1)).thenReturn(reservation1);
        Mockito.when(reservationRepo.save(reservation2)).thenReturn(reservation2);
        Mockito.when(reservationRepo.save(reservation3)).thenReturn(reservation3);
        Mockito.when(reservationRepo.save(reservation4)).thenReturn(reservation4);
        Mockito.when(reservationRepo.save(reservation5)).thenReturn(reservation5);
        Mockito.when(reservationRepo.save(reservation6)).thenReturn(reservation6);
        Mockito.when(reservationRepo.save(reservation7)).thenReturn(reservation7);

        reservationService.save(reservation1);
        reservationService.save(reservation2);
        reservationService.save(reservation3);
        reservationService.save(reservation4);
        reservationService.save(reservation5);
        reservationService.save(reservation6);
        Reservation savedReservation = reservationService.save(reservation7);

        assertEquals(null, savedReservation);
        verify(reservationRepo, times(6)).save(any(Reservation.class));
        verify(reservationRepo, times(0)).save(reservation7);
    }

    @Test
    void whenValidReservationWithToken_thenReservationShouldBeSaved() {
        User user = new User("ze", "ze@example.com");
        Reservation reservation = new Reservation("FA-BR-001_A1", "04-04-2024", user);
        Mockito.when(reservationRepo.save(any(Reservation.class))).thenReturn(reservation);

        Reservation savedReservation = reservationService.saveWithToken("FA-BR-001_A1", "04-04-2024", user);
        
        assertEquals(reservation.getReservationToken(), savedReservation.getReservationToken());
        verify(reservationRepo, times(1)).save(any(Reservation.class));
    }

    @Test
    void whenReservationLimitReachedWithToken_thenReservationShouldNotBeSaved() {
        User user = new User("ze", "ze@example.com");
        Reservation reservation1 = new Reservation("FA-BR-001_A1", "04-04-2024", user);
        Reservation reservation2 = new Reservation("FA-BR-001_A2", "04-04-2024", user);
        Reservation reservation3 = new Reservation("FA-BR-001_A3", "04-04-2024", user);
        Reservation reservation4 = new Reservation("FA-BR-001_A4", "04-04-2024", user);
        Reservation reservation5 = new Reservation("FA-BR-001_B1", "04-04-2024", user);
        Reservation reservation6 = new Reservation("FA-BR-001_B2", "04-04-2024", user);
        Reservation reservation7 = new Reservation("FA-BR-001_B4", "04-04-2024", user);
        
        Mockito.when(reservationRepo.save(any(Reservation.class))).thenReturn(reservation1);
        reservationService.saveWithToken("FA-BR-001_A1", "04-04-2024", user);
        Mockito.when(reservationRepo.save(any(Reservation.class))).thenReturn(reservation2);
        reservationService.saveWithToken("FA-BR-001_A2", "04-04-2024", user);
        Mockito.when(reservationRepo.save(any(Reservation.class))).thenReturn(reservation3);
        reservationService.saveWithToken("FA-BR-001_A3", "04-04-2024", user);
        Mockito.when(reservationRepo.save(any(Reservation.class))).thenReturn(reservation4);
        reservationService.saveWithToken("FA-BR-001_A4", "04-04-2024", user);
        Mockito.when(reservationRepo.save(any(Reservation.class))).thenReturn(reservation5);
        reservationService.saveWithToken("FA-BR-001_B1", "04-04-2024", user);
        Mockito.when(reservationRepo.save(any(Reservation.class))).thenReturn(reservation6);
        reservationService.saveWithToken("FA-BR-001_B2", "04-04-2024", user);
        Mockito.when(reservationRepo.save(any(Reservation.class))).thenReturn(reservation7);
        Reservation savedReservation7 = reservationService.saveWithToken("FA-BR-001_B4", "04-04-2024", user);

        assertEquals(null, savedReservation7);
        verify(reservationRepo, times(6)).save(any(Reservation.class));
        verify(reservationRepo, times(0)).save(reservation7);
    }

    @Test
    void whenValidReservationToken_thenReservationShouldBeRetrieved() {
        User user = new User("ze", "ze@example.com");
        Reservation reservation = new Reservation("BR-LI-003_E3", "04-04-2024", user);
        Mockito.when(reservationRepo.findByReservationToken("BR-LI-003_E3")).thenReturn(reservation);
        
        Reservation retrievedReservation = reservationService.getReservationByToken("BR-LI-003_E3");

        assertEquals(reservation.getReservationToken(), retrievedReservation.getReservationToken());
        verify(reservationRepo, times(1)).findByReservationToken(anyString());
    }

    @Test
    void whenInvalidReservationToken_thenReservationShouldNotBeRetrieved() {
        Mockito.when(reservationRepo.findByReservationToken("BR-LI-003_E3")).thenReturn(null);
        
        Reservation retrievedReservation = reservationService.getReservationByToken("BR-LI-003_E3");

        assertEquals(null, retrievedReservation);
        verify(reservationRepo, times(1)).findByReservationToken(anyString());
    }
}
