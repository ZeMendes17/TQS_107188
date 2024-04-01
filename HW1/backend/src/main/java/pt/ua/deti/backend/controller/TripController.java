package pt.ua.deti.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.deti.backend.service.TripService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pt.ua.deti.backend.entity.Trip;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class TripController {
    private static final Logger log = LoggerFactory.getLogger(TripController.class);
    private TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // methods

    // method to get all trips
    @GetMapping("/trip")
    public List<Trip> getAllTrips() {
        log.info("GET /trip");
        return tripService.getAllTrips();
    }
    
    // method to get all possible cities
    @GetMapping("/city")
    public List<String> getAllCities() {
        log.info("GET /city");
        return tripService.getAllCities();
    }

    // method that given a origin and a destination returns all trips that have that origin and destination
    @GetMapping("/trip/search")
    public List<Trip> searchTrips(@RequestParam String origin, @RequestParam String destination) {
        log.info("GET /trip/search?origin=" + origin + "&destination=" + destination);
        return tripService.searchTrips(origin, destination);
    }

    // method that given a code returns the trip with that code
    @GetMapping("/trip/search/code")
    public Trip searchTripByCode(@RequestParam String code) {
        log.info("GET /trip/search/code?code=" + code);
        return tripService.searchTripByCode(code);
    }
}
