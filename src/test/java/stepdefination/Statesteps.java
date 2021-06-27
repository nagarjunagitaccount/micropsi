package stepdefination;

import com.api.resources.resources;
import com.cucumber.listener.Reporter;
import com.micropsi.api.utilities.DataAccessConf;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Statesteps {
	
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;


	@Given("^User Sets state  api endpoint$")
	public void user_Sets_state_api_endpoint() throws Throwable {
		RestAssured.baseURI=DataAccessConf.get().gethost();
		request=given().header("Content-Type","application/json");
	}

	@When("^User sends get request to state$")
	public void user_sends_get_request_to_state() throws Throwable {
		response = request.when().get(resources.getstateresource());
	}

	@Then("^User receive valid Http response code for state \"([^\"]*)\"$")
	public void user_receive_valid_Http_response_code_for_state(String statuscode) throws Throwable {
		response.then().statusCode(Integer.valueOf(statuscode)).extract().response();
		String responseString=response.asString();
		Reporter.addStepLog("response"+" "+response.prettyPrint());
		Assert.assertEquals(200,response.getStatusCode(),"Correct status code returned");
	}
}
