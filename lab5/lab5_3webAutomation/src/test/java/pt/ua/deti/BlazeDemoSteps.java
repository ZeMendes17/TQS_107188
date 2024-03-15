package pt.ua.deti;

import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class BlazeDemoSteps {
    private WebDriver driver;

    @When("I navigate to {string}")
    public void navigateTo(String url) {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(url);
    }

    @And("I select the departure city as {string} and destination city as {string}")
    public void selectCities(String departure, String destination) {
        driver.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = '" + departure + "']")).click();
        }

        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = '" + destination + "']")).click();
        }
    }

    @And("I click on the {string} button")
    public void clickButton(String button) {
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    }

    @And("I select the flight number {int}")
    public void selectFlight(int number) {
        driver.findElement(By.cssSelector("tr:nth-child("+ number +") .btn")).click();
    }

    @And("I enter the passenger details, {string}, {string}, {string}, {string}, {string}")
    public void enterPassengerDetails(String name, String address, String city, String state, String zip) {
        driver.findElement(By.id("inputName")).sendKeys(name);
        driver.findElement(By.id("address")).sendKeys(address);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("zipCode")).sendKeys(zip);
    }

    @And("I enter the credit card details, {string}, {string}, {string}, {string}")
    public void enterCreditCardDetails(String cardNumber, String month, String year, String name) {
        driver.findElement(By.id("creditCardNumber")).sendKeys(cardNumber);
        driver.findElement(By.id("creditCardMonth")).sendKeys(month);
        driver.findElement(By.id("creditCardYear")).sendKeys(year);
        driver.findElement(By.id("nameOnCard")).sendKeys(name);
    }

    @Then("I assert that the title of the page is {string}")
    public void assertTitle(String title) {
        assertThat(driver.getTitle()).isEqualTo(title);
        driver.close();
    }
}
