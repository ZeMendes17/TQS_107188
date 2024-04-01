package pt.ua.deti.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ua.deti.backend.entity.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Integer>{
    Reservation findByReservationToken(String token);
}
