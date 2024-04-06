package pt.ua.deti.backend.PageObject;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PayPageObject {
    @FindBy(id = "currencySelect")
    private WebElement currencySelect;

    @FindBy(id = "coincurrencyUSD")
    private WebElement coincurrencyUSD;

    @FindBy(id = "nameInput")
    private WebElement nameInput;

    @FindBy(id = "emailInput")
    private WebElement emailInput;

    @FindBy(id = "addressInput")
    private WebElement addressInput;

    @FindBy(id = "cityInput")
    private WebElement cityInput;

    @FindBy(id = "zipCodeInput")
    private WebElement zipCodeInput;

    @FindBy(id = "creditCardTypeInput")
    private WebElement creditCardTypeInput;

    @FindBy(id = "creditCardMaestro")
    private WebElement cardMaestro;

    @FindBy(id = "creditCardNumberInput")
    private WebElement creditCardNumberInput;

    @FindBy(css = ".MuiIconButton-edgeEnd > .MuiSvgIcon-root")
    private WebElement calendar;

    @FindBy(css = ".MuiPickersCalendarHeader-switchViewIcon")
    private WebElement selectYear;

    @FindBy(css = ".MuiPickersYear-root:nth-child(126) > .MuiPickersYear-yearButton")
    private WebElement year;

    @FindBy(css = ".MuiIconButton-edgeStart > .MuiSvgIcon-root")
    private WebElement month;

    @FindBy(css = ".MuiDayCalendar-weekContainer:nth-child(4) > .MuiButtonBase-root:nth-child(3)")
    private WebElement day;

    @FindBy(id = "cvvInput")
    private WebElement cvvInput;

    @FindBy(id = "nameOnCardInput")
    private WebElement nameOnCardInput;
    
    @FindBy(id = "purchaseButton")
    private WebElement purchaseButton;

    @FindBy(css = "body")
    private WebElement body; // needed to click outside the select, else the select remains open and the tests fail

    public PayPageObject(WebDriver driver) {
        driver.get("http://localhost:3030/");
        PageFactory.initElements(driver, this);
    }

    public WebElement getCurrencySelect() {
        return currencySelect;
    }

    public WebElement getCoincurrencyUSD() {
        return coincurrencyUSD;
    }

    public WebElement getNameInput() {
        return nameInput;
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getAddressInput() {
        return addressInput;
    }

    public WebElement getCityInput() {
        return cityInput;
    }

    public WebElement getZipCodeInput() {
        return zipCodeInput;
    }

    public WebElement getCreditCardTypeInput() {
        return creditCardTypeInput;
    }

    public WebElement getCardMaestro() {
        return cardMaestro;
    }

    public WebElement getCreditCardNumberInput() {
        return creditCardNumberInput;
    }

    public WebElement getYear() {
        return year;
    }

    public WebElement getMonth() {
        return month;
    }

    public WebElement getDay() {
        return day;
    }

    public WebElement getCvvInput() {
        return cvvInput;
    }

    public WebElement getNameOnCardInput() {
        return nameOnCardInput;
    }

    public WebElement getPurchaseButton() {
        return purchaseButton;
    }

    public void purchase() {
        purchaseButton.click();
    }

    public void setName(String name) {
        nameInput.click();
        nameInput.sendKeys(name);
    }

    public void setEmail(String email) {
        emailInput.click();
        emailInput.sendKeys(email);
    }

    public void setAddress(String address) {
        addressInput.click();
        addressInput.sendKeys(address);
    }

    public void setCity(String city) {
        cityInput.click();
        cityInput.sendKeys(city);
    }

    public void setZipCode(String zipCode) {
        zipCodeInput.click();
        zipCodeInput.sendKeys(zipCode);
    }

    public void setCreditCardNumber(String creditCardNumber) {
        creditCardNumberInput.click();
        creditCardNumberInput.sendKeys(creditCardNumber);
    }

    public void setCvv(String cvv) {
        cvvInput.click();
        cvvInput.sendKeys(cvv);
    }

    public void setNameOnCard(String nameOnCard) {
        nameOnCardInput.click();
        nameOnCardInput.sendKeys(nameOnCard);
    }

    public void selectCurrency() {
        currencySelect.click();
        coincurrencyUSD.click();
        body.click();
    }

    public void selectCreditCardType() {
        creditCardTypeInput.click();
        cardMaestro.click();
        body.click();
    }

    public void selectDate() {
        calendar.click();
        selectYear.click();
        year.click();
        month.click();
        day.click();
    }
}
