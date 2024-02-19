package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    RemoteWebDriver driver;
    //Assertion assert = new Assertion();
    //WebDriverWait wait = new WebDriverWait(driver, 20);

    @FindBy(id = "floatingInput")
    private WebElement emailTextBox;
    
    @FindBy(id = "floatingPassword")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-login']")
    private WebElement loginBtn;

    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);
    }


    public Boolean loginUser(String email, String password) throws InterruptedException{
        emailTextBox.clear();
        emailTextBox.sendKeys(email);
        Thread.sleep(2000);

        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
        Thread.sleep(2000);

        loginBtn.click();
        Thread.sleep(2000);

        return driver.getCurrentUrl().contains("qtripdynamic");
        
    }
}
