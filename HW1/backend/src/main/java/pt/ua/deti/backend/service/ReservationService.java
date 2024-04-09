package pt.ua.deti.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.deti.backend.entity.Reservation;
import pt.ua.deti.backend.repository.ReservationRepo;
import pt.ua.deti.backend.entity.User;

@Service
public class ReservationService {
    private static final Logger log = LoggerFactory.getLogger(ReservationService.class);
    private ReservationRepo reservationRepo;
    private Map<String, Integer> reservations;

    @Autowired
    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
        this.reservations = new HashMap<>();
    }

    // methods
    public Reservation save(Reservation reservation) {
        log.info("Saving reservation: {}", reservation);
        // check if it is possible to make a reservation

        String tripCode = reservation.getReservationToken().split("_")[0];
        if (reservations.containsKey(tripCode)) {
            if (reservations.get(tripCode) >= 6) {
                log.error("Reservation limit reached for trip: {}", tripCode);
                return null;
            }
            reservations.put(tripCode, reservations.get(tripCode) + 1);
        } else {
            reservations.put(tripCode, 1);
        }
        return reservationRepo.save(reservation);
    }

    public Reservation saveWithToken(String token, String date, User user) {
        log.info("Saving reservation with token: {}, date: {}, user: {}", token, date, user);
        // check if it is possible to make a reservation
        String tripCode = token.split("_")[0];
        if (reservations.containsKey(tripCode)) {
            if (reservations.get(tripCode) >= 6) {
                log.error("Reservation limit reached for trip: {}", tripCode);
                return null;
            }
            reservations.put(tripCode, reservations.get(tripCode) + 1);
        } else {
            reservations.put(tripCode, 1);
        }

        Reservation reservation = new Reservation(token, date, user);
        return reservationRepo.save(reservation);
    }

    public Reservation getReservationByToken(String token) {
        log.info("Getting reservation by token: {}", token);
        return reservationRepo.findByReservationToken(token);
    }
}
