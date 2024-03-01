package qtriptest;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {

    public static boolean enterText(WebElement inputBox, String keysToSend) {
        try {
            inputBox.clear();
            inputBox.sendKeys(keysToSend);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static boolean clickAction(WebElement btn, WebDriver driver) {
        if(btn.isDisplayed()) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true)", btn);
                btn.click();
                Thread.sleep(2000);
                return true;
            } catch(Exception e) {
                return false;
            }
        }
        return false;
    }

    public static boolean navigateToUrl(String url, WebDriver driver) {
        try {
            if(driver.getCurrentUrl() != url) {
                driver.get(url);
                Thread.sleep(2000);
                return true;
            }
        }catch(Exception e) {
            return false;
        }
        return false;
    }

    public static String captureScreenshot(WebDriver driver) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "/QTRIPImages/" + System.currentTimeMillis() + ".png");
        FileUtils.copyFile(srcFile, dest);
        String errfilePath = dest.getAbsolutePath();
        return errfilePath;


    }




    
}
