package pt.ua.deti.lab3_2carService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ua.deti.lab3_2carService.model.Car;
import pt.ua.deti.lab3_2carService.service.CarManagerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CarController {
    private CarManagerService carManagerService;

    @Autowired
    public CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return ResponseEntity.ok(carManagerService.saveCar(car));
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/cars/{carId}")
    public ResponseEntity<Car> getCar(@PathVariable Long carId) {
        return ResponseEntity.of(carManagerService.getCarDetails(carId));
    }
}
