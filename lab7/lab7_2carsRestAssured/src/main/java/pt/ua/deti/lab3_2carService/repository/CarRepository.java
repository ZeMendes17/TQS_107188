package pt.ua.deti.lab3_2carService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.deti.lab3_2carService.model.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>{
    // method to find a car by a given ID
    Car findByCarId(Long carId);
}
