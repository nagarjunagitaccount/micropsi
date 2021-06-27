package stepdefination;

import com.api.pojo.color;
import com.api.resources.resources;
import com.cucumber.listener.Reporter;
import com.google.gson.GsonBuilder;
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

public class Colorsteps {
	
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	private String colorvalue;


	@Given("^User Sets Color api endpoint Color as \"([^\"]*)\"$")
	public void user_Sets_Color_api_endpoint_brightness(String colorcode) throws Throwable {
		RestAssured.baseURI= DataAccessConf.get().gethost();
		color col=new color(colorcode);
		colorvalue=colorcode;
		request=given().
				header("Content-Type","application/json").
				request().body(col).log().body().log().uri().log().headers();

		String fjson=new GsonBuilder().setPrettyPrinting()
				.create().toJson(col);
		Reporter.addStepLog(fjson);

	}

	@When("^User sends post request to Color$")
	public void user_sends_post_request_to_Color() throws Throwable {
		response = request.when().post(resources.getcolorresource()).then().extract().response();
	}

	@When("^User receive valid Http response code for Color \"([^\"]*)\"$")
	public void user_receive_valid_Http_response_code_for_Color(String status) throws Throwable {
		response.then().statusCode(Integer.valueOf(status)).extract().response();

		String responseString=response.asString();
		Reporter.addStepLog("response"+" "+responseString);
		System.out.println(responseString);
		Assert.assertEquals(200,response.getStatusCode(),"Correct status code returned");
	}

	@Then("^validate if api returns success response for Color \"([^\"]*)\"$")
	public void validate_if_api_returns_success_response_for_Color(String actual) throws Throwable {
		String responseString=response.asString();
		Reporter.addStepLog("response"+" "+responseString);
		JsonPath js= new JsonPath(responseString);
		boolean expected=Boolean.parseBoolean(actual);
		Assert.assertEquals(js.get("success"),expected);
	}
	@Then("^verify if the color is updated in state Api \"([^\"]*)\"$")
	public void verify_if_the_values_is_updated_in_state_Api(String attribute) throws Throwable {

		RestAssured.baseURI=DataAccessConf.get().gethost();
		response=given().header("Content-Type","application/json").when().get(resources.getstateresource()).then().statusCode(200).extract().response();
		String rstring=response.asString();
		JsonPath js= new JsonPath(rstring);
		Reporter.addStepLog("response"+" "+rstring);
		Assert.assertEquals(js.get(attribute),colorvalue);
	}
}
