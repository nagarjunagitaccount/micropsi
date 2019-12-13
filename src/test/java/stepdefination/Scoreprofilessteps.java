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
import org.json.JSONObject;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Scoreprofilessteps {
    private Response response;
    private String batchID;
    @SuppressWarnings("unused")
    private ValidatableResponse json;
    private RequestSpecification request;
    public List<HashMap<String,String>> datamap;
    @Before
    public void beforeScenario()
    {
        datamap = DataHelper.data("./src/main/resources/We_API_TDATA.xlsx","Score");
    }
/*
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
        Scoreprofilessteps sc=new Scoreprofilessteps();
        RestAssured.baseURI= DataAccessConf.get().gethost();
        Scoreprofile score=new Scoreprofile(datamap.get(index).get("Firstname"),datamap.get(index).get("LastName"),datamap.get(index).get("Address"),"",datamap.get(index).get("City"),datamap.get(index).get("State"),datamap.get(index).get("Zip"),datamap.get(index).get("Email"),datamap.get(index).get("Phone"),sc.getmodelsobject(count,index,datamap));
        request=given().
                header("Content-Type","application/json").
                header("authorization",DataAccessConf.get().getapikey()).
                request().body(score).log().body().log().uri().log().headers();
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
    }

    @Then("^the status code should be matching for scoreone \"([^\"]*)\"$")
    public void the_status_code_should_be_matching_for_scoreone(String arg1) throws Throwable {
        int index = Integer.parseInt(arg1)-1;
        json = response.then().statusCode(Integer.valueOf(datamap.get(index).get("Statuscode"))).log().body();
        String responseString=response.asString();
        Assert.assertNotNull(responseString);
    }*/
@Given("^customer provides Scoreprofiles endpoint with profiles at excel row \"([^\"]*)\" and \"([^\"]*)\"and \"([^\"]*)\" dataset$")
public void customer_provides_Scoreprofiles_endpoint_with_profiles_at_excel_row_and_and_dataset(String arg1, String arg2, String arg3) throws Throwable {
    int index = Integer.parseInt(arg1)-1;

    int endindex = Integer.parseInt(arg2);
    int count = Integer.parseInt(arg3);
   // Scoreonesteps sc=new Scoreonesteps();
    Scoreprofilessteps sc=new Scoreprofilessteps();
    sc.getprofilesforscore(index,endindex,count,datamap);
    RestAssured.baseURI= DataAccessConf.get().gethost();
    //Scoreprofile score=new Scoreprofile(datamap.get(index).get("Firstname"),datamap.get(index).get("LastName"),datamap.get(index).get("Address"),"",datamap.get(index).get("City"),datamap.get(index).get("State"),datamap.get(index).get("Zip"),datamap.get(index).get("Email"),datamap.get(index).get("Phone"),sc.getmodelsobject(count,index,datamap));
    request=given().
            header("Content-Type","application/json").
            header("authorization",DataAccessConf.get().getapikey()).
            request().body( sc.getprofilesforscore(index,endindex,count,datamap)).log().body().log().uri().log().headers();
}

    @When("^post request to score profiles$")
    public void post_request_to_score_profiles() throws Throwable {
        response = request.when().post(resources.getscorebyprofiles());
        batchID=response.jsonPath().getString("batchID");
    }
    @When("^post request to profiles score$")
    public void post_request_to_profiles_score() throws Throwable {
        response = request.when().post(resources.getscorebyprofilesRenamed());
        batchID=response.jsonPath().getString("batchID");
    }

    @Then("^the status code should be matching for profiles \"([^\"]*)\"$")
    public void the_status_code_should_be_matching_for_profiles(String arg1) throws Throwable {
        int index = Integer.parseInt(arg1)-1;
        json = response.then().statusCode(Integer.valueOf(datamap.get(index).get("Statuscode"))).log().body();
        String responseString=response.asString();
        Assert.assertNotNull(responseString);
    }
    public Scoreprofiles getprofilesforscore(int startindex,int endindex,int count,List<HashMap<String,String>> datamap)
    {
        List<Scoreprofile> profiles = new ArrayList<>() ;
        Scoreonesteps sc=new Scoreonesteps();
        for(int i=startindex;i<=endindex;i++)
        {
            Scoreprofile summary=new Scoreprofile(datamap.get(i).get("Firstname"),datamap.get(i).get("LastName"),datamap.get(i).get("Address"),"",datamap.get(i).get("City"),datamap.get(i).get("State"),datamap.get(i).get("Zip"),datamap.get(i).get("Email"),datamap.get(i).get("Phone"),sc.getmodelsobject(count,i,datamap));
            sc.getmodelsobject(count,i,datamap);
            profiles.add(summary);
        }

        Scoreprofiles profilesobject=new Scoreprofiles(profiles);
        return  profilesobject;
    }

    @Then("^call jobstatus endpoint to check the status of the batch$")
    public void call_jobstatus_endpoint_to_check_the_status_of_the_batch() throws Throwable {
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
                Findmanysteps.delay();
                continue outer;
            }

        }
    }
    @Then("^call scoreprofiles results endpoint with batchid$")
    public void call_scoreprofiles_results_endpoint_with_batchid() throws Throwable {
        RestAssured.baseURI = DataAccessConf.get().gethost();

        response = given().pathParam("batch_id", batchID).
                header("Content-Type", "application/json").log().uri().
                header("authorization", DataAccessConf.get().getapikey()).
                when().get(resources.getscoreprofileresults());
        String responseString = response.asString();
        System.out.println(responseString);
        Assert.assertNotNull(responseString);
    }
    @Then("^call profiles score results endpoint with batchid$")
    public void call_profilesscore_results_endpoint_with_batchid() throws Throwable {
        RestAssured.baseURI = DataAccessConf.get().gethost();

        response = given().pathParam("batch_id", batchID).
                header("Content-Type", "application/json").log().uri().
                header("authorization", DataAccessConf.get().getapikey()).
                when().get(resources.getscoreprofileresultsRenamed());
        String responseString = response.asString();
        System.out.println(responseString);
        Assert.assertNotNull(responseString);
    }
}

