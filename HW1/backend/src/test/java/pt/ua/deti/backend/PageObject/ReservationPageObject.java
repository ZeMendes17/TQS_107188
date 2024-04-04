package pt.ua.deti.backend.PageObject;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReservationPageObject {
    @FindBy(id = "checkReservations")
    private WebElement checkReservations;

    @FindBy(id = "emailInput")
    private WebElement emailInput;

    @FindBy(id = "tokenInput")
    private WebElement tokenInput;

    @FindBy(id = "findReservation")
    private WebElement findReservation;

    public ReservationPageObject(WebDriver driver) {
        driver.get("http://localhost:3030/");
        PageFactory.initElements(driver, this);
    }

    public WebElement getCheckReservations() {
        return checkReservations;
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getTokenInput() {
        return tokenInput;
    }

    public WebElement getFindReservation() {
        return findReservation;
    }

    public void findReservation() {
        findReservation.click();
    }

    public void checkReservations() {
        checkReservations.click();
    }

    public void setEmailInput(String email) {
        emailInput.sendKeys(email);
    }

    public void setTokenInput(String token) {
        tokenInput.sendKeys(token);
    }
}
