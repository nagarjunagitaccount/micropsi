package stepdefination;

import com.api.resources.resources;
import com.cucumber.listener.Reporter;
import com.micropsi.api.utilities.DataAccessConf;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class disconnectsteps {
	
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;


	@Given("^User Sets disconnect api endpoint$")
	public void user_Sets_disconnect_api_endpoint() throws Throwable {
		RestAssured.baseURI= DataAccessConf.get().gethost();

		request=given().
				header("Content-Type","application/json").body("");
	}

	@When("^User sends post request to disconnect$")
	public void user_sends_post_request_to_disconnect() throws Throwable {
		response = request.when().post(resources.getdisconnectresource()).then().extract().response();
	}

	@When("^User receive valid Http response code for disconnect \"([^\"]*)\"$")
	public void user_receive_valid_Http_response_code_for_disconnect(String status) throws Throwable {
		response.then().statusCode(Integer.valueOf(status)).extract().response();

		String responseString=response.asString();
		Reporter.addStepLog("response"+" "+responseString);
		System.out.println(responseString);
		Assert.assertEquals(200,response.getStatusCode(),"Correct status code returned");
	}

	@Then("^validate if api returns success response for disconnect \"([^\"]*)\"$")
	public void validate_if_api_returns_success_response_for_disconnect(String actual) throws Throwable {
		String responseString=response.asString();
	//	Reporter.addStepLog("response"+" "+response.prettyPrint());
		JsonPath js= new JsonPath(responseString);
		boolean expected=Boolean.parseBoolean(actual);
		Assert.assertEquals(js.get("success"),expected);
	}
	public void diconnect()
	{
		RestAssured.baseURI= DataAccessConf.get().gethost();

		given().
				header("Content-Type","application/json").body("").post(resources.getdisconnectresource()).then().statusCode(200);
	}

}
