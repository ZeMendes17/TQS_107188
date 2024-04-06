package pt.ua.deti.backend;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;
import pt.ua.deti.backend.pageObjects.HomePageObject;
import pt.ua.deti.backend.pageObjects.PayPageObject;
import pt.ua.deti.backend.pageObjects.ReservationPageObject;
import pt.ua.deti.backend.pageObjects.TripsPageObject;

@ExtendWith(SeleniumJupiter.class)
public class SeleniumTestIT {
    private static HomePageObject homePageObject;
    private static TripsPageObject tripsPageObject;
    private static PayPageObject payPageObject;
    private static ReservationPageObject reservationPageObject;
    private static final String URL = "http://localhost:3030/";

    @BeforeAll
    public static void setup() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Test
    public void test(FirefoxDriver driver) {
        
        homePageObject = new HomePageObject(driver);
        tripsPageObject = new TripsPageObject(driver);
        payPageObject = new PayPageObject(driver);
        reservationPageObject = new ReservationPageObject(driver);

        driver.get(URL);
        
        // home page
        homePageObject.selectOrigin();
        homePageObject.selectDestination();
        homePageObject.selectDate();
        homePageObject.search();

        // trips page
        tripsPageObject.buyTrip();

        // pay page
        payPageObject.selectCurrency();
        payPageObject.setName("Zé Mendes");
        payPageObject.setEmail("mendes.j@ua.pt");
        payPageObject.setAddress("Rua do Zé");
        payPageObject.setCity("Fafe");
        payPageObject.setZipCode("4820");
        payPageObject.selectCreditCardType();
        payPageObject.setCreditCardNumber("1234567890123456");
        payPageObject.selectDate();
        payPageObject.setCvv("123");
        payPageObject.setNameOnCard("José Mendes");
        payPageObject.purchase();

        // reservation page
        reservationPageObject.checkReservations();
        reservationPageObject.setEmailInput("mendes.j@ua.pt");
        reservationPageObject.setTokenInput("PO-LI-004_A1");
        reservationPageObject.findReservation();
    }
}
