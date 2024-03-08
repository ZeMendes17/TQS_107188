package pt.ua.deti;

// Generated by Selenium IDE
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;


public class Selenium1sttestTest {
  private static WebDriver driver;
  private static Map<String, Object> vars;
  static JavascriptExecutor js;

  @BeforeAll
  public static void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @AfterAll
  public static void tearDown() {
    driver.quit();
  }

  @Test
  public void selenium1sttest() {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(1057, 801));
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Mexico City']")).click();
    }
    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'London']")).click();
    }
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("José Mendes");
    driver.findElement(By.id("address")).sendKeys("123 Coiso");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("FAFEE");
    driver.findElement(By.id("state")).click();
    driver.findElement(By.id("state")).sendKeys("Braga");
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("4820");
    driver.findElement(By.id("cardType")).click();
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).sendKeys("123123123");
    driver.findElement(By.id("creditCardMonth")).click();
    driver.findElement(By.id("creditCardYear")).click();
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("José Mendes");
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
  }
}
