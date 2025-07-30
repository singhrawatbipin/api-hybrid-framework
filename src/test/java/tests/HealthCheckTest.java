package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import specifications.RequestSpecBuilderUtil;
import specifications.ResponseSpecBuilderUtil;
import utils.CustomLogFilter;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;


public class HealthCheckTest extends BaseTest {

	
	@Test()
    public void testApiIsUp() {
		RestAssured.requestSpecification = RequestSpecBuilderUtil.getDefaultSpec();
        int statusCode = given().when()
                .get("/api/users?page=2")
                .then()
                .extract()
                .statusCode();

        System.out.println("Status Code: " + statusCode);
        Assert.assertEquals(statusCode, 200, "API is not returning 200 status");
    }
//
//    @Test
//    public void healthCheck() {
//        given()
//                .when()
//                .get("/api/users/2")
//                .then()
//                .body("data.id", equalTo(2));
//    }
}
