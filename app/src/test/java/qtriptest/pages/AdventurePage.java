package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {
    RemoteWebDriver driver;

    @FindBy(id = "duration-select")
    private WebElement durationDropdown;

    @FindBy(id = "category-select")
    private WebElement categoryDropdown;

    @FindBy(id = "search-adventures")
    private WebElement adventureTextbox;

    @FindBy(xpath = "(//select[@class='form-control']/following-sibling::div)[1]")
    private WebElement clearFilterBtn;

    @FindBy(xpath = "(//select[@class='form-control']/following-sibling::div)[2]")
    private WebElement clearCategoryBtn;

    @FindBy(xpath = "//div[contains(@class,'col-6 col-lg-3')]//a")
    private List<WebElement> adventureResults;

    @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    private WebElement advResult;



    public AdventurePage(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);
    }

    public void hourdurationDropdown(String duration) throws InterruptedException {
        Select hourdropdown = new Select(durationDropdown);
        hourdropdown.selectByVisibleText(duration);
        Thread.sleep(2000);
    }

    public void addCategoryDropdown(String category) throws InterruptedException {
        Select catDropdown = new Select(categoryDropdown);
        catDropdown.selectByVisibleText(category);
        Thread.sleep(2000);

    }

    public void addAdventureTextBox(String adventure) throws InterruptedException {
        adventureTextbox.sendKeys(adventure);
        Thread.sleep(2000);

    }

    public Boolean clearFilter() {
        Boolean status = false;
        try {
            clearFilterBtn.click();
            Thread.sleep(2000);
            status = true;
            return status;
        } catch(Exception e) {
            System.out.println("Exception occured while clicking clear filter btn: " +e.getMessage());
            return status;
        }
    }

    public Boolean clearCategory() {
        Boolean status = false;
        try {
            clearCategoryBtn.click();
            Thread.sleep(2000);
            status = true;
            return status;
        } catch(Exception e) {
            System.out.println("Exception occured while clicking clear category btn: " +e.getMessage());
            return status;
        }
    }

    public List<WebElement> getAdventureResultsWithFilters(String expectedFilterResults) {
        try {
            return adventureResults;
        }catch(Exception e) {
            System.out.println("No adventure Results with filters: " + e.getMessage());
            return adventureResults;
        }
        
    }

    public List<WebElement> getAdventureResultsWithNoFilters(String expectedUnfilterdResults) {
        try {
            return adventureResults;
        }catch(Exception e) {
            System.out.println("No adventure Results without filters: " + e.getMessage());
            return adventureResults;
        }
        
    }

    public Boolean clickAdventureResult(String adventure) {
        Boolean status = false;
        try {
            advResult.click();
            Thread.sleep(2000);
            status = true;
            return status;
        } catch(Exception e) {
            System.out.println("Exception occured while clicking clear category btn: " +e.getMessage());
            return status;
        }
    }




}
