package qtriptest.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
// import org.testng.annotations.AfterTest;
// import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.testng.annotations.*;
import org.testng.Assert;


public class testCase_02 {

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
		logStatus("Driver", "Start Driver2", "Success");

    }


    @Test(description = "Verify search flow", dataProvider = "data-provider", dataProviderClass= DP.class, priority = 2, groups = {"Search and Filter Flow"}, enabled = true)
    public void TestCase02(String cityName, String category_Filter, String durationFilter, String expectedFilterResults, String expectedUnfilterdResults) throws InterruptedException {
        System.out.println(".......................................................................");
        Boolean status=true;
        try {
            HomePage home = new HomePage(driver);
            home.navigateToHome();

           //finding place with invalid name
           status = home.searchForPlace(cityName);
           

           if(status) {
            logStatus("Testcase2", "unable to find place with this name ", "PASS");
           }

           List<WebElement> searchResults = home.getSearchResults();
           //searchResults = home.getSearchResults();
            if (searchResults.size() == 0) {
                if (home.isNoResultFound()) {
                    logStatus("Step Success", "Successfully validated that no products found message is displayed", "PASS");
                } else {
                    logStatus("TestCase 2", "Test Case Fail. Expected: no results , actual: Results were available", "FAIL");
                }
            }

            status = home.searchForPlace(cityName);
            if (!status) {
                logStatus("TestCase 2", "Test Case Failure. Unable to search for given product", "FAIL");
                //return false;
            }

            searchResults = home.getSearchResults();
            if(searchResults.size() != 0) {
                home.clickSearchResult();
                logStatus("TestCase 2", "Test Case Pass. Successfully clicked the place btn", "PASS");
            } else {
                //home.clickSearchResult();
                logStatus("TestCase 2", "Test Case Failure. There were no results for the given search string", "FAIL");
            }

            AdventurePage adventure = new AdventurePage(driver);

            adventure.addCategoryDropdown(category_Filter);
            logStatus("Testcase2", "category_filter was selected from category dropdown", "PASS");

            adventure.hourdurationDropdown(durationFilter);
            logStatus("Testcase2", "duration filter was selected from duration dropdown", "PASS");

            List<WebElement> adventureResultswithFilters = adventure. getAdventureResultsWithFilters(expectedFilterResults);
            int count = adventureResultswithFilters.size();
            if(adventureResultswithFilters.size() != 0) {
                //System.out.println(count);
                System.out.println("Number of elements Expected:" +expectedFilterResults);
                System.out.println("Number of Elements Found:" + count);
            }
            

            adventure.clearFilter();
            logStatus("Testcase2", "cleared filter btn", "PASS");
            adventure.clearCategory();
            logStatus("Testcase2", "cleared Category btn", "PASS");


            List<WebElement> adventureResultswithoutFilters = adventure.getAdventureResultsWithNoFilters(expectedUnfilterdResults);
            int count2 = adventureResultswithoutFilters.size();
            if(adventureResultswithoutFilters.size() != 0) {
                //System.out.println(count2);
                System.out.println("Number of elements Expected:" +expectedUnfilterdResults);
                System.out.println("Number of Elements Found:" + count2);
            }

        }catch(Exception e) {
            e.printStackTrace();
			logStatus("Page Test", "TestCase2 validation", "Failure");
        }        
    }

    @AfterSuite(enabled = true)
	public void quitDriver() {
		// driver.close();
		driver.quit();
		logStatus("Page Test", "Quitting Driver2", "Success");
	}

}
