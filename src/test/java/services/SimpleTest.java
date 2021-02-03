package services;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Test;


public class SimpleTest
{
	private static String URL = "https://api.weatherbit.io/v2.0/forecast/daily";

	@Test
	public void testGetStatsus200(){
		String apiKey = "ff3d40689e234c448c6f9efc3c59cb10";

		given().
		param("city", "Sydney,AU").
				param("units","M").
				param("postal_code","2769").
				param("key","ff3d40689e234c448c6f9efc3c59cb10").
		when().
		get(URL)
		.then().
		statusCode(HttpStatus.SC_OK);

	}
}
