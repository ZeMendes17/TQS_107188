package pt.ua.deti.lab3_2carService.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ua.deti.lab3_2carService.model.Car;
import pt.ua.deti.lab3_2carService.repository.CarRepository;
import pt.ua.deti.lab3_2carService.service.CarManagerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock(lenient = true)
    private CarRepository carRepository; // mock the repository to avoid using the real database

    @InjectMocks
    private CarManagerService carManagerService; // inject the mock repository into the service

    @BeforeEach
    public void setUp() {
        // create some cars
        Car car = new Car("Toyota", "Corolla");
        car.setCarId(1L);
        Car car2 = new Car("Peugeot", "206");
        car2.setCarId(2L);
        Car car3 = new Car("Citroen", "C3");
        car3.setCarId(3L);

        List<Car> allCars = Arrays.asList(car, car2, car3);

        // mock the behavior of the carRepository
        when(carRepository.findAll()).thenReturn(allCars);
        when(carRepository.findById(car.getCarId())).thenReturn(Optional.of(car));
        when(carRepository.findById(car2.getCarId())).thenReturn(Optional.of(car2));
        when(carRepository.findById(car3.getCarId())).thenReturn(Optional.of(car3));
        // give a wrong ID to the repository
        when(carRepository.findById(4L)).thenReturn(null);
    }

    // Test implementations
    @Test
    public void whenValidId_thenCarShouldBeFound() {
        Long carId = 1L;
        Car found = carManagerService.getCarDetails(carId).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getCarId()).isEqualTo(carId);

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenInvalidId_thenCarShouldNotBeFound() {
        Long carId = 4L;
        Optional<Car> found = carManagerService.getCarDetails(carId);

        assertThat(found).isNull();

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void given3Cars_whenGetAll_thenReturn3Records() {
        Car car = new Car("Toyota", "Corolla");
        Car car2 = new Car("Peugeot", "206");
        Car car3 = new Car("Citroen", "C3");

        List<Car> allCars = carManagerService.getAllCars();

        assertThat(allCars)
                .hasSize(3)
                .extracting(Car::getMaker)
                .contains(car.getMaker(), car2.getMaker(), car3.getMaker());

        verifyFindAllCarsIsCalledOnce();
    }


    private void verifyFindByIdIsCalledOnce() {
        verify(carRepository, times(1)).findById(anyLong());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        verify(carRepository, times(1)).findAll();
    }
}
