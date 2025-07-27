package base;

import config.ConfigLoader;
import constants.FrameworkConstants;
import io.restassured.RestAssured;
import specifications.RequestSpecBuilderUtil;
import specifications.ResponseSpecBuilderUtil;
import utils.ExtentManagerUtil;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeClass;
import utils.CustomLogFilter;

import org.testng.annotations.*;

public class BaseTest {

	@BeforeSuite
	public void beforeSuite() {
		// Load environment (default = qa)
		String env = System.getProperty("env", "qa");
		ConfigLoader.loadConfig(env);

		// Initialize Extent Report
		ExtentManagerUtil.initReport();
	}

	@BeforeClass
	public void setup() {
		RestAssured.requestSpecification = RequestSpecBuilderUtil.getRequestSpec();
		RestAssured.responseSpecification = ResponseSpecBuilderUtil.getResponseSpec();
		RestAssured.filters(new CustomLogFilter()); // ✅ Add custom log filter
	}

	@AfterSuite
	public void afterSuite() {
		// Flush the Extent Report
		ExtentManagerUtil.flushReport();
	}
}
