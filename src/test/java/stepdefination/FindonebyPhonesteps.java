package stepdefination;

import com.api.pojo.Email;
import com.api.pojo.Phone;
import com.api.resources.resources;
import com.we.api.utilities.DataAccessConf;
import com.we.api.utilities.DataHelper;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class FindonebyPhonesteps {
	private Response response;
	@SuppressWarnings("unused")
	private ValidatableResponse json;
	private RequestSpecification request;
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


	@Given("^customer provides find_one endpoint with valid phone at excel row \"([^\"]*)\" dataset$")
	public void customer_provides_find_one_endpoint_with_valid_phone_at_excel_row_dataset(String arg1) throws Throwable {
		int index = Integer.parseInt(arg1)-1;

		RestAssured.baseURI=DataAccessConf.get().gethost();
		//Email email=new Email(datamap.get(index).get("Firstname"),datamap.get(index).get("LastName"),datamap.get(index).get("Email"));
		Phone phone=new Phone(datamap.get(index).get("Firstname"),datamap.get(index).get("LastName"),datamap.get(index).get("Phone"));
		request=given().
				header("Content-Type","application/json").
				header("authorization",DataAccessConf.get().getapikey()).
				request().body(phone).log().body().log().uri().log().headers();
	}
	@When("^post request to find_one phone basic$")
	public void post_request_to_find_one_phone_basic() throws Throwable {
		response = request.when().post(resources.getfindonebyphonebasic());
	}

	@Then("^validateresponse at jsonpath \"([^\"]*)\" and \"([^\"]*)\" at excel row for phone \"([^\"]*)\"$")
	public void validateresponse_at_jsonpath_and_at_excel_row_for_phone(String arg1, String arg2, String arg3) throws Throwable {
		int index = Integer.parseInt(arg3)-1;
		Assert.assertEquals(response.body().jsonPath().getJsonObject(arg1).toString() /*actual value*/, datamap.get(index).get("P2G-text") /*expected value*/, "P2G score text is matching");
		Assert.assertEquals(response.body().jsonPath().getJsonObject(arg2).toString() /*actual value*/, datamap.get(index).get("P2G-value") /*expected value*/, "P2G score value is matching");
	}
	@When("^post request to find_one phone full$")
	public void post_request_to_find_one_phone_full() throws Throwable {
		response = request.when().post(resources.getfindonebyphonefull());
	}

	@Then("^the status code should be matching for phone \"([^\"]*)\"$")
	public void the_status_code_should_be_matching_for_phone(String arg1) throws Throwable {
		int index = Integer.parseInt(arg1)-1;
		json = response.then().statusCode(Integer.valueOf(datamap.get(index).get("Statuscode"))).log().body();
		String responseString=response.asString();
		Assert.assertNotNull(responseString);
	}

}
