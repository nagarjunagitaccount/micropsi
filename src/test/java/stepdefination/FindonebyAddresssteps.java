package stepdefination;

import static io.restassured.RestAssured.given;

import com.api.pojo.Address;
import com.cucumber.listener.Reporter;
import com.google.gson.GsonBuilder;
import com.we.api.utilities.DataHelper;
import org.testng.Assert;

import com.api.resources.resources;
import com.we.api.utilities.DataAccessConf;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;

public class FindonebyAddresssteps {
	
	private Response response;
	@SuppressWarnings("unused")
	private ValidatableResponse json;
	private RequestSpecification request;
	Scenario scenario;
	//private excelreader data;
	public List<HashMap<String,String>> datamap;
/*
	public summarysteps()
	{

		datamap = DataHelper.data("./src/main/resources/We_API_TDATA.xlsx","DataSheet1");
	}*/

	@Before
	public void beforeScenario()
	{
		datamap = DataHelper.data("./src/main/resources/We_API_TDATA.xlsx","DataSheet1");
	}


	@Given("^customer provides find_one endpoint with valid address at excel row \"([^\"]*)\" dataset$")
	public void customer_provides_find_one_endpoint_with_valid_address_at_excel_row_dataset(String arg1) throws Throwable {
		int index = Integer.parseInt(arg1)-1;

		RestAssured.baseURI=DataAccessConf.get().gethost();
		Address address=new Address(datamap.get(index).get("Firstname"),datamap.get(index).get("LastName"),datamap.get(index).get("Address"),"",datamap.get(index).get("City"),datamap.get(index).get("State"),datamap.get(index).get("Zip"));
		request=given().
				header("Content-Type","application/json").
				header("authorization",DataAccessConf.get().getapikey()).
				request().body(address).log().body().log().uri().log().headers();
		String formattedjson=new GsonBuilder().setPrettyPrinting()
				.create().toJson(address);
		Reporter.addStepLog("Request "+" "+formattedjson);

	}


	@When("^post request to find_one address basic$")
	public void post_request_to_find_one_address_basic() throws Throwable {
		response = request.when().post(resources.getfindonebyaddressbybasic());
		Reporter.addStepLog("response"+" "+response.prettyPrint());
	}
	@When("^post request to find_one address full$")
	public void post_request_to_find_one_address_full() throws Throwable {
		response = request.when().post(resources.getfindonebyaddressbyfull());
		Reporter.addStepLog("response"+" "+response.prettyPrint());
	}

	@Then("^the status code should be matching at excel row \"([^\"]*)\"$")
	public void the_status_code_should_be_matching_at_excel_row(String arg1) throws Throwable {
		int index = Integer.parseInt(arg1)-1;
		json = response.then().statusCode(Integer.valueOf(datamap.get(index).get("Statuscode"))).log().body();
		Reporter.addStepLog("Response code"+" "+response.statusCode());
		String responseString=response.asString();
		Assert.assertNotNull(responseString);
	}


	@Then("^validateresponse at jsonpath \"([^\"]*)\" and \"([^\"]*)\" at excel row \"([^\"]*)\"$")
	public void validateresponse_at_jsonpath_and_at_excel_row(String arg1, String arg2, String arg3) throws Throwable {
		int index = Integer.parseInt(arg3)-1;
		Assert.assertEquals(response.body().jsonPath().getJsonObject(arg1).toString() /*actual value*/, datamap.get(index).get("P2G-text") /*expected value*/, "P2G score text is matching");
		Assert.assertEquals(response.body().jsonPath().getJsonObject(arg2).toString() /*actual value*/, datamap.get(index).get("P2G-value") /*expected value*/, "P2G score value is matching");
	}

}
