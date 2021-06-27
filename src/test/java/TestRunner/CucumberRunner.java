package TestRunner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cucumber.listener.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.cucumber.listener.ExtentCucumberFormatter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import cucumber.api.CucumberOptions;		



import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = { "src/test/java/features/"} , glue = {
        "stepdefination" }, monochrome = true, tags = {"@smoke"},
        plugin = { "pretty", "html:target/cucumber", "json:target/cucumber.json",
                "com.cucumber.listener.ExtentCucumberFormatter:output/extentreport.html" }

                )



public class CucumberRunner extends AbstractTestNGCucumberTests
{

    @AfterClass
    public static void setup() throws IOException {


      Reporter.loadXMLConfig(new File("src/main/resources/extent-config.xml"));


    }



}