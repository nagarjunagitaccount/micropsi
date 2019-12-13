package stepdefination;

import com.api.pojo.*;
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

import java.util.*;

import static io.restassured.RestAssured.given;

public class Megacallsteps {
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


	@Given("^customer provides megacall endpoint with profile details at excel row \"([^\"]*)\" and \"([^\"]*)\" dataset$")
	public void customer_provides_megacall_endpoint_with_profile_details_at_excel_row_and_dataset(String arg1, String arg2) throws Throwable {
		int index = Integer.parseInt(arg1)-1;
		int indexend = Integer.parseInt(arg2)-1;
		Megacallsteps fms=new Megacallsteps();
		RestAssured.baseURI=DataAccessConf.get().gethost();
		request=given().
				header("Content-Type","application/json").
				header("authorization",DataAccessConf.get().getmegakey()).
				request().body(fms.getprofiles(index,indexend,datamap)).log().body().log().uri().log().headers();
	}

	@When("^post request to megacall$")
	public void post_request_to_megacall() throws Throwable {
		response = request.when().post(resources.getmegacall());
		String responseString=response.asString();
		batchID=response.jsonPath().getString("batchID");
	}

	@Then("^the status code should be matching for megacall \"([^\"]*)\"$")
	public void the_status_code_should_be_matching_for_megacall(String arg1) throws Throwable {
		int index = Integer.parseInt(arg1)-1;
		json = response.then().statusCode(Integer.valueOf(datamap.get(index).get("Statuscode"))).log().body();
		String responseString=response.asString();
		Assert.assertNotNull(responseString);
	}
	public Megacall getprofiles(int startindex,int endindex,List<HashMap<String,String>> datamap)
	{
		List<Megacallprofile> profiles = new ArrayList<>() ;


		for(int i=startindex;i<=endindex;i++)
		{
			List<megaaddress> addressess=new ArrayList<>() ;
			Identity identity=new Identity(datamap.get(i).get("Firstname"),datamap.get(i).get("LastName"));
			megaaddress addresses=new megaaddress(datamap.get(i).get("Address"),datamap.get(i).get("City"),datamap.get(i).get("State"),datamap.get(i).get("Zip"),"USA","");
			addressess.add(addresses);
			Megacallprofile profile=new Megacallprofile(identity,addressess);
			profiles.add(profile);
		}

		Megacall profilesobject=new Megacall(profiles);
		return  profilesobject;
	}
	@Then("^call megacall jobstatus endpoint to check the status$")
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
	@Then("^call megacall results endpoint with batchid$")
	public void call_megacall_results_endpoint_with_batchid() throws Throwable {
		RestAssured.baseURI = DataAccessConf.get().gethost();

		response = given().pathParam("batch_id", batchID).
				header("Content-Type", "application/json").log().uri().
				header("authorization", DataAccessConf.get().getmegakey()).
				when().get(resources.getmegacallresults());
		String responseString = response.asString();
		System.out.println(responseString);
		Assert.assertNotNull(responseString);
	}
}
