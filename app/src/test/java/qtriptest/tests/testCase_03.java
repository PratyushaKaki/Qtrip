package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_03 {
    static RemoteWebDriver driver;

	// Method to help us log our Unit Tests
	public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

	// Iinitialize webdriver for our Unit Tests
	@BeforeSuite(alwaysRun = true, enabled = true)
    public void createDriver() throws MalformedURLException, InterruptedException{
        logStatus("driver", "Initializing driver", "Started");
        DriverSingleton driverSingleton = new DriverSingleton();
        driver = driverSingleton.getInstance(); 
        Thread.sleep(5000);
		logStatus("Driver", "Start Driver3", "Success");

    }

    @Test(description = "Verify Bookings", dataProvider = "data-provider", dataProviderClass= DP.class, priority = 3, groups = {"Booking and Cancellation Flow"}, enabled = true)
    public void TestCase03(String userName, String password, String city, String adventureName, String guestName, String date, String count) throws InterruptedException {
        System.out.println(".......................................................................");
        Boolean status=true;

        try {
            HomePage home = new HomePage(driver);
            home.navigateToHomePage();

            RegisterPage register = new RegisterPage(driver);
            register.navigateToHomePage();
			Thread.sleep(2000);
			if(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/pages/register/")) {
				logStatus("Page Test", "User Navigated to register page Successfully", "Success");
			} else {
				logStatus("Page Test", "User doesnot Navigated to register page", "Failure");
			}

            register.registerUser(userName, password,true);
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

            home.searchForPlace(city);
            if (!status) {
                logStatus("TestCase 3", "Test Case Failure. Unable to search for given place", "FAIL");
            }

            List<WebElement> searchResults = home.getSearchResults();
            
            searchResults = home.getSearchResults();
            if(searchResults.size() != 0) {
                home.clickSearchResult();
                logStatus("TestCase 3", "Test Case Pass. Successfully clicked the place btn", "PASS");
            } else {
                //home.clickSearchResult();
                logStatus("TestCase 3", "Test Case Failure. There were no results for the given search string", "FAIL");
            }

            AdventurePage adventure = new AdventurePage(driver);
            adventure.addAdventureTextBox(adventureName);
            logStatus("Testcase3", "Adventure name is sent in text box", "PASS");
            adventure.clickAdventureResult(adventureName);
            logStatus("Testcase3","Clicked adventure result", "PASS");

            AdventureDetailsPage adDetails = new AdventureDetailsPage(driver);
            adDetails.enterName(guestName);
            logStatus("Testcase3", "name is sent in text box", "PASS");
            adDetails.enterDate(date);
            logStatus("Testcase3", "date is sent in text box", "PASS");
            adDetails.enterPersons(count);
            logStatus("Testcase3", "Number is sent in text box", "PASS");
            adDetails.clickReserveBtn();
            logStatus("Testcase3", "Clicked Reserve Button", "PASS");
            adDetails.clickHereBtn();
            logStatus("Testcase3", "Clicked Click here Button", "PASS");

            HistoryPage history = new HistoryPage(driver);
            history.getTransactionId();
            List<WebElement> transids = history.idhistoryResults();
            int count1 = transids.size();
            if(count1 != 0) {
                System.out.println("Number of Elements Found before refreshing page:" + count1);
            }

            history.cancelBtn();
            logStatus("Testcase3", "Clicked cancel Button", "PASS");
            history.refreshPage();
            logStatus("Testcase3", "Page was Refreshed", "PASS");

            List<WebElement> transids2 = history.idhistoryResults();
            int count2 = transids2.size();
            if(count2 != 0) {
                System.out.println("Number of Elements Found after refreshing page:" + count2);
            }

            home.clickOnLogout();
            logStatus("TestCase 3", "Test Case Pass. Clicked on logout button", "PASS");

        }catch(Exception e) {
            e.printStackTrace();
			logStatus("Page Test", "TestCase3 validation", "Failure");
        }
    }

    @AfterSuite(enabled = true)
	public void quitDriver() {
		// driver.close();
		driver.quit();
		logStatus("Page Test", "Quitting Driver3", "Success");
	}

}
