package pt.ua.deti.lab3_2carService.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pt.ua.deti.lab3_2carService.model.Car;
import pt.ua.deti.lab3_2carService.repository.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindCarByExistingId_thenReturnCar() {
        // car
        Car car = new Car("Ferrari", "F40");
        entityManager.persistAndFlush(car);
        Car fromDb = carRepository.findById(car.getCarId()).orElse(null);

        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getCarId()).isEqualTo(car.getCarId());
        assertThat(fromDb.getMaker()).isEqualTo(car.getMaker());
        assertThat(fromDb.getModel()).isEqualTo(car.getModel());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(-11L);

        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        // cars
        Car car = new Car("Ferrari", "F40");
        Car car2 = new Car("Porsche", "911 GT3 RS");
        Car car3 = new Car("Lamborghini", "Countach");

        entityManager.persist(car);
        entityManager.persist(car2);
        entityManager.persist(car3);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getMaker).containsOnly(car.getMaker(), car2.getMaker(), car3.getMaker());
    }
}
