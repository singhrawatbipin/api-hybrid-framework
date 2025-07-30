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
		System.out.println("::BasePage :: @BeforeSuite");
		// Load environment (default = qa)
		String env = System.getProperty("env", "qa");
		
		System.out.println("::BasePage :: ConfigLoader.loadConfig(env);");
		ConfigLoader.loadConfig(env);
		

		// Initialize Extent Report
		System.out.println("::BasePage :: ExtentManagerUtil.initReport();");
		ExtentManagerUtil.initReport();
		
		System.out.println("::BasePage :: ExtentManagerUtil.getExtent().setSystemInfo(\"Environment\", env);");
		ExtentManagerUtil.getExtent().setSystemInfo("Environment", env);
		
		System.out.println("::BasePage :: ExtentManagerUtil.getExtent().setSystemInfo(\"Base URI\", ConfigLoader.getProperty(\"base.url\"));");
		ExtentManagerUtil.getExtent().setSystemInfo("Base URI", ConfigLoader.getProperty("base.url"));
		
		


	}

	@BeforeClass
	public void setup() {
		System.out.println(":: BaseClass :: @setup()");
		RestAssured.requestSpecification = RequestSpecBuilderUtil.getRequestSpec();
		RestAssured.responseSpecification = ResponseSpecBuilderUtil.getResponseSpec();
		RestAssured.filters(new CustomLogFilter()); // ✅ Add custom log filter
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println(":: BaseClass :: @AfterSuite :: @setup()");
		// Flush the Extent Report
		ExtentManagerUtil.flushReport();
	}
}
