package stepdefination;

import com.api.pojo.Profiles;
import com.api.pojo.Summary;
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
import org.json.JSONObject;
import org.testng.Assert;

import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.conn.params.ConnManagerParams.setTimeout;

public class Findmanysteps {
	private Response response;
	@SuppressWarnings("unused")
	private ValidatableResponse json;
	private RequestSpecification request;
	public List<HashMap<String,String>> datamap;
	private String batchID;

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

@Given("^customer provides Findmany endpoint with profile details at excel row \"([^\"]*)\" and \"([^\"]*)\"$")
public void customer_provides_Findmany_endpoint_with_profile_details_at_excel_row_and(String arg1, String arg2) throws Throwable {
	int index = Integer.parseInt(arg1)-1;
	int indexend = Integer.parseInt(arg2)-1;
	Findmanysteps fms=new Findmanysteps();
	RestAssured.baseURI=DataAccessConf.get().gethost();
	request=given().
			header("Content-Type","application/json").
			header("authorization",DataAccessConf.get().getapikey()).
			request().body(fms.getprofiles(index,indexend,datamap)).log().body().log().uri().log().headers();
}

	@When("^post request to findmany basic$")
	public void post_request_to_findmany_basic() throws Throwable {
		response = request.when().post(resources.getfindmanybasic());
		String responseString=response.asString();
		batchID=response.jsonPath().getString("batchID");
	}
	@When("^post request to findmany full$")
	public void post_request_to_findmany_full() throws Throwable {
		response = request.when().post(resources.getfindmanyfull());
		String responseString=response.asString();
		batchID=response.jsonPath().getString("batchID");
	}
	@When("^post request to Profiles Summary$")
	public void post_request_to_ProfilesSummary() throws Throwable {
		response = request.when().post(resources.getprofilesbysummary());
		String responseString=response.asString();
		batchID=response.jsonPath().getString("batchID");
	}

	@Then("^the status code should be matching for findmany basic \"([^\"]*)\"$")
	public void the_status_code_should_be_matching_for_findmany_basic(String arg1) throws Throwable {
		int index = Integer.parseInt(arg1)-1;
		json = response.then().statusCode(Integer.valueOf(datamap.get(index).get("Statuscode"))).log().body();
		String responseString=response.asString();
		Assert.assertNotNull(responseString);
	}
	public Profiles getprofiles(int startindex,int endindex,List<HashMap<String,String>> datamap)
	{
		List<Summary> profiles = new ArrayList<>() ;
		for(int i=startindex;i<=endindex;i++)
		{
			Summary summary=new Summary(datamap.get(i).get("Firstname"),datamap.get(i).get("LastName"),datamap.get(i).get("Address"),"",datamap.get(i).get("City"),datamap.get(i).get("State"),datamap.get(i).get("Zip"),datamap.get(i).get("Email"),datamap.get(i).get("Phone"));
			profiles.add(summary);
	    }

		Profiles profilesobject=new Profiles(profiles);
		return  profilesobject;
	}
	@Then("^call jobstatus endpoint to check the status$")
	public void call_jobstatus_endpoint_to_check_the_status() throws Throwable {
		outer:
		for(int i=0;i<=10;i++) {
			RestAssured.baseURI = DataAccessConf.get().gethost();
			String status;
			response = given().pathParam("batch_id", batchID).
					header("Content-Type", "application/json").log().uri().
					header("authorization", DataAccessConf.get().getapikey()).
					when().get(resources.getjobstatus());
			String responseString = response.asString();
			System.out.println(responseString);
			status = response.jsonPath().getString("status");
			if (status.equals("Complete"))
			{
				break ;

			}
			else
				{
					System.out.println(status);
					delay();
					continue outer;
				}

		}
	}
	public static void delay()
	{
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				// here goes your code to delay
			}
		}, 2000L);
	}

	@Then("^call findmany results endpoint with batchid$")
	public void call_findmany_results_endpoint_with_batchid() throws Throwable {
		RestAssured.baseURI = DataAccessConf.get().gethost();

		response = given().pathParam("batch_id", batchID).
				header("Content-Type", "application/json").log().uri().
				header("authorization", DataAccessConf.get().getapikey()).
				when().get(resources.getfindmanyresults());
		String responseString = response.asString();
		System.out.println(responseString);
		Assert.assertNotNull(responseString);
	}
	@Then("^call Profiles summary results endpoint with batchid$")
	public void call_Profilessummary_results_endpoint_with_batchid() throws Throwable {
		RestAssured.baseURI = DataAccessConf.get().gethost();

		response = given().pathParam("batch_id", batchID).
				header("Content-Type", "application/json").log().uri().
				header("authorization", DataAccessConf.get().getapikey()).
				when().get(resources.getprofilessummaryresults());
		String responseString = response.asString();
		System.out.println(responseString);
		Assert.assertNotNull(responseString);
	}
	@Then("^validate Schema for Profiles summary$")
	public void validate_Schema_for_Profiles_summary() throws Throwable {
		response.then().assertThat().body(matchesJsonSchemaInClasspath("ProfilesSummary.json"));
	}
}
