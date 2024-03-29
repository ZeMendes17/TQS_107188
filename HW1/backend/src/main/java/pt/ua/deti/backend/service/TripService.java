package pt.ua.deti.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.deti.backend.repository.TripRepo;
import pt.ua.deti.backend.entity.Trip;

@Service
public class TripService {
    private TripRepo tripRepo;

    @Autowired
    public TripService(TripRepo tripRepo) {
        this.tripRepo = tripRepo;
    }

    // methods
    public Trip save(Trip trip) {
        return tripRepo.save(trip);
    }
}
