package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {
    static RemoteWebDriver driver;
    public static DriverSingleton instanceOfSingleton;

    private DriverSingleton() throws MalformedURLException {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities); // This line creates a new instance of RemoteWebDriver in each test class
        driver.manage().window().maximize();
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
    }

    public static DriverSingleton getInstanceOfSingletonBrowserClass() throws MalformedURLException {
        if(instanceOfSingleton == null) 
            instanceOfSingleton = new DriverSingleton();
        return instanceOfSingleton;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

}
