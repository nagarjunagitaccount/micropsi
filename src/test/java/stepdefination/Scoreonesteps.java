package stepdefination;

import com.api.pojo.*;
import com.api.resources.resources;
import com.cucumber.listener.Reporter;
import com.google.gson.GsonBuilder;
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
import org.json.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;

public class Scoreonesteps {
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

    @Given("^customer provides Scoreone endpoint with email at excel row \"([^\"]*)\" dataset$")
    public void customer_provides_Scoreone_endpoint_with_email_at_excel_row_dataset(String arg1) throws Throwable {
        int index = Integer.parseInt(arg1)-1;

        RestAssured.baseURI= DataAccessConf.get().gethost();

        ScoreEmail scoreemai=new ScoreEmail(datamap.get(index).get("Firstname"),datamap.get(index).get("LastName"),datamap.get(index).get("Email"),datamap.get(index).get("model1"));
        request=given().
                header("Content-Type","application/json").
                header("authorization",DataAccessConf.get().getapikey()).
                request().body(scoreemai).log().body().log().uri().log().headers();
    }

    @When("^post request to scoreone address$")
    public void post_request_to_scoreone_address() throws Throwable {
        response = request.when().post(resources.getscoreonebyaddress());
        Reporter.addStepLog("response"+response.prettyPrint());
    }
    @Given("^customer provides Scoreone endpoint with Address at excel row \"([^\"]*)\" dataset$")
    public void customer_provides_Scoreone_endpoint_with_Address_at_excel_row_dataset(String arg1) throws Throwable {
        int index = Integer.parseInt(arg1)-1;

        RestAssured.baseURI= DataAccessConf.get().gethost();
        ScoreAddress saddress=new ScoreAddress(datamap.get(index).get("Firstname"),datamap.get(index).get("LastName"),datamap.get(index).get("Address"),"",datamap.get(index).get("City"),datamap.get(index).get("State"),datamap.get(index).get("Zip"),datamap.get(index).get("model1"));
        JSONObject obj=new JSONObject(saddress);
        System.out.println(obj.toString());
        request=given().
                header("Content-Type","application/json").
                header("authorization",DataAccessConf.get().getapikey()).
                request().body(saddress).log().body().log().uri().log().headers();
    }

    @When("^post request to scoreone email$")
    public void post_request_to_scoreone_email() throws Throwable {
        response = request.when().post(resources.getscoreonebyemail());
        Reporter.addStepLog("response"+response.prettyPrint());
    }
    @Given("^customer provides Scoreone endpoint with phone at excel row \"([^\"]*)\" dataset$")
    public void customer_provides_Scoreone_endpoint_with_phone_at_excel_row_dataset(String arg1) throws Throwable {
        int index = Integer.parseInt(arg1)-1;

        RestAssured.baseURI= DataAccessConf.get().gethost();
        ScorePhone phonea=new ScorePhone(datamap.get(index).get("Firstname"),datamap.get(index).get("LastName"),datamap.get(index).get("Phone"),datamap.get(index).get("model1"));
        JSONObject obj=new JSONObject(phonea);
        System.out.println(obj.toString());
        request=given().
                header("Content-Type","application/json").
                header("authorization",DataAccessConf.get().getapikey()).
                request().body(phonea).log().body().log().uri().log().headers();
    }

