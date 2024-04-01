package pt.ua.deti.backend.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import pt.ua.deti.backend.entity.Trip;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.deti.backend.entity.Seat;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Integer>{
    void deleteBySeatAndTripId(String seat, Integer tripId);
    List<Seat> findByTripId(Integer tripId);
}
