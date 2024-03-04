package qtriptest;

import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.remote.RemoteWebDriver;
// import com.relevantcodes.extentreports.ExtentReports;
public class ReportSingleton {
    private static ReportSingleton instanceOfSingletonReport = null;
    private ExtentReports report;

    private ReportSingleton() {
        report = new ExtentReports(System.getProperty("user.dir") + "/report" + getTimestamp() + ".html", true);

    }

    private String getTimestamp() {
        // LocalDateTime currentTime = LocalDateTime.now();
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String formattedTime = currentTime.format(formatter);
        // // System.out.println("[" + formattedTime + "] " + message);
        // return "[" + formattedTime + "] " + message;\
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp.getTime());
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