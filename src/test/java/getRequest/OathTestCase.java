package getRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import io.restassured.RestAssured;


public class OathTestCase {
	
	public static String pattern="Ra";
	public static String matcher="My name is Rahul Mendiratta. Name is Rahul Mendiratta";

	public static Pattern p = Pattern.compile(pattern);
	public static Matcher m = p.matcher(matcher);

	@Test
	public void authorise() {

		RestAssured.baseURI="https://accounts-staging.paytm.in";

		given().
		header("authorization","Basic dGVzdGNsaWVudDo5MzhkZWJjYS02NWQyLTQ1NzEtYTEwZC0xMzQwNTNmNzhkNTE=").and()
		.header("Content-Type", "application/x-www-form-urlencoded").and().
		body("response_type=code&client_id=testclient&do_not_redirect=true&scope=paytm&"+
				"username=9599711105&password=paytm@123").
		when().post("/oauth2/authorize").then().assertThat().statusCode(200).and().contentType("application/json");/*.and().
		assertThat().body("code",equalTo("96077597-b924-4016-9473-f94fb8370800"));*/

	}
	
	@Test
	public void matchCount() {
		while(m.find()) {
			System.out.println(m.start()+"...."+m.end()+"--->"+m.group());
		}
	}

}
