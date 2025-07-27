package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;


public class HealthCheckTest extends BaseTest {

	@Test
	public void testApiIsUp() {
		RestAssured.baseURI = "https://reqres.in/api";
		int statusCode = RestAssured.given().when().get("/users?page=2").then().extract().statusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "API is not returning 200 status");
	}

	@Test
	public void healthCheck() {
		RestAssured.given().when().get("/api/users/2").then().body("data.id", equalTo(2));
	}
}
