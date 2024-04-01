package pt.ua.deti.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.deti.backend.entity.Reservation;
import pt.ua.deti.backend.repository.ReservationRepo;
import pt.ua.deti.backend.entity.User;

@Service
public class ReservationService {
    private ReservationRepo reservationRepo;

    @Autowired
    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    // methods
    public Reservation save(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    public Reservation saveWithToken(String token, String date, User user) {
        Reservation reservation = new Reservation(token, date, user);
        return reservationRepo.save(reservation);
    }

    public Reservation getReservationByToken(String token) {
        return reservationRepo.findByReservationToken(token);
    }
}
