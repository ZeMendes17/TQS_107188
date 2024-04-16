package pt.ua.deti.lab3_2carService.ControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ua.deti.lab3_2carService.controller.CarController;
import pt.ua.deti.lab3_2carService.model.Car;
import pt.ua.deti.lab3_2carService.service.CarManagerService;
import pt.ua.deti.lab3_2carService.JsonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(CarController.class)
public class CarControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private CarManagerService carManagerService;

    // NEW: Rest Assured
    @Autowired
    private MockMvc mockMvc;

    // Test implementations
    @Test
    public void whenGetCars_thenReturnCars() throws Exception {
        // create some cars
        Car car = new Car("Toyota", "Corolla");
        Car car2 = new Car("Peugeot", "206");
        Car car3 = new Car("Citroen", "C3");
        // mock the behavior of the carManagerService
        when(carManagerService.getAllCars()).thenReturn(Arrays.asList(car, car2, car3));

        RestAssuredMockMvc.given()
                .mockMvc(mockMvc)
                .when()
                .get("/api/v1/cars")
                .then()
                .statusCode(200)
                .body("$", hasSize(3))
                .body("[0].maker", is(car.getMaker()))
                .body("[0].model", is(car.getModel()))
                .body("[1].maker", is(car2.getMaker()))
                .body("[1].model", is(car2.getModel()))
                .body("[2].maker", is(car3.getMaker()))
                .body("[2].model", is(car3.getModel()));
    }

    @Test
    public void whenGetCar_thenReturnCar() throws Exception {
        // create a car
        Car car = new Car("Toyota", "Corolla");
        // mock the behavior of the carManagerService
        when(carManagerService.getCarDetails(anyLong())).thenReturn(Optional.of(car));

        RestAssuredMockMvc.given()
                .mockMvc(mockMvc)
                .when()
                .get("/api/v1/cars/1")
                .then()
                .statusCode(200)
                .body("maker", is(car.getMaker()))
                .body("model", is(car.getModel()));
    }

    @Test
    public void whenCreateCar_thenReturnCar() throws Exception {
        // create a car
        Car car = new Car("Toyota", "Corolla");
        // mock the behavior of the carManagerService
        when(carManagerService.saveCar(car)).thenReturn(car);
//        mvc.perform(post("/api/v1/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.maker", is(car.getMaker())))
//                .andExpect(jsonPath("$.model", is(car.getModel())));
//
//        verify(carManagerService, times(1)).saveCar(car);

        RestAssuredMockMvc.given()
                .mockMvc(mockMvc)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(JsonUtils.toJson(car))
                .when()
                .post("/api/v1/cars")
                .then()
                .statusCode(200)
                .body("maker", is(car.getMaker()))
                .body("model", is(car.getModel()));

        verify(carManagerService, times(1)).saveCar(car);
    }
}
