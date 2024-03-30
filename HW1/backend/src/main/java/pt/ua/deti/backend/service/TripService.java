package pt.ua.deti.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

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

    public List<Trip> getAllTrips() {
        return tripRepo.findAll();
    }

    public List<String> getAllCities() {
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
        return tripRepo.findByOriginAndDestination(origin, destination);
    }

    public Trip searchTripByCode(String code) {
        return tripRepo.findByTripCode(code);
    }
}
