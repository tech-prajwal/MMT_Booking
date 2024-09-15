package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import baseClasses.PageBase;

public class ExtentReport  {

	public static ExtentReports report;

	// creating report
	public static ExtentReports getReportInstance() {

		if (report == null) {
			String reportName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
					System.getProperty("user.dir") + "//Reports//" + reportName + ".html");
			report = new ExtentReports();
			report.attachReporter(htmlReporter);

			// setting system information
			report.setSystemInfo("OS", "Windows 10 Enterprise");
			report.setSystemInfo("Environment", "MakeMyTrip");
			report.setSystemInfo("Version", "95");
			report.setSystemInfo("Browser", "Chrome");

			htmlReporter.config().setDocumentTitle("MakeMyTrip");
			htmlReporter.config().setReportName("MakeMyTrip_Module");
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		}

		return report;
	}
}
