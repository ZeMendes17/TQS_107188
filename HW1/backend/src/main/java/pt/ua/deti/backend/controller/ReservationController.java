package pt.ua.deti.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.deti.backend.entity.Reservation;
import pt.ua.deti.backend.service.ReservationService;
import pt.ua.deti.backend.service.SeatService;
import pt.ua.deti.backend.service.TripService;
import pt.ua.deti.backend.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pt.ua.deti.backend.entity.User;
import pt.ua.deti.backend.entity.Trip;
import pt.ua.deti.backend.entity.Seat;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:3030")
@RestController
@RequestMapping("/api/v1")
public class ReservationController {
    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    private ReservationService reservationService;
    private SeatService seatService;
    private UserService userService;
    private TripService tripService;

    @Autowired
    public ReservationController(ReservationService reservationService, SeatService seatService, UserService userService, TripService tripService) {
        this.reservationService = reservationService;
        this.seatService = seatService;
        this.userService = userService;
        this.tripService = tripService;
    }

    // methods
    @PostMapping("/reservation")
    public Reservation saveReservation(@RequestParam String code, @RequestParam String date, @RequestParam Integer userId) {
        log.info("POST /reservation?code={}&date={}&userId={}", code, date, userId);

        Trip trip = tripService.searchTripByCode(code);
        List<Seat> seats = seatService.getSeatsByTripId(trip.getId());
        Seat seat = seats.get(0);
        String token = code + "_" + seat.getSeatNumber();
        seatService.deleteBySeatNumberAndTripId(seat.getSeatNumber(), trip.getId());
        User user = userService.getUserById(userId);
        return reservationService.saveWithToken(token, date, user);
    }
    
    @GetMapping("/reservation")
    public Map<String, String> getReservationByToken(@RequestParam String token) {
        log.info("GET /reservation?token={}", token);

        Map<String, String> response = new HashMap<>();
        Reservation r = reservationService.getReservationByToken(token);
        response.put("reservationToken", r.getReservationToken());
        response.put("tripCode", r.getTripCode());
        response.put("seat", r.getSeat());
        response.put("date", r.getDate());
        User user = r.getUser();
        response.put("userName", user.getName());
        response.put("userEmail", user.getEmail());
        Trip trip = tripService.searchTripByCode(r.getTripCode());
        response.put("time", trip.getTime().split(":")[0] + ":" + trip.getTime().split(":")[1]);
        return response;
    }
    
}
