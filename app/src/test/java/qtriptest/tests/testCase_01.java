package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import org.apache.logging.log4j.core.util.Assert;
//import java.net.URL;
//import org.apache.logging.log4j.core.util.Assert;
//import org.openqa.selenium.remote.BrowserType;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class testCase_01 {

    static RemoteWebDriver driver;

	// Method to help us log our Unit Tests
	public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

	// Iinitialize webdriver for our Unit Tests
	@BeforeTest(alwaysRun = true, enabled = true)
	public static void createDriver() throws MalformedURLException {
		logStatus("driver", "Initializing driver", "Started");
		DriverSingleton driverSingleton = new DriverSingleton();
    	driver = driverSingleton.getInstance();
		logStatus("driver", "Initializing driver", "Success");
	}

	@Test(description = "Verify user registratiaon, login, logout", dataProvider = "data-provider", dataProviderClass= DP.class, priority = 1, groups = {"Login Flow"}, enabled = true)
    public static void TestCase01(String userName, String password) throws InterruptedException {
		Boolean status;
		try {
			HomePage home = new HomePage(driver);
			home.navigateToHomePage();
			Thread.sleep(2000);
			RegisterPage register = new RegisterPage(driver);
			register.navigateToHomePage();
			Thread.sleep(2000);
			if(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/pages/register/")) {
				logStatus("Page Test", "User Navigated to register page Successfully", "Success");
			} else {
				logStatus("Page Test", "User doesnot Navigated to register page", "Failure");
			}
			System.out.println("after navigating");
			System.out.println("before registration");
			status = register.registerUser(userName, password,  true);
			if(status) {
				logStatus("PageTest", "User Registerd Successfully", "Success");
			} else {
				logStatus("PageTest", "User not Registerd ", "Failure");
			}
			String lastGeneratedUserName = register.user_email; 

			LoginPage login = new LoginPage(driver);
			status = login.loginUser(lastGeneratedUserName, password);
			if(status) {
				logStatus("PageTest", "User Logged in Successfully", "Success");
			} else {
				logStatus("PageTest", "User doesn't logged in", "Failue");
			}

			home.clickOnLogout();
			logStatus("Page Test", "User Logged out Successfully", "Success");
			
		} catch(Exception e) {
			e.printStackTrace();
			logStatus("Page Test", "TestCase1 validation", "Failure");
		}
	}

	
	@AfterTest(enabled = true)
	public void quitDriver() {
		driver.close();
		driver.quit();
		logStatus("Page Test", "Quitting Driver", "Success");
	}


}
