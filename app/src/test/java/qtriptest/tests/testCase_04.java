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

public class testCase_04 {
    static RemoteWebDriver driver;
    static ExtentReports report;
    static ExtentTest test;

	// Method to help us log our Unit Tests
	public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

	// Iinitialize webdriver for our Unit Tests
	@BeforeSuite(alwaysRun = true)
    public void createDriver() throws MalformedURLException, InterruptedException{
        logStatus("driver", "Initializing driver", "Started");
        ReportSingleton rpt = ReportSingleton.getInstanceOfSingletonReportClass();
		report = rpt.getReport();
		DriverSingleton singleton = DriverSingleton.getInstanceOfSingletonBrowserClass();
    	driver = singleton.getDriver();
        Thread.sleep(5000);
		logStatus("Driver", "Start Driver4", "Success");

    }

    @Test(description = "Verify Bookings", dataProvider = "data-provider", dataProviderClass= DP.class,priority = 4, groups = {"Reliabilty Flow"}, enabled = true)
    public void TestCase04(String userName, String password, String dataset1, String dataset2, String dataset3) throws InterruptedException, IOException {
        System.out.println(".......................................................................");
        Boolean status=true;
        String[] ds1 = dataset1.split(";");
        String[] ds2 = dataset2.split(";");
        String[] ds3 = dataset3.split(";");

        try {
            test = report.startTest("Verify Bookingsin testcase4");
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

            //for dataset1
            home.searchForPlace(ds1[0]);
            if (!status) {
               // logStatus("TestCase 4", "Test Case Failure. Unable to search for given place", "FAIL");
               test.log(LogStatus.PASS, "Unable to search for given place");
            }

            List<WebElement> searchResults = home.getSearchResults();
            
            searchResults = home.getSearchResults();
            if(searchResults.size() != 0) {
                home.clickSearchResult();
                //logStatus("TestCase 4", "Test Case Pass. Successfully clicked the place btn", "PASS");
                test.log(LogStatus.PASS, "Successfully clicked the place btn");
            } else {
                //home.clickSearchResult();
               // logStatus("TestCase 4", "Test Case Failure. There were no results for the given search string", "FAIL");
               test.log(LogStatus.FAIL, test.addScreenCapture(SeleniumWrapper.captureScreenshot(driver)) +"There were no results for the given search string");
            }

            AdventurePage adventure = new AdventurePage(driver);
            adventure.addAdventureTextBox(ds1[1]);
            //logStatus("Testcase4", "Adventure name is sent in text box", "PASS");
            test.log(LogStatus.PASS, "Adventure name is sent in text box");

            adventure.clickAdventureResult(ds1[1]);
            //logStatus("Testcase4","Clicked adventure result", "PASS");
            test.log(LogStatus.PASS, "Clicked adventure result");


            AdventureDetailsPage adDetails = new AdventureDetailsPage(driver);
            adDetails.enterName(ds1[2]);
            //logStatus("Testcase4", "name is sent in text box", "PASS");
            test.log(LogStatus.PASS, "name is sent in text box");

            adDetails.enterDate(ds1[3]);
            //logStatus("Testcase4", "date is sent in text box", "PASS");
            test.log(LogStatus.PASS, "date is sent in text box");

            adDetails.enterPersons(ds1[4]);
            //logStatus("Testcase4", "Number is sent in text box", "PASS");
            test.log(LogStatus.PASS, "Number is sent in text box");

            adDetails.clickReserveBtn();
            //logStatus("Testcase4", "Clicked Reserve Button", "PASS");
            test.log(LogStatus.PASS, "Clicked Reserve Button");

            adDetails.clickHereBtn();
            //logStatus("Testcase4", "Clicked Click here Button", "PASS");
            test.log(LogStatus.PASS, "Clicked Click here Button");

            HistoryPage history = new HistoryPage(driver);
            //history.getTransactionId();
            List<WebElement> transids = history.idhistoryResults();
            int count1 = transids.size();
            if(count1 != 0) {
                System.out.println("Number of Elements Found before refreshing page:" + count1);
            }

            //for dataset2
            home.navigateToHome();
            Thread.sleep(3000);
            home.searchForPlace(ds2[0]);
            if (!status) {
                //logStatus("TestCase 4", "Test Case Failure. Unable to search for given place", "FAIL");
                test.log(LogStatus.PASS, "Unable to search for given place");
            }

            searchResults = home.getSearchResults();
            
            searchResults = home.getSearchResults();
            if(searchResults.size() != 0) {
                home.clickSearchResult();
                //logStatus("TestCase 4", "Test Case Pass. Successfully clicked the place btn", "PASS");
                test.log(LogStatus.PASS, "Successfully clicked the place btn");
            } else {
                //home.clickSearchResult();
                //logStatus("TestCase 4", "Test Case Failure. There were no results for the given search string", "FAIL");
                test.log(LogStatus.FAIL, test.addScreenCapture(SeleniumWrapper.captureScreenshot(driver)) +"There were no results for the given search string");
            }

            //AdventurePage adventure = new AdventurePage(driver);
            adventure.addAdventureTextBox(ds2[1]);
            //logStatus("Testcase4", "Adventure name is sent in text box", "PASS");
            test.log(LogStatus.PASS, "Adventure name is sent in text box");

            adventure.clickAdventureResult(ds2[1]);
            //logStatus("Testcase4","Clicked adventure result", "PASS");
            test.log(LogStatus.PASS, "Clicked adventure result");

            adDetails.enterName(ds2[2]);
            //logStatus("Testcase4", "name is sent in text box", "PASS");
            test.log(LogStatus.PASS, "name is sent in text box");

            adDetails.enterDate(ds2[3]);
            //logStatus("Testcase4", "date is sent in text box", "PASS");
            test.log(LogStatus.PASS, "date is sent in text box");

            adDetails.enterPersons(ds2[4]);
            //logStatus("Testcase4", "Number is sent in text box", "PASS");
            test.log(LogStatus.PASS, "Number is sent in text box");

            adDetails.clickReserveBtn();
            //logStatus("Testcase4", "Clicked Reserve Button", "PASS");
            test.log(LogStatus.PASS, "Clicked Reserve Button");

            adDetails.clickHereBtn();
            //logStatus("Testcase4", "Clicked Click here Button", "PASS");
            test.log(LogStatus.PASS, "Clicked Click here Button");

            //HistoryPage history = new HistoryPage(driver);
            //history.getTransactionId();
            transids = history.idhistoryResults();
            int count2 = transids.size();
            if(count2 != 0) {
                System.out.println("Number of Elements Found before refreshing page:" + count2);
            }

            //for dataset3
            home.navigateToHome();
            Thread.sleep(3000);
            home.searchForPlace(ds3[0]);
            if (!status) {
                //logStatus("TestCase 4", "Test Case Failure. Unable to search for given place", "FAIL");
                test.log(LogStatus.FAIL, test.addScreenCapture(SeleniumWrapper.captureScreenshot(driver)) +"Unable to search for given place");
            }

            searchResults = home.getSearchResults();
            
            searchResults = home.getSearchResults();
            if(searchResults.size() != 0) {
                home.clickSearchResult();
                //logStatus("TestCase 4", "Test Case Pass. Successfully clicked the place btn", "PASS");
                test.log(LogStatus.PASS, "Successfully clicked the place btn");
            } else {
                //home.clickSearchResult();
                //logStatus("TestCase 4", "Test Case Failure. There were no results for the given search string", "FAIL");
                test.log(LogStatus.FAIL, test.addScreenCapture(SeleniumWrapper.captureScreenshot(driver)) +"There were no results for the given search string");
            }

            //AdventurePage adventure = new AdventurePage(driver);
            adventure.addAdventureTextBox(ds3[1]);
            //logStatus("Testcase4", "Adventure name is sent in text box", "PASS");
            test.log(LogStatus.PASS, "Adventure name is sent in text box");

            adventure.clickAdventureResult(ds3[1]);
            //logStatus("Testcase4","Clicked adventure result", "PASS");
            test.log(LogStatus.PASS, "Clicked adventure result");

            adDetails.enterName(ds3[2]);
            //logStatus("Testcase4", "name is sent in text box", "PASS");
            test.log(LogStatus.PASS, "name is sent in text box");

            adDetails.enterDate(ds3[3]);
            //logStatus("Testcase4", "date is sent in text box", "PASS");
            test.log(LogStatus.PASS, "date is sent in text box");

            adDetails.enterPersons(ds3[4]);
            //logStatus("Testcase4", "Number is sent in text box", "PASS");
            test.log(LogStatus.PASS, "Number is sent in text box");

            adDetails.clickReserveBtn();
            //logStatus("Testcase4", "Clicked Reserve Button", "PASS");
            test.log(LogStatus.PASS, "Clicked Reserve Button");

            adDetails.clickHereBtn();
            //logStatus("Testcase4", "Clicked Click here Button", "PASS");
            test.log(LogStatus.PASS, "Clicked Click here Button");

            //HistoryPage history = new HistoryPage(driver);
            //history.getTransactionId();
            transids = history.idhistoryResults();
            int count3 = transids.size();
            if(count3 != 0) {
                System.out.println("Number of Elements Found before refreshing page:" + count3);
            }

            home.clickOnLogout();
            Thread.sleep(2000);
            //logStatus("TestCase 4", "Test Case Pass. Clicked on logout button", "PASS");
            test.log(LogStatus.PASS, "Clicked on logout button");

        }catch(Exception e) {
            e.printStackTrace();
			//logStatus("Page Test", "TestCase4 validation", "Failure");
            test.log(LogStatus.FAIL, test.addScreenCapture(SeleniumWrapper.captureScreenshot(driver)) +"TestCase4 validation");
        }
    }

    @AfterSuite
    public void quitDriver() {
 		driver.quit();
         report.close();
         report.flush();
 		//logStatus("Page Test", "Quitting Driver3", "Success");
         test.log(LogStatus.PASS, "Quitting Driver3");
    }
    
}
