package pt.ua.deti;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class HelloWorldFirefoxSelJupTest {

    @Test
    void testWithFirefox(FirefoxDriver driver) {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }
}
