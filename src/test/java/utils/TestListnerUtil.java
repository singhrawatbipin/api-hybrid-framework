package utils;

import utils.ExtentManagerUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.*;

public class TestListnerUtil implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {
		ExtentManagerUtil.initReport();
		extent = ExtentManagerUtil.getExtent();
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentManagerUtil.flushReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		testThread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testThread.get().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		testThread.get().fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testThread.get().skip("Test Skipped");
	}

	public static void addLogToReport(String log) {
		if (testThread.get() != null) {
			testThread.get().info("<pre>" + log + "</pre>");
		}
	}

}
