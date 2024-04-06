package pt.ua.deti.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

import pt.ua.deti.backend.repository.TripRepo;
import pt.ua.deti.backend.entity.Trip;

@Service
public class TripService {
    private static final Logger log = LoggerFactory.getLogger(TripService.class);
    private TripRepo tripRepo;

    @Autowired
    public TripService(TripRepo tripRepo) {
        this.tripRepo = tripRepo;
    }

    // methods
    public Trip save(Trip trip) {
        log.info("Saving trip: {}", trip);

        return tripRepo.save(trip);
    }

    public List<Trip> getAllTrips() {
        log.info("Getting all trips");

        return tripRepo.findAll();
    }

    public List<String> getAllCities() {
        log.info("Getting all cities");

        List<Trip> trips = tripRepo.findAll();
        List<String> originCities = new ArrayList<>();
        List<String> destinationCities = new ArrayList<>();

        for (Trip trip : trips) {
            if (!originCities.contains(trip.getOrigin())) {
                originCities.add(trip.getOrigin());
            }
            if (!destinationCities.contains(trip.getDestination())) {
                destinationCities.add(trip.getDestination());
            }
        }

        for (String city : destinationCities) {
            if (!originCities.contains(city)) {
                originCities.add(city);
            }
        }

        return originCities;
    }

    public List<Trip> searchTrips(String origin, String destination) {
        log.info("Getting trips from {} to {}", origin, destination);
        return tripRepo.findByOriginAndDestination(origin, destination);
    }

    public Trip searchTripByCode(String code) {
        log.info("Getting trip with code: {}", code);
        return tripRepo.findByTripCode(code);
    }
}
