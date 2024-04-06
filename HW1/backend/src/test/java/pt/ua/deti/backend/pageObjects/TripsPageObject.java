package pt.ua.deti.backend.pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TripsPageObject {
    @FindBy(id = "buyTripPO-LI-004")
    private WebElement trip;

    public TripsPageObject(WebDriver driver) {
        driver.get("http://localhost:3030/");
        PageFactory.initElements(driver, this);
    }

    public WebElement getTrip() {
        return trip;
    }

    public void buyTrip() {
        trip.click();
    }
}
