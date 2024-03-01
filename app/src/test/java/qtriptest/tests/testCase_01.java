package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.SeleniumWrapper;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
// import org.apache.logging.log4j.core.util.Assert;
//import java.net.URL;
//import org.apache.logging.log4j.core.util.Assert;
//import org.openqa.selenium.remote.BrowserType;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
// import org.testng.annotations.AfterTest;
// import org.testng.annotations.BeforeClass;
// import org.testng.annotations.BeforeSuite;
// import org.testng.annotations.BeforeTest;
// import org.testng.annotations.Test;
// import org.testng.asserts.Assertion;


import org.testng.annotations.*;
import org.testng.Assert;


public class testCase_01 {

    static RemoteWebDriver driver;
    static ExtentReports report;
	static ExtentTest test;
	// Method to help us log our Unit Tests
	public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

	// Iinitialize webdriver for our Unit Tests
	@BeforeTest(alwaysRun = true, enabled = true)
	public static void createDriver() throws MalformedURLException {
		logStatus("driver", "Initializing driver", "Started");
		ReportSingleton rpt = ReportSingleton.getInstanceOfSingletonReportClass();
		report = rpt.getReport();
		DriverSingleton singleton = DriverSingleton.getInstanceOfSingletonBrowserClass();
    	driver = singleton.getDriver();
		logStatus("driver", "Initializing driver", "Success");
	}

	@Test(description = "Verify user registration, login, logout", dataProvider = "data-provider", dataProviderClass= DP.class, priority = 1, groups = {"Login Flow"}, enabled = true)
    public static void TestCase01(String userName, String password) throws InterruptedException, IOException {
		Boolean status;
		try {
			test = report.startTest("Verify user registration, login, logout");
			HomePage home = new HomePage(driver);
			home.navigateToHomePage();
			Thread.sleep(2000);
			RegisterPage register = new RegisterPage(driver);
			register.navigateToHomePage();
			Thread.sleep(2000);
			status = driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
			Assert.assertTrue(status, "User Navigated to register page Successfully");
			// if(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/pages/register/")) {
			// 	logStatus("Page Test", "User Navigated to register page Successfully", "Success");
			// } else {
			// 	logStatus("Page Test", "User doesnot Navigated to register page", "Failure");
			// }
			System.out.println("after navigating");
			System.out.println("before registration");
			status = register.registerUser(userName, password,  true);
			if(status) {
				//logStatus("PageTest", "User Registerd Successfully", "Success");
				test.log(LogStatus.PASS, "User Registerd Successfully");
			} else {
				//logStatus("PageTest", "User not Registerd ", "Failure");
				test.log(LogStatus.FAIL, test.addScreenCapture(SeleniumWrapper.captureScreenshot(driver)) + "User not Registerd");
			}
			String lastGeneratedUserName = register.user_email; 

			LoginPage login = new LoginPage(driver);
			status = login.loginUser(lastGeneratedUserName, password);
			if(status) {
				//logStatus("PageTest", "User Logged in Successfully", "Success");
				test.log(LogStatus.PASS, "User Logged in Successfully");
			} else {
				//logStatus("PageTest", "User doesn't logged in", "Failue");
				test.log(LogStatus.FAIL, test.addScreenCapture(SeleniumWrapper.captureScreenshot(driver)) +"User doesn't logged in");
			}

			home.clickOnLogout();
			// logStatus("Page Test", "User Logged out Successfully", "Success");
			test.log(LogStatus.PASS, "User Logged out Successfully");
			
		} catch(Exception e) {
			e.printStackTrace();
			//logStatus("Page Test", "TestCase1 validation", "Failure");
			test.log(LogStatus.PASS, test.addScreenCapture(SeleniumWrapper.captureScreenshot(driver)) +"TestCase1 validation Failure");
		}
	}

	
	@AfterTest(enabled = true)
	public void quitDriver() {
		//driver.close();
		driver.quit();
		//logStatus("Page Test", "Quitting Driver", "Success");
		report.close();
		report.flush();
		test.log(LogStatus.PASS, "Quitting Driver");
	}


}
