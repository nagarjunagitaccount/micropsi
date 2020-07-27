package stepdefination;

import com.api.pojo.Session;
import com.api.resources.resources;
import com.cucumber.listener.Reporter;
import com.google.gson.GsonBuilder;
import com.we.api.utilities.DataAccessConf;
import com.we.api.utilities.DataHelper;
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
import static org.hamcrest.Matchers.hasKey;

public class sessionsteps {
    private Response response;
    @SuppressWarnings("unused")
    private ValidatableResponse json;
    private RequestSpecification request;
    public List<HashMap<String,String>> datamap;

	public sessionsteps()
	{

		datamap = DataHelper.data("./src/main/resources/We_API_TDATA.xlsx","Session");
	}

    @Given("^customer provides createsession endpoint with duration \"([^\"]*)\"$")
    public void customer_provides_createsession_endpoint_with_duration(String duration) throws Throwable {
        RestAssured.baseURI= DataAccessConf.get().gethost();
        Session payload=new Session(duration);
        request=given().
                header("Content-Type","application/json").
                header("authorization",DataAccessConf.get().getapikey()).
                request().body(payload).log().body().log().uri().log().headers();
    }
    @When("^post to createsession api$")
    public void post_to_createsession_api() throws Throwable {
        response = request.when().post(resources.getcreatesession());
        Reporter.addStepLog("response"+" "+response.prettyPrint());

    }

    @Then("^validate response code \"([^\"]*)\"$")
    public void validate_response_code(String statuscode) throws Throwable {

	    json = response.then().statusCode(Integer.valueOf(statuscode)).log().body().and().body("$", hasKey("_id"));
        String responseString=response.asString();
        Assert.assertNotNull(responseString);


    }
    @Given("^customer provides createsession endpoint with duration at excel row \"([^\"]*)\"$")
    public void customer_provides_createsession_endpoint_with_duration_at_excel_row(String arg1) throws Throwable {
        int index = Integer.parseInt(arg1)-1;
	    RestAssured.baseURI= DataAccessConf.get().gethost();
        Session payload=new Session(datamap.get(index).get("Duration"));
        request=given().
                header("Content-Type","application/json").
                header("authorization",DataAccessConf.get().getapikey()).
                request().body(payload).log().body().log().uri().log().headers();
        String formattedjson=new GsonBuilder().setPrettyPrinting()
                .create().toJson(payload);
        Reporter.addStepLog("Request "+" "+formattedjson);
    }

    @Then("^validate response code at excel row \"([^\"]*)\"$")
    public void validate_response_code_at_excel_row(String arg1) throws Throwable {
        int index = Integer.parseInt(arg1)-1;
	    json = response.then().statusCode(Integer.parseInt(datamap.get(index).get("Statuscode"))).log().body().and().body("$", hasKey("_id"));
        Reporter.addStepLog("Response code"+" "+response.statusCode());
	    String responseString=response.asString();
        Assert.assertNotNull(responseString);
    }
}
