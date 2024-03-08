package pt.ua.deti;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumJupiter.class)
public class TravelAgencyTest {
    private static TravelAgencyPage travelAgencyPage;
    private static WebDriver driver;
    private static final String URL = "https://blazedemo.com/";

    @BeforeAll
    public static void setUp() {
        driver = new FirefoxDriver();
        travelAgencyPage = new TravelAgencyPage(driver);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void travelAgencyTest() {
        driver.get(URL);
        driver.manage().window().setSize(new Dimension(1800, 1000));

        travelAgencyPage.clickFromPort();
        travelAgencyPage.setFromPort("Mexico City");

        travelAgencyPage.clickToPort();
        travelAgencyPage.setToPort("London");

        travelAgencyPage.findFlights();
        travelAgencyPage.chooseFlight();

        travelAgencyPage.clickInputName();
        travelAgencyPage.setInputName("José Mendes");

        travelAgencyPage.clickInputAddress();
        travelAgencyPage.setInputAddress("123 Coiso");

        travelAgencyPage.clickInputCity();
        travelAgencyPage.setInputCity("FAFEE");

        travelAgencyPage.clickInputState();
        travelAgencyPage.setInputState("Braga");

        travelAgencyPage.clickInputZipCode();
        travelAgencyPage.setInputZipCode("4820");

        travelAgencyPage.clickInputCreditCardNumber();
        travelAgencyPage.clickInputCreditCardNumber();
        travelAgencyPage.setInputCreditCardNumber("123123123");

        travelAgencyPage.clickInputCreditCardMonth();
        travelAgencyPage.clickInputCreditCardYear();

        travelAgencyPage.clickInputNameOnCard();
        travelAgencyPage.setInputNameOnCard("José Mendes");

        travelAgencyPage.purchase();

        assertTrue(travelAgencyPage.isConfirmationPage());
    }
}
