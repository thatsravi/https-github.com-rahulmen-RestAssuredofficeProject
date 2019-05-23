package getRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestAssuredDemo1 {

	private static String addPlaceRequestBody = "{\n" + 
			"    \"location\":{\n" + 
			"        \"lat\" : -38.383494,\n" + 
			"        \"lng\" : 33.427362\n" + 
			"    },\n" + 
			"    \"accuracy\":50,\n" + 
			"    \"name\":\"Frontline house\",\n" + 
			"    \"phone_number\":\"(+91) 983 893 3937\",\n" + 
			"    \"address\" : \"29, side layout, cohen 09\",\n" + 
			"    \"types\": [\"shoe park\",\"shop\"],\n" + 
			"    \"website\" : \"http://google.com\",\n" + 
			"    \"language\" : \"French-IN\"\n" + 
			"}";

	private static String deletePlaceRequestBody="{\n" + 
			"    \"place_id\":place_id           //(This value comes from Add place response)\n" + 
			"}";

	private static String place_id;

	@Test(priority=1)
	public void andPlace() {

		RestAssured.baseURI="http://216.10.245.166";

		Response res = given().body(addPlaceRequestBody).when().post("/maps/api/place/add/json").
				then().assertThat().statusCode(200).and().assertThat().contentType(ContentType.JSON).
				and().assertThat().body("status",equalTo("OK")).
				extract().response();

		/*extract().response() will return response in raw data so we have to use 
		 * toString() method to convert it into String still we can use Nativgate over the response and
		 * do validation for that we need to convert it into Json. 
		 */

		//ResponseBody resBody = res.getBody();

		String responseOutput = res.asString();
		System.out.println(responseOutput);
		JsonPath json = new JsonPath(responseOutput);		
		place_id = json.get("place_id");
	}


	@Test(priority=2)
	public void deleteplace() {
		RestAssured.baseURI="http://216.10.245.166";
		Response res = given().body(deletePlaceRequestBody).when().post("/maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().assertThat().contentType(ContentType.JSON).
		extract().response();
		//and().assertThat().body("status",equalTo("OK"));	

		String deleteResponse = res.asString();
		
		System.out.println(deleteResponse);
		
	}
}