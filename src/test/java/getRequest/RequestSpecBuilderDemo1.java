/*
 * We use RequestSpecBuilder if our multiple test use same parameters. 
 */


package getRequest;

import org.testng.annotations.*;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;



public class RequestSpecBuilderDemo1 extends java.lang.Object {

	 private Map<String, String> header = new HashMap<String,String>();
	 private Map<String,Object> param = new HashMap<String,Object>();
	 private RequestSpecification reqSpec;
	 private RequestSpecBuilder reqBuilder;
	 private Response res1;
	 private JsonPath json;
	 
	@BeforeTest(description = "Without RequestSpecBuilder")
	public void setup() {
		RestAssured.baseURI = "https://accounts-staging.paytm.in";
		header.put("authorization","Basic dGVzdGNsaWVudDo5MzhkZWJjYS02NWQyLTQ1NzEtYTEwZC0xMzQwNTNmNzhkNTE=");
		header.put("Content-Type", "application/x-www-form-urlencoded");
		param.put("client_id", "testclient");
		param.put("do_not_redirect", "true");
		param.put("scope", "paytm");
		param.put("response_type", "code");
		param.put("username", "8806060281");
		param.put("password", "Paytm@123");
	}
	

	@BeforeTest(description="With RequestSpecBuilder")
	private void setup1() {	
		reqBuilder = new RequestSpecBuilder();
		reqBuilder.addFormParams(param);
		reqBuilder.setBaseUri("https://accounts-staging.paytm.in");
		reqBuilder.setBasePath("/oauth2/authorize");
		reqBuilder.addHeaders(header);
		reqSpec=reqBuilder.build();
	}

	
	@Test(description = "Without ReqSpecBuilder",priority=1)
	public void test1() {
		Response res = given().params(param).
		headers(header).when().
		post("/oauth2/authorize").then().extract().response();
		System.out.println(res.asString());
	}
	
	
	@Test(description="With ReqSpecBuilder",priority=2)
	public void test2() {
		res1=given().spec(reqSpec).when().post().then().extract().response();
		System.out.println(res1.asString());
	}
	
	@Test(description="Changing into Json",dependsOnMethods="test2")
	public void test3() {
		String response = res1.asString();
		json = new JsonPath(response);
	}
	
	

}
