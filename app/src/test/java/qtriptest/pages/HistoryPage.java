package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HistoryPage {

    RemoteWebDriver driver;
   // WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(xpath = "(//th[@scope='row'])[1]")
    private WebElement transid;

    @FindBy(id = "9ce593ab0e7c96a8")
    private WebElement cancelButton;

    @FindBy(id = "//th[@scope='row']")
    private List<WebElement> ids;

    public HistoryPage(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);
    }

    public String getTransactionId() {
        try {
            String idText = transid.getText();
            return idText;
        }catch(Exception e) {
            System.out.println("Exception occured while clicking click here button: " +e.getMessage());
            return null;
        }
    }

    public Boolean cancelBtn() {
        Boolean status = false;
        try {
            //wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
            cancelButton.click();
            Thread.sleep(2000);
            status = true;
            return status;
        }catch(Exception e) {
            System.out.println("Exception occured while clicking cancel button: " +e.getMessage());
            return status;
        }
    }

    public void refreshPage() {
        try {
            driver.navigate().refresh();
            Thread.sleep(4000);
        } catch(Exception e) {
            System.out.println("Exception occured while refreshing page: " +e.getMessage());
        }
    }

    public List<WebElement> idhistoryResults() {
        try {
            return ids;
        }catch(Exception e) {
            System.out.println("No adventure Results without filters: " + e.getMessage());
            return ids;
        }
    }

}