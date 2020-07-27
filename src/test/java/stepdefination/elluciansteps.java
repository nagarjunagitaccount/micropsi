package stepdefination;

import com.api.pojo.*;
import com.api.resources.resources;

import com.cucumber.listener.Reporter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.relevantcodes.extentreports.ExtentReports;
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




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class elluciansteps {
    private Response response;
    @SuppressWarnings("unused")
    private ValidatableResponse json;
    private RequestSpecification request;
    public List<HashMap<String,String>> datamap;
    @Before
    public void beforeScenario()
    {
        datamap = DataHelper.data("./src/main/resources/We_API_TDATA.xlsx","Score");
    }

@Given("^customer provides ellucian endpoint with address at excel row \"([^\"]*)\" dataset$")
public void customer_provides_ellucian_endpoint_with_address_at_excel_row_dataset(String arg1) throws Throwable {
    int index = Integer.parseInt(arg1)-1;
    elluciansteps ell=new elluciansteps();
    RestAssured.baseURI= DataAccessConf.get().gethost();

    request=given().
            header("Content-Type","application/vnd.hedtech.integration.v6+json").
            header("authorization",DataAccessConf.get().getapikey()).
            request().body(ell.getellucianpayload(index,datamap)).log().body().log().uri().log().headers();



    }

    @When("^post request to ellucian$")
    public void post_request_to_ellucian() throws Throwable {
        response = request.when().post(resources.getellucian());
        Reporter.addStepLog("response"+response.prettyPrint());

    }

    @Then("^the status code should be matching for ellucian \"([^\"]*)\"$")
    public void the_status_code_should_be_matching_for_ellucian(String arg1) throws Throwable {
        int index = Integer.parseInt(arg1)-1;
        json = response.then().statusCode(Integer.valueOf(datamap.get(index).get("Statuscode"))).log().body();
        String responseString=response.asString();
        Reporter.addStepLog("Response code"+" "+response.statusCode());
        Assert.assertNotNull(responseString);
    }
    public ellucian getellucianpayload(int index,List<HashMap<String,String>> datamap)
    {
        Gson gson = new Gson();

        PersonName perobject=new PersonName(datamap.get(index).get("Firstname"),datamap.get(index).get("LastName"));
        PrimaryAddress priobject=new PrimaryAddress(datamap.get(index).get("Zip"),datamap.get(index).get("City"),datamap.get(index).get("Address"),datamap.get(index).get("State"));
        ContactDetails conobject=new ContactDetails(datamap.get(index).get("Phone"),datamap.get(index).get("Email"),priobject);
        ellucian ellucianobject=new ellucian(perobject,conobject,"28e19624-181a-467d-a00f-1d09230c845e");
        String formattedjson=new GsonBuilder().setPrettyPrinting()
                .create().toJson(ellucianobject);
        Reporter.addStepLog("Request "+" "+formattedjson);
        return ellucianobject;
    }
    @Then("^validate Schema for ellucian$")
    public void validate_Schema_for_ellucian() throws Throwable {
        response.then().assertThat().body(matchesJsonSchemaInClasspath("ellucian.json"));
    }
}
