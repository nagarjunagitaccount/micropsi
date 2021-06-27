package stepdefination;

import static io.restassured.RestAssured.given;


import com.cucumber.listener.Reporter;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import org.testng.Assert;

import com.api.resources.resources;
import com.micropsi.api.utilities.DataAccessConf;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.regex.*;

import java.util.List;

public class Devicesteps  {
	
	private Response response;
	@SuppressWarnings("unused")
	private ValidatableResponse json;
	private RequestSpecification request;


	 @Given("^User Sets Devices Service api endpoint$")
	 public void user_Sets_Devices_Service_api_endpoint() throws Throwable {

		RestAssured.baseURI=DataAccessConf.get().gethost();
		request=given().header("Content-Type","application/json");
		//Reporter.addStepLog(request.get().asString());




	}

	@When("^User sends get request to devices$")
	public void user_sends_get_request_to_devices() throws Throwable {
		response = request.when().get(resources.getdevicesresource());


	}

	@Then("^User receive valid Http response code \"([^\"]*)\"$")
	public void user_receive_valid_Http_response_code(String statuscode) throws Throwable {
		response=response.then().statusCode(Integer.valueOf(statuscode)).extract().response();
		Assert.assertEquals(200,response.getStatusCode(),"Correct status code returned");


	}

	@Then("^User receive valid response$")
	public void user_receive_valid_response() throws Throwable {

		String responseString=response.asString();
		Reporter.addStepLog("response"+" "+response.prettyPrint());
		Assert.assertNotNull(responseString);


	}
	@Then("^verify if listed Ips are in valid format$")
	public void verify_if_listed_Ips_are_in_valid_format() throws Throwable {
	 	XmlPath xmlPath = new XmlPath(XmlPath.CompatibilityMode.HTML, response.getBody().asString());
		String body=xmlPath.get("html.body");
		JsonPath jsonPath = new JsonPath(body);
		List<String> ips = jsonPath.getList("ip");

		String zeroTo255
				= "(\\d{1,2}|(0|1)\\"
				+ "d{2}|2[0-4]\\d|25[0-5])";
		String regex
				= zeroTo255 + "\\."
				+ zeroTo255 + "\\."
				+ zeroTo255 + "\\."
				+ zeroTo255;
		Pattern p = Pattern.compile(regex);


		for(String ip:ips)
		{
			Matcher m = p.matcher(ip);
			Assert.assertTrue(m.matches());

		}


	}




}
