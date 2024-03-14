package pt.ua.deti;


import static java.lang.invoke.MethodHandles.lookup;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

public class CalculatorSteps {
    static final Logger log = getLogger(lookup().lookupClass());
    private Calculator calculator;

    @Given("a calculator I just turned on")
    public void setup() {
        calculator = new Calculator();
    }

    @When("I add {int} and {int}")
    public void add(int arg1, int arg2) {
        log.info("Adding {} and {}", arg1, arg2);
        calculator.push(arg1);
        calculator.push(arg2);
        calculator.push("+");
    }

    @When("I subtract {int} to {int}")
    public void subtract(int arg1, int arg2) {
        log.info("Subtracting {} to {}", arg1, arg2);
        calculator.push(arg1);
        calculator.push(arg2);
        calculator.push("-");
    }

    @When("I multiply {int} and {int}")
    public void multiply(int arg1, int arg2) {
        log.info("Multiplying {} and {}", arg1, arg2);
        calculator.push(arg1);
        calculator.push(arg2);
        calculator.push("*");
    }

    @When("I divide {int} by {int}")
    public void divide(int arg1, int arg2) {
        log.info("Dividing {} by {}", arg1, arg2);
        calculator.push(arg1);
        calculator.push(arg2);
        calculator.push("/");
    }

    @Then("the result is {double}")
    public void the_result_is(double expected) {
        Number value = calculator.value();
        log.debug("Result: {} (expected: {})", value, expected);
        assertEquals(expected, value);
    }
}
