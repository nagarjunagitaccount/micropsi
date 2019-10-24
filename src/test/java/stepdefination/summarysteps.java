package stepdefination;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import com.api.pojo.Address;
import com.api.resources.resources;
import com.we.api.utilities.DataAccessConf;
import com.we.api.utilities.excelreader;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class summarysteps {
	
	private Response response;
	@SuppressWarnings("unused")
	private ValidatableResponse json;
	private RequestSpecification request;
	Scenario scenario;
	//private excelreader data;

	@Before
	public void before(Scenario scenario) {
	    this.scenario = scenario;
	}




@Then("^validateresponse \"([^\"]*)\"$")
public void validateresponse(String arg1) throws Throwable {
	Assert.assertEquals(response.body().jsonPath().getJsonObject(arg1).toString() /*actual value*/, "2|3 - Above Average" /*expected value*/, "Data not matching with API response");
}


@Given("^A valid api key and Address for SummaryApi$")
public void a_valid_api_key_and_Address_for_SummaryApi() throws Throwable {

	        RestAssured.baseURI=DataAccessConf.get().gethost();
            Address address=new Address("John","Kimberlin","5940 Via Real","Carpinteria","CA","93013");
	        request=given().
		    header("Content-Type","application/json").
			header("authorization",DataAccessConf.get().getapikey()).
			request().body(address);
}
@Given("^Prepare the request for summary api with Address \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
public void prepare_the_request_for_summary_api_with_Address(String fname, String lname, String adress, String city, String state, String zip) throws Throwable {
	excelreader data=new excelreader();  
	    String fname1=data.get(scenario.getName(), fname);
	    System.out.println(fname1);
	    RestAssured.baseURI=DataAccessConf.get().gethost();
	    Address address=new Address(data.get(scenario.getName(), fname),data.get(scenario.getName(), lname),data.get(scenario.getName(), adress),data.get(scenario.getName(), city),data.get(scenario.getName(), state),data.get(scenario.getName(), zip));
	 
        request=given().
	    header("Content-Type","application/json").
		header("authorization",DataAccessConf.get().getapikey()).
		request().body(address).log().body();
        //System.out.println("request: " + request().body(address));
}
@When("^Invoke the post Api$")
public void invoke_the_post_Api() throws Throwable {
	response = request.when().post(resources.getfindonebyaddressbybasic());
	System.out.println("response: " + response.prettyPrint());
}



@Then("^the status code should be \"([^\"]*)\"$")
public void the_status_code_should_be(String statuscode) throws Throwable 
{
	excelreader data=new excelreader();
	 int code=Integer.parseInt(data.get(scenario.getName(), statuscode));
	 System.out.println(scenario.getName());
	 json = response.then().statusCode(code);
}



}
