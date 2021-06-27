package stepdefination;

import com.api.pojo.name;
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

public class Namesteps {
	
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	private String name;
	@Given("^User Sets Name api endpoint with name as \"([^\"]*)\"$")
	public void user_Sets_Name(String Name) throws Throwable {
		RestAssured.baseURI= DataAccessConf.get().gethost();
		name=Name;
		name dev=new name(Name);
		request=given().
				header("Content-Type","application/json").
				request().body(dev).log().body().log().uri().log().headers();

		String fjson=new GsonBuilder().setPrettyPrinting()
				.create().toJson(dev);
		Reporter.addStepLog(fjson);
	}

	@When("^User sends post request to Name$")
	public void user_sends_post_request_to_Name() throws Throwable {
		response = request.when().post(resources.getnameresource()).then().extract().response();
	}

	@When("^User receive valid Http response code for Name \"([^\"]*)\"$")
	public void user_receive_valid_Http_response_code_for_Name(String status) throws Throwable {
		response.then().statusCode(Integer.valueOf(status)).extract().response();

		String responseString=response.asString();
		Reporter.addStepLog("response"+" "+responseString);
		System.out.println(responseString);
		Assert.assertEquals(200,response.getStatusCode(),"Correct status code returned");
	}

	@Then("^validate if api returns success response for Name \"([^\"]*)\"$")
	public void validate_if_api_returns_success_response_for_Name(String actual) throws Throwable {
		String responseString=response.asString();
		//Reporter.addStepLog("response"+" "+response.prettyPrint());
		JsonPath js= new JsonPath(responseString);
		boolean expected=Boolean.parseBoolean(actual);
		Assert.assertEquals(js.get("success"),expected);
	}
	@Then("^verify if the name is updated in state Api \"([^\"]*)\"$")
	public void verify_if_the_name_is_updated_in_state_Api(String attribute) throws Throwable {

		RestAssured.baseURI=DataAccessConf.get().gethost();
		response=given().header("Content-Type","application/json").when().get(resources.getstateresource()).then().statusCode(200).extract().response();
		String rstring=response.asString();
		JsonPath js= new JsonPath(rstring);
		Reporter.addStepLog("response"+" "+rstring);
		Assert.assertEquals(js.get(attribute),name);
	}

}
