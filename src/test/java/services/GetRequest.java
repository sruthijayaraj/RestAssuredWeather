package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest {
     String URL ="https://api.weatherbit.io/v2.0/forecast/daily";
     String key = "ff3d40689e234c448c6f9efc3c59cb10";



    public Response getWeatherReport(HashMap<String, String> paramsMap){
        Response response=  given().
                param("city", paramsMap.get("city")).
                param("units",paramsMap.get("units")).
                param("postal_code",paramsMap.get("postal_code")).
                param("key",key).
                when().
                get(URL).then().

                extract().response();

        return response;
    }
}
