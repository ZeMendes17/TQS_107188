package pt.ua.deti;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TravelAgencyPage {
    private WebDriver driver;

    @FindBy(name = "fromPort")
    private WebElement fromPort;

    @FindBy(name = "toPort")
    private WebElement toPort;

    @FindBy(css = ".btn-primary")
    private WebElement findFlightsButton;

    @FindBy(css = "tr:nth-child(3) .btn")
    private WebElement chooseFlightButton;

    @FindBy(id = "inputName")
    private WebElement inputName;

    @FindBy(id = "address")
    private WebElement inputAddress;

    @FindBy(id = "city")
    private WebElement inputCity;

    @FindBy(id = "state")
    private WebElement inputState;

    @FindBy(id = "zipCode")
    private WebElement inputZipCode;

    @FindBy(id = "cardType")
    private WebElement inputCardType;

    @FindBy(id = "creditCardNumber")
    private WebElement inputCreditCardNumber;

    @FindBy(id = "creditCardMonth")
    private WebElement inputCreditCardMonth;

    @FindBy(id = "creditCardYear")
    private WebElement inputCreditCardYear;

    @FindBy(id = "nameOnCard")
    private WebElement inputNameOnCard;

    @FindBy(css = ".btn-primary")
    private WebElement purchaseButton;

    // Constructor
    public TravelAgencyPage(WebDriver driver) {
        this.driver = driver;

        // Initialize web elements
        PageFactory.initElements(driver, this);
    }

    // Setters
    public void setFromPort(String fromPort) {
        this.fromPort.findElement(By.xpath("//option[. = '" + fromPort + "']")).click();
    }

    public void setToPort(String toPort) {
        this.toPort.findElement(By.xpath("//option[. = '" + toPort + "']")).click();
    }

    public void setInputName(String inputName) {
        this.inputName.sendKeys(inputName);
    }

    public void setInputAddress(String inputAddress) {
        this.inputAddress.sendKeys(inputAddress);
    }

    public void setInputCity(String inputCity) {
        this.inputCity.sendKeys(inputCity);
    }

    public void setInputState(String inputState) {
        this.inputState.sendKeys(inputState);
    }

    public void setInputZipCode(String inputZipCode) {
        this.inputZipCode.sendKeys(inputZipCode);
    }

    public void setInputCreditCardNumber(String inputCreditCardNumber) {
        this.inputCreditCardNumber.sendKeys(inputCreditCardNumber);
    }

    public void setInputCreditCardMonth(String inputCreditCardMonth) {
        this.inputCreditCardMonth.sendKeys(inputCreditCardMonth);
    }

    public void setInputCreditCardYear(String inputCreditCardYear) {
        this.inputCreditCardYear.sendKeys(inputCreditCardYear);
    }

    public void setInputNameOnCard(String inputNameOnCard) {
        this.inputNameOnCard.sendKeys(inputNameOnCard);
    }

    // Actions
    public void clickFromPort() {
        fromPort.click();
    }

    public void clickToPort() {
        toPort.click();
    }

    public void clickInputName() {
        inputName.click();
    }

    public void clickInputAddress() {
        inputAddress.click();
    }

    public void clickInputCity() {
        inputCity.click();
    }

    public void clickInputState() {
        inputState.click();
    }

    public void clickInputZipCode() {
        inputZipCode.click();
    }

    public void clickInputCreditCardNumber() {
        inputCreditCardNumber.click();
    }

    public void clickInputCreditCardMonth() {
        inputCreditCardMonth.click();
    }

    public void clickInputCreditCardYear() {
        inputCreditCardYear.click();
    }

    public void clickInputNameOnCard() {
        inputNameOnCard.click();
    }

    // Button Actions
    public void findFlights() {
        findFlightsButton.click();
    }

    public void chooseFlight() {
        chooseFlightButton.click();
    }

    public void purchase() {
        purchaseButton.click();
    }


    // Verifications
    public boolean isConfirmationPage() {
        return driver.getTitle().equals("BlazeDemo Confirmation");
    }
}