package qtriptest.pages;

import org.openqa.selenium.WebElement;
import qtriptest.SeleniumWrapper;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    @FindBy(xpath = "//a[@class='nav-link login register']")
    private WebElement registerbtn;

    @FindBy(xpath = "//div[text()='Logout']")
    private WebElement logoutbtn;

    @FindBy(id = "autocomplete") 
    private WebElement searchBox;

    @FindBy(xpath = "//h5[text()='No City found']")
    private WebElement noCityFound;

    @FindBy(xpath = "//div[@class='tile']")
    private List<WebElement> searchResults;

    @FindBy(id = "results")
    private WebElement result;

    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory, this);
    }

    public void navigateToHomePage() throws InterruptedException {
        // if(driver.getCurrentUrl() != url){
        //     driver.get(url);
        //     Thread.sleep(4000);
        //     registerbtn.click();
        //     Thread.sleep(2000);
        // }
        SeleniumWrapper.navigateToUrl(url, driver);
        Thread.sleep(2000);
        SeleniumWrapper.clickAction(registerbtn, driver);
        Thread.sleep(2000);

    }

    public void navigateToHome() throws InterruptedException {
        SeleniumWrapper.navigateToUrl(url, driver);
        Thread.sleep(2000);
    }

    public Boolean searchForPlace(String place) {
        try {
            // searchBox.clear();
            // searchBox.sendKeys(place);
            SeleniumWrapper.enterText(searchBox, place);
            Thread.sleep(2000);
            return true;
        } catch(Exception e) {
            System.out.println("Error while searching place: " + e.getMessage());
            return false;
        }
    }

    public Boolean isNoResultFound() {
       Boolean status = false;
       try {
        status = noCityFound.isDisplayed();
        return status;
       }catch(Exception e) {
        return status;
       }

    }

    public List<WebElement> getSearchResults() {
        try {
            return searchResults;
        }catch(Exception e) {
            System.out.println("No search Results: " + e.getMessage());
            return searchResults;
        }
        
    }

    public void clickSearchResult() {
        //result.click();
        SeleniumWrapper.clickAction(result, driver);
    }

    public void clickOnLogout() throws InterruptedException {
        //logoutbtn.click();
        SeleniumWrapper.clickAction(logoutbtn, driver);
        Thread.sleep(1000);
        
    }
}
