package pt.ua.deti.backend.pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObject {
    @FindBy(id = "originSelect")
    private WebElement originSelect;

    @FindBy(id = "origin_Porto")
    private WebElement origin_Porto;

    @FindBy(id = "destSelect")
    private WebElement destSelect;

    @FindBy(id = "dest_Lisboa")
    private WebElement dest_Lisboa;

    @FindBy(css = ".MuiIconButton-edgeEnd > .MuiSvgIcon-root")
    private WebElement searchIcon;

    @FindBy(css = ".MuiDayCalendar-weekContainer:nth-child(4) > .MuiButtonBase-root:nth-child(7)")
    private WebElement searchDate;

    @FindBy(id = "searchButton")
    private WebElement searchButton;
    
    @FindBy(css = "body")
    private WebElement body; // needed to click outside the select, else the select remains open and the tests fail


    public HomePageObject(WebDriver driver) {
        driver.get("http://localhost:3030/");
        PageFactory.initElements(driver, this);
    }

    public WebElement getOriginSelect() {
        return originSelect;
    }

    public WebElement getOrigin_Porto() {
        return origin_Porto;
    }

    public WebElement getDestSelect() {
        return destSelect;
    }

    public WebElement getDest_Lisboa() {
        return dest_Lisboa;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public void search() {
        searchButton.click();
    }

    public void selectOrigin() {
        originSelect.click();
        origin_Porto.click();
        body.click();
    }

    public void selectDestination() {
        destSelect.click();
        dest_Lisboa.click();
        body.click();
    }

    public void selectDate() {
        searchIcon.click();
        searchDate.click();
    }
}
