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
			
			System.out.println(":: ExtentManagerUtil :: extent = new ExtentReports();");
			extent = new ExtentReports();
			
			System.out.println(":: ExtentManagerUtil :: extent.attachReporter(spark);");
			extent.attachReporter(spark);
			
		}
	}

	public static ExtentReports getExtent() {
		System.out.println(":: ExtentManagerUtil :: getExtent(()");
		return extent;
	}

	public static void flushReport() {
		if (extent != null) {
			extent.flush();
		}
	}
}