    @When("^post request to scoreone phone$")
    public void post_request_to_scoreone_phone() throws Throwable {
        response = request.when().post(resources.getscoreonebyphone());
        Reporter.addStepLog("response"+response.prettyPrint());
    }
    @Given("^customer provides Scoreone endpoint with summary at excel row \"([^\"]*)\" dataset$")
    public void customer_provides_Scoreone_endpoint_with_summary_at_excel_row_dataset(String arg1) throws Throwable {
        int index = Integer.parseInt(arg1)-1;

        RestAssured.baseURI= DataAccessConf.get().gethost();
        model model=new model(datamap.get(index).get("model1"));

        List<model> models = new ArrayList<>();
       //models.add(model);
        models.add(model);

        Scoreprofile score=new Scoreprofile(datamap.get(index).get("Firstname"),datamap.get(index).get("LastName"),datamap.get(index).get("Address"),"",datamap.get(index).get("City"),datamap.get(index).get("State"),datamap.get(index).get("Zip"),datamap.get(index).get("Email"),datamap.get(index).get("Phone"),models);
       JSONObject obj=new JSONObject(score);
        System.out.println(obj.toString());

        request=given().
                header("Content-Type","application/json").
                header("authorization",DataAccessConf.get().getapikey()).
                request().body(score).log().body().log().uri().log().headers();
    }
    @Given("^customer provides Scoreone endpoint with summary at excel row \"([^\"]*)\" and \"([^\"]*)\" dataset$")
    public void customer_provides_Scoreone_endpoint_with_summary_at_excel_row_and_dataset(String arg1, String arg2) throws Throwable {
        int index = Integer.parseInt(arg1)-1;
        int count = Integer.parseInt(arg2);
        Scoreonesteps sc=new Scoreonesteps();
        RestAssured.baseURI= DataAccessConf.get().gethost();
        Scoreprofile score=new Scoreprofile(datamap.get(index).get("Firstname"),datamap.get(index).get("LastName"),datamap.get(index).get("Address"),"",datamap.get(index).get("City"),datamap.get(index).get("State"),datamap.get(index).get("Zip"),datamap.get(index).get("Email"),datamap.get(index).get("Phone"),sc.getmodelsobject(count,index,datamap));
        request=given().
                header("Content-Type","application/json").
                header("authorization",DataAccessConf.get().getapikey()).
                request().body(score).log().body().log().uri().log().headers();
        String formattedjson=new GsonBuilder().setPrettyPrinting()
                .create().toJson(score);
        Reporter.addStepLog("Request "+" "+formattedjson);
    }

    public List<model> getmodelsobject(int count,int index,List<HashMap<String,String>> datamap) {

        List<model> models = new ArrayList<>();
        if (count==1)
        {
            model model1=new model(datamap.get(index).get("model1"));
            models.add(model1);

        }else if(count==2)
        {

            model model1=new model(datamap.get(index).get("model1"));
            models.add(model1);
            model model2=new model(datamap.get(index).get("model2"));
            models.add(model2);
        }
        else {
            model model1=new model(datamap.get(index).get("model1"));
            models.add(model1);
            model model2=new model(datamap.get(index).get("model2"));
            models.add(model2);
            model model3=new model(datamap.get(index).get("model3"));
            models.add(model3);

        }

        return models;
    }

    @When("^post request to score profile$")
    public void post_request_to_score_profile() throws Throwable {
        response = request.when().post(resources.getscorebyprofile());
        Reporter.addStepLog("response"+response.prettyPrint());
    }
    @When("^post request to profile score$")
    public void post_request_to_profile_score() throws Throwable {
        response = request.when().post(resources.getscorebyprofileRenamed());
        Reporter.addStepLog("response"+response.prettyPrint());
    }

    @Then("^the status code should be matching for scoreone \"([^\"]*)\"$")
    public void the_status_code_should_be_matching_for_scoreone(String arg1) throws Throwable {
        int index = Integer.parseInt(arg1)-1;
        json = response.then().statusCode(Integer.valueOf(datamap.get(index).get("Statuscode"))).log().body();
        String responseString=response.asString();
        Reporter.addStepLog("Response code"+" "+response.statusCode());
        Assert.assertNotNull(responseString);
    }
    @Then("^validate Schema for Scoreprofile$")
    public void validate_Schema_for_Scoreprofile() throws Throwable {
        response.then().assertThat().body(matchesJsonSchemaInClasspath("ProfileScore.json"));
    }
}

