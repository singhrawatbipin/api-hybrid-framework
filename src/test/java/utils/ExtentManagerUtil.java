package utils;

import constants.FrameworkConstants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentManagerUtil {
	private static ExtentReports extent;

	public static void initReport() {
		if (extent == null) {
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.EXTENT_REPORT_PATH);
			spark.config().setReportName("API Automation Report");
			spark.config().setDocumentTitle("API Test Results");
			extent = new ExtentReports();
			extent.attachReporter(spark);
		}
	}

	public static ExtentReports getExtent() {
		return extent;
	}

	public static void flushReport() {
		if (extent != null) {
			extent.flush();
		}
	}
}
