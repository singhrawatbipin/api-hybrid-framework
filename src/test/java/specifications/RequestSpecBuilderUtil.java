package specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import services.AuthService;

import java.util.HashMap;
import java.util.Map;

import config.ConfigLoader;

public class RequestSpecBuilderUtil {

	public static RequestSpecification getRequestSpec() {

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		//headers.put("x-api-key", "reqres-free-v1");
		headers.put("Authorization", "Bearer " + AuthService.getToken());
		

		System.out.println("Headers: " + headers);

		
		return new RequestSpecBuilder().setBaseUri(ConfigLoader.getProperty("base.url")) // You can load from config
				.addHeaders(headers).build();
	}
	
    public static RequestSpecification getDefaultSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getProperty("base.url"))
                .addHeader("Content-Type", "application/json")
                .build();
    }
}
