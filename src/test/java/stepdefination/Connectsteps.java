package stepdefination;

import com.api.pojo.network;
import com.api.resources.resources;
import com.cucumber.listener.Reporter;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

import java.util.List;

import static io.restassured.RestAssured.given;

public class Connectsteps {
	
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;



	@Given("^User Sets connect Service api endpoint$")
	public void user_Sets_connect_Service_api_endpoint() throws Throwable {
		disconnectsteps step=new disconnectsteps();
		RestAssured.baseURI= DataAccessConf.get().gethost();
		step.diconnect();
	    List<String> ip=Getavailableip();
		network net=new network(ip.get(0));
		request=given().
				header("Content-Type","application/json").
				request().body(net).log().body().log().uri().log().headers();
		String fjson=new GsonBuilder().setPrettyPrinting()
				.create().toJson(net);
		Reporter.addStepLog("Payload "+" "+fjson);

	}

	@When("^User sends post request to connect$")
	public void user_sends_post_request_to_connect() throws Throwable {
		response = request.when().post(resources.getconnectresource()).then().extract().response();
	}

	@Then("^validate if api returns success response as \"([^\"]*)\"$")
	public void validate_if_api_returns_success_response_as(String actual) throws Throwable {
		String responseString=response.asString();
		Reporter.addStepLog("response"+" "+response.prettyPrint());
		JsonPath js= new JsonPath(responseString);
		boolean expected=Boolean.parseBoolean(actual);
		Assert.assertEquals(js.get("success"),expected);

	}

	@Then("^User receive valid Http response code for connect \"([^\"]*)\"$")
	public void user_receive_valid_Http_response_code(String statuscode) throws Throwable {
		response.then().statusCode(Integer.valueOf(statuscode)).extract().response();
		Assert.assertEquals(200,response.getStatusCode(),"Correct status code returned");


	}
	public List<String> Getavailableip()
	{

		RestAssured.baseURI=DataAccessConf.get().gethost();
		response=given().header("Content-Type","application/json").when().get(resources.getdevicesresource()).then().statusCode(200).extract().response();


		List<String> ips = response.jsonPath().getList("ip");


		return  ips;
	}
	public void connect()
	{
		RestAssured.baseURI= DataAccessConf.get().gethost();
		List<String> ip=Getavailableip();
		network net=new network(ip.get(0));
		given().
				header("Content-Type","application/json").
				request().body(net).when().post(resources.getconnectresource());



	}


}
