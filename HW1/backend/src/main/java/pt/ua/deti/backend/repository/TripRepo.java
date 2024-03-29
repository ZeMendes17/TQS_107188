package pt.ua.deti.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ua.deti.backend.entity.Trip;

@Repository
public interface TripRepo extends JpaRepository<Trip, Integer>{
    
}
