package pt.ua.deti.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import pt.ua.deti.backend.entity.Reservation;
import pt.ua.deti.backend.entity.Seat;
import pt.ua.deti.backend.entity.Trip;
import pt.ua.deti.backend.entity.User;
import pt.ua.deti.backend.service.ReservationService;
import pt.ua.deti.backend.service.SeatService;
import pt.ua.deti.backend.service.TripService;
import pt.ua.deti.backend.service.UserService;

import java.util.List;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private SeatService seatService;

    @MockBean
    private UserService userService;

    @MockBean
    private TripService tripService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenSavingReservation_thenReturnReservation() {
        Trip trip = new Trip("BR-PO-004", "Braga", "Porto", "13:00", 8.0f);
        trip.setId(1);
        when(tripService.searchTripByCode("BR-PO-004")).thenReturn(trip);

        Seat seat = new Seat("A1", trip);
        Seat seat2 = new Seat("A2", trip);
        Seat seat3 = new Seat("B1", trip);
        Seat seat4 = new Seat("B2", trip);
        when(seatService.getSeatsByTripId(trip.getId())).thenReturn(List.of(seat, seat2, seat3, seat4));

        doNothing().when(seatService).deleteBySeatNumberAndTripId("A1", trip.getId());

        User user = new User("ze", "ze@example.com");
        user.setId(1);
        when(userService.getUserById(1)).thenReturn(user);

        Reservation reservation = new Reservation("BR-PO-004_A1", "04-04-2024", user);
        when(reservationService.saveWithToken("BR-PO-004_A1", "04-04-2024", user)).thenReturn(reservation);

        try {
            mockMvc.perform(post("/api/v1/reservation")
                .param("code", "BR-PO-004")
                .param("date", "04-04-2024")
                .param("userId", "1"))
                .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(tripService, times(1)).searchTripByCode("BR-PO-004");
        verify(seatService, times(1)).getSeatsByTripId(trip.getId());
        verify(seatService, times(1)).deleteBySeatNumberAndTripId("A1", trip.getId());
        verify(userService, times(1)).getUserById(1);
        verify(reservationService, times(1)).saveWithToken("BR-PO-004_A1", "04-04-2024", user);
    }

    @Test
    void whenGettingReservation_thenReturnReservation() {
        Reservation reservation = new Reservation("BR-PO-004_A1", "04-04-2024", new User("ze", "ze@example.com"));
        when(reservationService.getReservationByToken("BR-PO-004_A1")).thenReturn(reservation);
        
        Trip trip = new Trip("BR-PO-004", "Braga", "Porto", "13:00", 8.0f);
        when(tripService.searchTripByCode("BR-PO-004")).thenReturn(trip);

        try {
            mockMvc.perform(get("/api/v1/reservation")
                .param("token", "BR-PO-004_A1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationToken", is("BR-PO-004_A1")))
                .andExpect(jsonPath("$.tripCode", is("BR-PO-004")))
                .andExpect(jsonPath("$.seat", is("A1")))
                .andExpect(jsonPath("$.date", is("04-04-2024")))
                .andExpect(jsonPath("$.userName", is("ze")))
                .andExpect(jsonPath("$.userEmail", is("ze@example.com")))
                .andExpect(jsonPath("$.time", is("13:00")));

        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(reservationService, times(1)).getReservationByToken("BR-PO-004_A1");
        verify(tripService, times(1)).searchTripByCode("BR-PO-004");
    }
}
