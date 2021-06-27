package stepdefination;

import com.api.pojo.brightness;
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

public class Brightnesssteps {
	
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	private float brightnessactualvalue;

	@Given("^User Sets brightness api endpoint brightness \"([^\"]*)\"$")
	public void user_Sets_brightness_api_endpoint(String brightness) throws Throwable {
		RestAssured.baseURI= DataAccessConf.get().gethost();
		brightness bright=new brightness(Double.parseDouble(brightness));
		brightnessactualvalue= Float.parseFloat(brightness);
		request=given().
				header("Content-Type","application/json").
				request().body(bright).log().body().log().uri().log().headers();

		String fjson=new GsonBuilder().setPrettyPrinting()
				.create().toJson(bright);
		Reporter.addStepLog(fjson);

	}

	@When("^User sends post request to brightness$")
	public void user_sends_post_request_to_brightness() throws Throwable {

		response = request.when().post(resources.getbrightnessresource()).then().extract().response();
	}

	@Then("^User receive valid Http response code for update \"([^\"]*)\"$")
	public void user_receive_valid_Http_response_code_for_brightness(String status) throws Throwable {

		response.then().statusCode(Integer.valueOf(status)).extract().response();

		String responseString=response.asString();
		//Reporter.addStepLog("response"+" "+response.prettyPrint());
		System.out.println(responseString);
		Assert.assertEquals(200,response.getStatusCode(),"Correct status code returned");
	}
	@Then("^validate if api returns success response for update \"([^\"]*)\"$")
	public void validate_if_api_returns_success_response(String actual) throws Throwable {
		String responseString=response.asString();
		Reporter.addStepLog("response"+" "+response.prettyPrint());
		JsonPath js= new JsonPath(responseString);
		boolean expected=Boolean.parseBoolean(actual);
		Assert.assertEquals(js.get("success"),expected);

	}
	@Then("^verify if the value is updated in state Api \"([^\"]*)\"$")
	public void verify_if_the_values_is_updated_in_state_Api(String attribute) throws Throwable {

		RestAssured.baseURI=DataAccessConf.get().gethost();
		response=given().header("Content-Type","application/json").when().get(resources.getstateresource()).then().statusCode(200).extract().response();
		String rstring=response.asString();
		JsonPath js= new JsonPath(rstring);
		Reporter.addStepLog("response"+" "+rstring);
		System.out.println(brightnessactualvalue);
		Assert.assertEquals(js.get(attribute),brightnessactualvalue);
	}

}
