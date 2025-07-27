package specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import config.ConfigLoader;

public class RequestSpecBuilderUtil {

    public static RequestSpecification getRequestSpec() {
       
    	Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getProperty("base.url"))  // You can load from config
                .addHeaders(headers)
                .setRelaxedHTTPSValidation()
                .build();
    }
}
