package pt.ua.deti.lab3_2carService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ua.deti.lab3_2carService.model.Car;
import pt.ua.deti.lab3_2carService.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {
    private CarRepository carRepository;

    @Autowired
    public CarManagerService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long carId) {
        return carRepository.findById(carId);
    }
}
