package pt.ua.deti.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pt.ua.deti.backend.repository.SeatRepo;
import pt.ua.deti.backend.entity.Seat;
import java.util.List;

@Service
public class SeatService {
    private static final Logger log = LoggerFactory.getLogger(SeatService.class);
    private SeatRepo seatRepo;

    @Autowired
    public SeatService(SeatRepo seatRepo) {
        this.seatRepo = seatRepo;
    }

    // methods
    @Transactional
    public void deleteBySeatAndTripId(String seat, Integer tripId) {
        log.info("Deleting seat: " + seat + " from trip with id: " + tripId);
        seatRepo.deleteBySeatAndTripId(seat, tripId);
    }

    public List<Seat> getSeatsByTripId(Integer tripId) {
        log.info("Getting seats from trip with id: " + tripId);
        return seatRepo.findByTripId(tripId);
    }
}
