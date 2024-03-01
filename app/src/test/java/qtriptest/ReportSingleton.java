package qtriptest;

import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.remote.RemoteWebDriver;
// import com.relevantcodes.extentreports.ExtentReports;
public class ReportSingleton {
    private static ReportSingleton instanceOfSingletonReport = null;
    public RemoteWebDriver driver;
    private ExtentReports report;

    private ReportSingleton() {
        report = new ExtentReports(System.getProperty("user.dir") + "/report" + getTimestamp() + "html", true);

    }

    private String getTimestamp() {
        return null;
    }

    public static ReportSingleton getInstanceOfSingletonReportClass() throws MalformedURLException {
        if (instanceOfSingletonReport == null) {
            instanceOfSingletonReport = new ReportSingleton();
        }
        return instanceOfSingletonReport;
    }

    public ExtentReports getReport() {
        return report;
    }

}