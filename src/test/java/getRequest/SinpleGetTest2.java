package getRequest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class SinpleGetTest2 {

	@Test
	public void GetWeatherDetails() {

		String uri = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";


		RestAssured.baseURI = uri;


		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET);
		//httpRequest.get(uri).then().assertThat().body("message");

		String responseBody = response.getBody().asString();

		System.out.println("Response body is :"+responseBody);






	}

}
