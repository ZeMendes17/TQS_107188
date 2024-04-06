package pt.ua.deti.backend.service;

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

    @Autowired
    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    // methods
    public Reservation save(Reservation reservation) {
        log.info("Saving reservation: {}", reservation);
        return reservationRepo.save(reservation);
    }

    public Reservation saveWithToken(String token, String date, User user) {
        log.info("Saving reservation with token: {}, date: {}, user: {}", token, date, user);
        Reservation reservation = new Reservation(token, date, user);
        return reservationRepo.save(reservation);
    }

    public Reservation getReservationByToken(String token) {
        log.info("Getting reservation by token: {}", token);
        return reservationRepo.findByReservationToken(token);
    }
}
