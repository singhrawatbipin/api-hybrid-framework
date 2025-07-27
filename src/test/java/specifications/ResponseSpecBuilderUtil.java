package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

public class ResponseSpecBuilderUtil {

	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder().expectStatusCode(200).expectResponseTime(lessThan(3000L)).build();
	}
}
