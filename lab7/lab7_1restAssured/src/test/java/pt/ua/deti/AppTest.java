package pt.ua.deti;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void endpointAllToDosTest() {
        get("https://jsonplaceholder.typicode.com/todos")
            .then()
            .statusCode(200);
    }

    @Test
    public void endpointToDo4Test() {
        get("https://jsonplaceholder.typicode.com/todos/4")
            .then()
            .assertThat()
            .body("title", equalTo("et porro tempora"));
    }

    @Test
    public void listAllToDosTest() {
        get("https://jsonplaceholder.typicode.com/todos")
            .then()
            .assertThat()
            .body("id", hasItems(198, 199));
    }

    @Test
    public void listAllToDosLessThan2SecondsTest() {
        get("https://jsonplaceholder.typicode.com/todos")
            .then()
            .time(lessThan(2000L));
    }
}
