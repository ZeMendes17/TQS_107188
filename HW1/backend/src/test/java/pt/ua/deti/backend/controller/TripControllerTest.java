package pt.ua.deti.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import pt.ua.deti.backend.entity.Trip;
import pt.ua.deti.backend.service.TripService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(TripController.class)
class TripControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenGettingAllTrips_thenAllTripsShouldBeReturned() {
        List<Trip> trips = new ArrayList<>();
        trips.add(new Trip("PO-LI-001", "Porto", "Lisboa", "10:00", 12.0f));
        trips.add(new Trip("PO-LI-002", "Porto", "Lisboa", "15:00", 12.0f));
        trips.add(new Trip("PO-LI-003", "Porto", "Lisboa", "20:00", 12.0f));
        when(tripService.getAllTrips()).thenReturn(trips);

        try {
            mockMvc.perform(get("/api/v1/trip"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].tripCode", is("PO-LI-001")))
                .andExpect(jsonPath("$[0].origin", is("Porto")))
                .andExpect(jsonPath("$[0].destination", is("Lisboa")))
                .andExpect(jsonPath("$[0].time", is("10:00")))
                .andExpect(jsonPath("$[0].price", is(12.0)))
                .andExpect(jsonPath("$[1].tripCode", is("PO-LI-002")))
                .andExpect(jsonPath("$[1].origin", is("Porto")))
                .andExpect(jsonPath("$[1].destination", is("Lisboa")))
                .andExpect(jsonPath("$[1].time", is("15:00")))
                .andExpect(jsonPath("$[1].price", is(12.0)))
                .andExpect(jsonPath("$[2].tripCode", is("PO-LI-003")))
                .andExpect(jsonPath("$[2].origin", is("Porto")))
                .andExpect(jsonPath("$[2].destination", is("Lisboa")))
                .andExpect(jsonPath("$[2].time", is("20:00")))
                .andExpect(jsonPath("$[2].price", is(12.0)));

        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(tripService, times(1)).getAllTrips();
    }

    @Test
    void whenGettingAllCities_thenAllCitiesShouldBeReturned() {
        List<String> cities = new ArrayList<>();
        cities.add("Porto");
        cities.add("Lisboa");
        cities.add("Coimbra");
        cities.add("Aveiro");
        when(tripService.getAllCities()).thenReturn(cities);

        try {
            mockMvc.perform(get("/api/v1/city"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0]", is("Porto")))
                .andExpect(jsonPath("$[1]", is("Lisboa")))
                .andExpect(jsonPath("$[2]", is("Coimbra")))
                .andExpect(jsonPath("$[3]", is("Aveiro")));

        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(tripService, times(1)).getAllCities();
    }

    @Test
    void whenSearchingTrips_thenTripsWithOriginAndDestinationShouldBeReturned() {
        List<Trip> trips = new ArrayList<>();
        trips.add(new Trip("PO-LI-001", "Porto", "Lisboa", "10:00", 12.0f));
        trips.add(new Trip("PO-LI-002", "Porto", "Lisboa", "15:00", 12.0f));
        trips.add(new Trip("PO-LI-003", "Porto", "Lisboa", "20:00", 12.0f));
        when(tripService.searchTrips("Porto", "Lisboa")).thenReturn(trips);

        try {
            mockMvc.perform(get("/api/v1/trip/search")
                .param("origin", "Porto")
                .param("destination", "Lisboa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].tripCode", is("PO-LI-001")))
                .andExpect(jsonPath("$[0].origin", is("Porto")))
                .andExpect(jsonPath("$[0].destination", is("Lisboa")))
                .andExpect(jsonPath("$[0].time", is("10:00")))
                .andExpect(jsonPath("$[0].price", is(12.0)))
                .andExpect(jsonPath("$[1].tripCode", is("PO-LI-002")))
                .andExpect(jsonPath("$[1].origin", is("Porto")))
                .andExpect(jsonPath("$[1].destination", is("Lisboa")))
                .andExpect(jsonPath("$[1].time", is("15:00")))
                .andExpect(jsonPath("$[1].price", is(12.0)))
                .andExpect(jsonPath("$[2].tripCode", is("PO-LI-003")))
                .andExpect(jsonPath("$[2].origin", is("Porto")))
                .andExpect(jsonPath("$[2].destination", is("Lisboa")))
                .andExpect(jsonPath("$[2].time", is("20:00")))
                .andExpect(jsonPath("$[2].price", is(12.0)));

        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(tripService, times(1)).searchTrips("Porto", "Lisboa");
    }

    @Test
    void whenSearchingTripByCode_thenTripWithCodeShouldBeReturned() {
        Trip trip = new Trip("PO-LI-001", "Porto", "Lisboa", "10:00", 12.0f);
        when(tripService.searchTripByCode("PO-LI-001")).thenReturn(trip);

        try {
            mockMvc.perform(get("/api/v1/trip/search/code")
                .param("code", "PO-LI-001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tripCode", is("PO-LI-001")))
                .andExpect(jsonPath("$.origin", is("Porto")))
                .andExpect(jsonPath("$.destination", is("Lisboa")))
                .andExpect(jsonPath("$.time", is("10:00")))
                .andExpect(jsonPath("$.price", is(12.0)));

        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(tripService, times(1)).searchTripByCode("PO-LI-001");
    }
}