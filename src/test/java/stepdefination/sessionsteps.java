package stepdefination;

import com.api.pojo.Session;
import com.api.resources.resources;
import com.we.api.utilities.DataAccessConf;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class sessionsteps {
    private Response response;
    @SuppressWarnings("unused")
    private ValidatableResponse json;
    private RequestSpecification request;

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

    }

    @Then("^validate response code \"([^\"]*)\"$")
    public void validate_response_code(String statuscode) throws Throwable {
        json = response.then().statusCode(Integer.valueOf(statuscode)).log().body().and().body("$", hasKey("_id"));
        String responseString=response.asString();
        Assert.assertNotNull(responseString);


    }
}
