package stepdefs;


import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojo.ResponseObject;
import services.GetRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import  FilterUtility.Filters;

public class WeatherStepDefinitions {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	private Scenario scenario;

	public static HashMap<String, String> requestParameters = new HashMap<>();
	public static ArrayList<String> searchCriteria = new ArrayList<>();
	List<ResponseObject> responseObjects = new ArrayList<ResponseObject>() ;
	List<ResponseObject> filteredByTemp = new ArrayList<ResponseObject>() ;
	List<ResponseObject> filteredByUv = new ArrayList<ResponseObject>() ;
	public  List<ResponseObject> finalList = new ArrayList<ResponseObject>() ;


	Filters filtersUtility ;

	public WeatherStepDefinitions(){

	}

	@Given("^I like to surf in any beach of (.*)$")
	public void userSetsStateAndCountry(String param){
    	requestParameters.put("city:" , param);
	}

	@And("^I only like to surf on any 2 days specifically (.*) & (.*) in next 16 Days$")
	public void surfOnSpecificDays(String dayOne, String dayTwo) {
		searchCriteria.add(dayOne);
		searchCriteria.add(dayTwo);
	}
	@When("^I look up the the weather forecast for the next 16 days using POSTALCODES (.*)$")
	public void getWeatherForNextDatesUsingPostcode(String postalCode){
		requestParameters.put("postal_code",postalCode);
		requestParameters.put("units","M");
		response = new GetRequest().getWeatherReport(requestParameters);
		filtersUtility= new Filters(response,responseObjects,filteredByTemp);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("^I check to if see the temperature is between (.*)°C and (.*)°C$")
	public void searchDataWithTempBetween(String min, String max)  {
		float min_temp=Float.parseFloat(min);
		float max_temp = Float.parseFloat(max);
 		searchCriteria.add(min);
		searchCriteria.add(max);
		filteredByTemp=filtersUtility.filterDataBasedOnTempLimits(min_temp,max_temp);

	}


	@Then("^I check to see if UV index is <= (.*)$")
	public void searchIfUVIndexIsLesserThan(Integer uv)  {
		searchCriteria.add(uv.toString());
		filteredByUv =filtersUtility.filterByUV(uv);
	}


	@Then("^I Pick two spots based on suitable weather forecast for the day$")
	public void pickTwoDays(){

		finalList =filtersUtility.searchAndFilterBasedOnUserGivenTwoDays(searchCriteria.get(0),searchCriteria.get(1));


		System.out.println("Printing final dates");
		//Assert the final dates are under the required UV index
		for (ResponseObject finalOne : finalList) {
			System.out.println("Final dates "+finalOne.getValid_date());
			System.out.println("Max temperature on the day is "+finalOne.getMax_temp());
			System.out.println("Min temperature on the day is "+finalOne.getMin_temp());
			System.out.println("UV : "+finalOne.getUv());
			Assert.assertTrue((finalOne.getUv() < Float.parseFloat(searchCriteria.get(4))));
		}

	}



	@After
	public void tearDown(Scenario scenario) throws Exception {

		try {
			if (scenario != null) {

				if(scenario.isFailed())
					scenario.log("Failed");
				else {
					//scenario.log(response.prettyPrint());
					//If needed this will print the response

					for (ResponseObject finalOne : finalList) {
						System.out.println("Final dates " + finalOne.getValid_date());
						scenario.log("Final dates " + finalOne.getValid_date());
						System.out.println("Max temperature on the day is  " + finalOne.getMax_temp());
						scenario.log("Max temperature on the day is  " + finalOne.getMax_temp());
						System.out.println("Min temperature on the day is " + finalOne.getMin_temp());
						scenario.log("Min temperature on the day is " + finalOne.getMin_temp());
						System.out.println("UV:" + finalOne.getUv());
						scenario.log("UV:" + finalOne.getUv());




					}
				}

			}
		} catch (Exception e) {

			throw e;
		}
	}


}


