package pt.ua.deti.lab3_2carService.ControllerTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.couchbase.AutoConfigureDataCouchbase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import pt.ua.deti.lab3_2carService.model.Car;
import pt.ua.deti.lab3_2carService.repository.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase // To use a test database
@TestPropertySource( locations = "application-integrationtest.properties") // To use a real database
public class CarRestControllerTemplateIT {
    // will need to use the server port for the invocation url
    @LocalServerPort
    int port;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateCar() {
        Car car = new Car("Renault", "Clio");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/v1/cars", car, Car.class);
        List<Car> found = carRepository.findAll();

        assertThat(found).extracting(Car::getMaker).containsOnly("Renault");
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200() {
        createTestCar("Toyota", "Corolla");
        createTestCar("Peugeot", "206");
        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/v1/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("Toyota", "Peugeot");
    }

    @Test
    public void givenCar_returnsCarDetails() {
        createTestCar("Toyota", "Corolla");
        ResponseEntity<Car> response = restTemplate.getForEntity("/api/v1/cars/1", Car.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).isEqualTo("Toyota");

    }


    private void createTestCar(String maker, String model) {
        Car car = new Car(maker, model);
        carRepository.saveAndFlush(car);
    }
}
