package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	static ExtentReports extent;

	public static ExtentReports getReporter() {
		if (extent == null) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/index.html");
			spark.config().setDocumentTitle("Automation Report");
			spark.config().setReportName("Test Report Details");
			spark.config().setTheme(Theme.DARK);
			extent.attachReporter(spark);

			// System Info
			extent.setSystemInfo("Machine", "01");
			extent.setSystemInfo("User", "Aditya Paul");
			extent.setSystemInfo("Role", "Test Engineer");
			extent.setSystemInfo("OS", "Windows 11");
			extent.setSystemInfo("Browser", "Chrome");
			extent.setSystemInfo("Mode", "Incognito");
			extent.setSystemInfo("IDE", "Eclipse");
			extent.setSystemInfo("Language", "JAVA");
			extent.setSystemInfo("Framework", "Selenium");
		}

		return extent;
	}
}
