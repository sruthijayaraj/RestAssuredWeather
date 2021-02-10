package services;

import io.restassured.response.Response;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class GetRequest {
    public String URL ;
    public String key ;

    public GetRequest(String URL, String key){
        this.URL= URL;
        this.key= key;
    }

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
