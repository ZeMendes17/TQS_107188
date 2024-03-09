package pt.ua.deti;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.github.bonigarcia.seljup.BrowserType.CHROME;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumJupiter.class)
public class TravelAgencyDockerBrowserTest {
    private static TravelAgencyPage travelAgencyPage;
    private static final String URL = "https://blazedemo.com/";

    @Test
    public void travelAgencyDockerBrowserTest(@DockerBrowser(type = CHROME)RemoteWebDriver driver) {
        travelAgencyPage = new TravelAgencyPage(driver);
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
