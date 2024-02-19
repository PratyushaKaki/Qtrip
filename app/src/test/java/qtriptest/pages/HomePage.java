package qtriptest.pages;

import org.openqa.selenium.WebElement;
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

    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);
    }

    public void navigateToHomePage() throws InterruptedException {
        if(driver.getCurrentUrl() != url){
            driver.get(url);
            // this.driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
            registerbtn.click();
            Thread.sleep(2000);
        }

    }

    public void clickOnLogout() throws InterruptedException {
        logoutbtn.click();
        Thread.sleep(1000);
        
    }
}
