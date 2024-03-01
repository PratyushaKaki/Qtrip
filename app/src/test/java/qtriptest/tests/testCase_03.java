package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.SeleniumWrapper;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import org.testng.Assert;

public class testCase_03 {
    static RemoteWebDriver driver;
    static ExtentReports report;
    static ExtentTest test;

	// Method to help us log our Unit Tests
	public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

	// Iinitialize webdriver for our Unit Tests
	@BeforeSuite(alwaysRun = true, enabled = true)
    public void createDriver() throws MalformedURLException, InterruptedException{
        logStatus("driver", "Initializing driver", "Started");
        ReportSingleton rpt = ReportSingleton.getInstanceOfSingletonReportClass();
		report = rpt.getReport();
		DriverSingleton singleton = DriverSingleton.getInstanceOfSingletonBrowserClass();
    	driver = singleton.getDriver();
        Thread.sleep(5000);
		logStatus("Driver", "Start Driver3", "Success");

    }

    @Test(description = "Verify Bookings", dataProvider = "data-provider", dataProviderClass= DP.class, priority = 3, groups = {"Booking and Cancellation Flow"}, enabled = true)
    public void TestCase03(String userName, String password, String city, String adventureName, String guestName, String date, String count) throws InterruptedException, IOException {
        System.out.println(".......................................................................");
        Boolean status=true;

        try {
            test = report.startTest("Verify Bookings");
            HomePage home = new HomePage(driver);
            home.navigateToHomePage();

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

            register.registerUser(userName, password,true);
            if(status) {
				//logStatus("PageTest", "User Registerd Successfully", "Success");
                test.log(LogStatus.PASS, "User Registerd Successfully");
			} else {
				//logStatus("PageTest", "User not Registerd ", "Failure");
                test.log(LogStatus.FAIL, test.addScreenCapture(SeleniumWrapper.captureScreenshot(driver)) +"User not Registerd");
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

            home.searchForPlace(city);
            if (!status) {
                //logStatus("TestCase 3", "Test Case Failure. Unable to search for given place", "FAIL");
                test.log(LogStatus.FAIL, "Unable to search for given place");
            }

            List<WebElement> searchResults = home.getSearchResults();
            
            searchResults = home.getSearchResults();
            if(searchResults.size() != 0) {
                home.clickSearchResult();
                //logStatus("TestCase 3", "Test Case Pass. Successfully clicked the place btn", "PASS");
                test.log(LogStatus.PASS, "Successfully clicked the place btn");
            } else {
                //logStatus("TestCase 3", "Test Case Failure. There were no results for the given search string", "FAIL");
                test.log(LogStatus.FAIL, test.addScreenCapture(SeleniumWrapper.captureScreenshot(driver)) +"There were no results for the given search string");
            }

            AdventurePage adventure = new AdventurePage(driver);
            adventure.addAdventureTextBox(adventureName);
            //logStatus("Testcase3", "Adventure name is sent in text box", "PASS");
            test.log(LogStatus.PASS, "Adventure name is sent in text box");

            adventure.clickAdventureResult(adventureName);
            //logStatus("Testcase3","Clicked adventure result", "PASS");
            test.log(LogStatus.PASS, "Clicked adventure result");

            AdventureDetailsPage adDetails = new AdventureDetailsPage(driver);
            adDetails.enterName(guestName);
            //logStatus("Testcase3", "name is sent in text box", "PASS");
            test.log(LogStatus.PASS, "name is sent in text box");

            adDetails.enterDate(date);
            //logStatus("Testcase3", "date is sent in text box", "PASS");
            test.log(LogStatus.PASS, "date is sent in text box");

            adDetails.enterPersons(count);
            //logStatus("Testcase3", "Number is sent in text box", "PASS");
            test.log(LogStatus.PASS, "Number is sent in text box");

            adDetails.clickReserveBtn();
            //logStatus("Testcase3", "Clicked Reserve Button", "PASS");
            test.log(LogStatus.PASS, "Clicked Reserve Button");

            adDetails.clickHereBtn();
            //logStatus("Testcase3", "Clicked Click here Button", "PASS");
            test.log(LogStatus.PASS, "Clicked Click here Button");

            HistoryPage history = new HistoryPage(driver);
            history.getTransactionId();
            List<WebElement> transids = history.idhistoryResults();
            int count1 = transids.size();
            if(count1 != 0) {
                System.out.println("Number of Elements Found before refreshing page:" + count1);
            }

            history.cancelBtn();
            //logStatus("Testcase3", "Clicked cancel Button", "PASS");
            test.log(LogStatus.PASS, "Clicked cancel Button");

            history.refreshPage();
            //logStatus("Testcase3", "Page was Refreshed", "PASS");
            test.log(LogStatus.PASS, "Page was Refreshed");

            List<WebElement> transids2 = history.idhistoryResults();
            int count2 = transids2.size();
            if(count2 != 0) {
                System.out.println("Number of Elements Found after refreshing page:" + count2);
            }

            home.clickOnLogout();
            //logStatus("TestCase 3", "Test Case Pass. Clicked on logout button", "PASS");
            test.log(LogStatus.PASS, "Clicked on logout button");

        }catch(Exception e) {
            e.printStackTrace();
			//logStatus("Page Test", "TestCase3 validation", "Failure");
            test.log(LogStatus.FAIL, test.addScreenCapture(SeleniumWrapper.captureScreenshot(driver)) +"TestCase3 validation");
        }
    }

    @AfterSuite(enabled = true)
	public void quitDriver() {
		driver.quit();
        report.close();
		report.flush();
		//logStatus("Page Test", "Quitting Driver3", "Success");
        test.log(LogStatus.PASS, "Quitting Driver3");
	}

}
