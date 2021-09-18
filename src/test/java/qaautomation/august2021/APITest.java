package qaautomation.august2021;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import qaautomation.august2021.apis.APIEndpoints;
import qaautomation.august2021.utils.APIUtility;
import qaautomation.august2021.utils.DataUtility;

/**
 * Unit test for simple App.
 */
public class APITest extends BaseAPITest {
	/**
	 * Rigorous Test :-)
	 */

	@Test(priority = 2)
	public void userAPI() {
		Response response = given().spec(loginJsonSpec).when().get(APIEndpoints.user);
		APIUtility.verifyStatusCode(response);
		AssertJUnit.assertEquals(APIUtility.verifyStatusCode(response), true);
	}

	@Test(priority = 3)
	public void dashboardAPI() {
		Response response = given().spec(loginJsonSpec).param("status", "completed").when().get(APIEndpoints.dashboard);
		System.out.println(response.body().asPrettyString());
		APIUtility.verifyStatusCode(response);
	}
	
	@Test
	public void configAPI() {
		Response response = given().spec(commonJsonSpec).when().get(APIEndpoints.config);
		APIUtility.verifyStatusCode(response);
	}
	
	@Test
	public void fakerEmail() {
		Faker faker = new Faker();
		System.out.println(faker.name().username() + "@gmail.com");
	}
	
	@Test
	public void registerUsingFaker() {
		Faker faker = new Faker();
		String fakerEmail = faker.name().username() + "@gmail.com";
		String signUpPayload = DataUtility.getDataFromExcel("Payloads", "SignUpPayload")
				.replace("$.email", fakerEmail);
		Response response = given().spec(commonJsonSpec).body(signUpPayload).when().post(APIEndpoints.signUp);
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void incorrectLoginAPI() {
		String loginFailedPayload = DataUtility.getDataFromExcel("Payloads", "IncorrectLoginPayload")
				.replace("$.username", "a@gmail.com").replace("$.password", "1234544");
		Response response = given().spec(commonJsonSpec).body(loginFailedPayload).when().post(APIEndpoints.login);
//		APIUtility.verifyStatusCode(response);
		assertNotEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void dashboardAPIWithSchema() {
		Response response = given().spec(loginJsonSpec).param("status", "completed").when().get(APIEndpoints.dashboard);
		APIUtility.verifyStatusCode(response);
		response.then().assertThat()
				.body(matchesJsonSchema(DataUtility.getDataFromExcel("Schemas", "DashboardAPISchema")));
	}

}
