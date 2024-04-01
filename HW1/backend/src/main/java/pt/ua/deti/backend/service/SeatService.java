package pt.ua.deti.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pt.ua.deti.backend.repository.SeatRepo;
import pt.ua.deti.backend.entity.Seat;
import java.util.List;

@Service
public class SeatService {
    private SeatRepo seatRepo;

    @Autowired
    public SeatService(SeatRepo seatRepo) {
        this.seatRepo = seatRepo;
    }

    // methods
    @Transactional
    public void deleteBySeatAndTripId(String seat, Integer tripId) {
        seatRepo.deleteBySeatAndTripId(seat, tripId);
    }

    public List<Seat> getSeatsByTripId(Integer tripId) {
        return seatRepo.findByTripId(tripId);
    }
}
