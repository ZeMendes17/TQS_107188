package pt.ua.deti.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ua.deti.backend.entity.Trip;

@Repository
public interface TripRepo extends JpaRepository<Trip, Integer>{
    List<Trip> findByOriginAndDestination(String origin, String destination);
    Trip findByTripCode(String code);
}
