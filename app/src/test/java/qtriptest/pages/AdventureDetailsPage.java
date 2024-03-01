package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventureDetailsPage {
    RemoteWebDriver driver;

    @FindBy(xpath = "(//form[@id='myForm']//input)[1]")
    private WebElement nameTextBox;

    @FindBy(xpath = "//input[@type='date']")
    private WebElement dateTextBox;

    @FindBy(xpath = "//input[@type='number']")
    private WebElement numberTextBox;

    @FindBy(xpath = "//button[text()='Reserve']")
    private WebElement reserveButton;

    @FindBy(xpath = "//strong[text()='here']")
    private WebElement clickherebutton;

    public AdventureDetailsPage(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);
    }

    public Boolean enterName(String name) {
        Boolean status = false;
        try {
            // nameTextBox.clear();
            // nameTextBox.sendKeys(name);
            SeleniumWrapper.enterText(nameTextBox, name);
            Thread.sleep(2000);
            status = true;
            return status;
        }catch(Exception e) {
            System.out.println("Exception occured while entering Name: " +e.getMessage());
            return status;
        }
    }

    public Boolean enterDate(String date) {
        Boolean status = false;
        try {
            // dateTextBox.clear();
            // dateTextBox.sendKeys(date);
            SeleniumWrapper.enterText(dateTextBox, date);
            Thread.sleep(2000);
            status = true;
            return status;
        }catch(Exception e) {
            System.out.println("Exception occured while entering date: " +e.getMessage());
            return status;
        }
    }

    public Boolean enterPersons(String number) {
        Boolean status = false;
        try {
            // numberTextBox.clear();
            // numberTextBox.sendKeys(number);
            SeleniumWrapper.enterText(numberTextBox,number);
            Thread.sleep(2000);
            status = true;
            return status;
        }catch(Exception e) {
            System.out.println("Exception occured while entering Number: " +e.getMessage());
            return status;
        }
    }

    public Boolean clickReserveBtn() {
        Boolean status = false;
        try {
            //reserveButton.click();
            SeleniumWrapper.clickAction(reserveButton, driver);
            Thread.sleep(2000);
            status = true;
            return status;
        }catch(Exception e) {
            System.out.println("Exception occured while clicking reserve button: " +e.getMessage());
            return status;
        }
    }

    public Boolean clickHereBtn() {
        Boolean status = false;
        try {
            //clickherebutton.click();
            SeleniumWrapper.clickAction(clickherebutton, driver);
            Thread.sleep(2000);
            status = true;
            return status;
        }catch(Exception e) {
            System.out.println("Exception occured while clicking click here button: " +e.getMessage());
            return status;
        }
    }

}