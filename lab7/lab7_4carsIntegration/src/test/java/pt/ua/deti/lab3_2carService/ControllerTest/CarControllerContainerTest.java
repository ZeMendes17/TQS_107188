package pt.ua.deti.lab3_2carService.ControllerTest;

import pt.ua.deti.lab3_2carService.model.Car;
import pt.ua.deti.lab3_2carService.repository.CarRepository;

import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.boot.test.web.server.LocalServerPort;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create")
public class CarControllerContainerTest {
    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:13.2")
            .withUsername("user")
            .withPassword("user")
            .withDatabaseName("cars");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private CarRepository carRepository;

    Car car1;
    Car car2;

    @BeforeEach
    public void setUp() {
        car1 = new Car("Toyota", "Corolla");
        car2 = new Car("Peugeot", "206");
        carRepository.save(car1);
        carRepository.save(car2);
    }

    @AfterEach
    public void tearDown() {
        carRepository.deleteAll();
    }

    @Test
    void givenCars_whenGetCars_thenStatus200() {
        String endpoint = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(randomServerPort)
                .path("/api/v1/cars")
                .toUriString();

        given()
                .when()
                .get(endpoint)
                .then()
                .statusCode(200);
    }

    @Test
    void givenCar_whenGetCarById_thenStatus200() {
        String endpoint = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(randomServerPort)
                .path("/api/v1/cars/{id}")
                .buildAndExpand(car1.getCarId())
                .toUriString();

        given()
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .assertThat()
                .body("model", is(car1.getModel()));
    }

    @Test
    void givenCar_whenGetBadCarById_thenStatus404() {
        String endpoint = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(randomServerPort)
                .path("/api/v1/cars/{id}")
                .buildAndExpand(999)
                .toUriString();

        given()
                .when()
                .get(endpoint)
                .then()
                .statusCode(404);
    }

    @Test
    void givenCar_whenPostCar_thenStatus200() {
        String endpoint = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(randomServerPort)
                .path("/api/v1/cars")
                .toUriString();

        Car car3 = new Car("Renault", "Clio");

        given()
                .contentType("application/json")
                .body(car3)
                .when()
                .post(endpoint)
                .then()
                .statusCode(200)
                .assertThat()
                .body("model", is(car3.getModel()));
    }
}
