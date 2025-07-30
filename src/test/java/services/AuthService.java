package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import base.BaseTest;
import config.ConfigLoader;

public class AuthService  {
	private static String token;

	public static String getToken() {
		if (token == null) {
			token = fetchToken();
		}
		return token;
	}

	private static String fetchToken() {
		
		String body = "{ \"email\": \"" + ConfigLoader.getProperty("email") +
	             "\", \"password\": \"" + ConfigLoader.getProperty("password") + "\" }";

		System.out.println("Base URL: " + ConfigLoader.getProperty("base.url"));
		System.out.println("Auth Endpoint: " + ConfigLoader.getProperty("auth.endpoint"));
		System.out.println("Body: " + body);



		Response response = RestAssured.given().baseUri(ConfigLoader.getProperty("base.url"))
				.header("x-api-key", "reqres-free-v1").header("Content-Type", "application/json").body(body).post(ConfigLoader.getProperty("auth.endpoint"));

		
		if (response.statusCode() == 200) {
			System.out.println(response.jsonPath().getString("token"));
			return response.jsonPath().getString("token");
		} else {
			throw new RuntimeException("Failed to get token: " + response.asString());
		}
	}
}
