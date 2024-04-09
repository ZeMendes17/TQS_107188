package pt.ua.deti.backend.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.deti.backend.repository.TripRepo;
import pt.ua.deti.backend.entity.Trip;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @Mock(lenient = true)
    private TripRepo tripRepo;

    @InjectMocks
    private TripService tripService;

    @Test
    void whenSavingTrip_thenTripShouldBeSaved() {
        Trip trip = new Trip("PO-LI-001", "Porto", "Lisboa", "10:10", 12.0f);
        when(tripRepo.save(trip)).thenReturn(trip);

        Trip savedTrip = tripService.save(trip);

        assertEquals(trip.getTripCode(), savedTrip.getTripCode());
        verify(tripRepo, times(1)).save(trip);
    }

    @Test
    void whenGettingAllTrips_thenAllTripsShouldBeReturned() {
        Trip trip1 = new Trip("PO-LI-001", "Porto", "Lisboa", "10:10", 12.0f);
        Trip trip2 = new Trip("PO-LI-002", "Porto", "Lisboa", "12:10", 12.0f);
        Trip trip3 = new Trip("PO-LI-003", "Porto", "Lisboa", "14:10", 12.0f);
        when(tripRepo.findAll()).thenReturn(List.of(trip1, trip2, trip3));

        List<Trip> trips = tripService.getAllTrips();

        assertEquals(3, trips.size());
        verify(tripRepo, times(1)).findAll();
    }

    @Test
    void whenGettingAllCities_thenAllCitiesShouldBeReturned() {
        Trip trip1 = new Trip("PO-LI-001", "Porto", "Lisboa", "10:10", 12.0f);
        Trip trip2 = new Trip("PO-LI-002", "Porto", "Lisboa", "12:10", 12.0f);
        Trip trip3 = new Trip("PO-LI-003", "Porto", "Lisboa", "14:10", 12.0f);
        when(tripRepo.findAll()).thenReturn(List.of(trip1, trip2, trip3));

        List<String> cities = tripService.getAllCities();

        assertEquals(2, cities.size());
        List<String> expectedCities = List.of("Porto", "Lisboa");
        assertEquals(expectedCities, cities);
        verify(tripRepo, times(1)).findAll();
    }

    @Test
    void whenSearchingTrips_thenTripsShouldBeReturned() {
        Trip trip1 = new Trip("PO-LI-001", "Porto", "Lisboa", "10:10", 12.0f);
        Trip trip2 = new Trip("PO-LI-002", "Porto", "Lisboa", "12:10", 12.0f);
        Trip trip3 = new Trip("PO-LI-003", "Porto", "Lisboa", "14:10", 12.0f);
        when(tripRepo.findByOriginAndDestination("Porto", "Lisboa")).thenReturn(List.of(trip1, trip2, trip3));

        List<Trip> trips = tripService.searchTrips("Porto", "Lisboa");

        assertEquals(3, trips.size());
        verify(tripRepo, times(1)).findByOriginAndDestination("Porto", "Lisboa");
    }

    @Test
    void whenSearchingTripByCode_thenTripShouldBeReturned() {
        Trip trip = new Trip("PO-LI-001", "Porto", "Lisboa", "10:10", 12.0f);
        when(tripRepo.findByTripCode("PO-LI-001")).thenReturn(trip);

        Trip foundTrip = tripService.searchTripByCode("PO-LI-001");

        assertEquals(trip.getTripCode(), foundTrip.getTripCode());
        verify(tripRepo, times(1)).findByTripCode("PO-LI-001");
    }
}
