package qtriptest.pages;

import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegisterPage {
    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";

    //WebDriverWait wait = new WebDriverWait(driver, 20);
    
    @FindBy(name = "email")
    private WebElement emailTextBox;

    @FindBy(name = "password")
    private WebElement passwordTextBox;

    @FindBy(name = "confirmpassword")
    private WebElement confirmpasswordTextBox;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-login']")
    private WebElement registerBtn;

 
    public RegisterPage(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);
    }

    public void navigateToHomePage() throws InterruptedException {
        if(driver.getCurrentUrl() != url){
            driver.get(url);
        }
    }

    String emailAddress = "";

    public String user_email = "";

    public Boolean registerUser(String email, String password, Boolean makeUserDynamic) throws InterruptedException {
        // driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
        System.out.println("in side registration");
        if(makeUserDynamic) {
            emailAddress = email.split("@")[0] + UUID.randomUUID().toString() + "@" + email.split("@")[1];
        } else {
            emailAddress = email;
        }

        // WebElement emailTextBox = driver.findElement(By.xpath("//input[@placeholder ='name@example.com']"));
        // wait.until(ExpectedConditions.visibilityOf(emailTextBox));
        emailTextBox.clear();
        emailTextBox.sendKeys(emailAddress);
        Thread.sleep(2000);

        //WebElement passwordTextBox = driver.findElement(By.xpath("//input[@placeholder ='Type to create account password']"));
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
        Thread.sleep(2000);

        //WebElement  confirmpasswordTextBox= driver.findElement(By.xpath("//input[@placeholder ='Retype Password to Confirm']"));
        confirmpasswordTextBox.clear();
        confirmpasswordTextBox.sendKeys(password);
        Thread.sleep(2000);


        //WebElement registerBtn= driver.findElement(By.xpath("//button[@class='btn btn-primary btn-login']"));
        registerBtn.click();
        Thread.sleep(3000);

        this.user_email = emailAddress;
        return this.driver.getCurrentUrl().endsWith("/login");
        
        
    }
}
