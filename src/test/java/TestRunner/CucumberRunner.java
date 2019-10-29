package TestRunner;		

import java.io.File;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;		



import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features="src/test/java/features", glue = "stepdefination",
        plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "testng:target/cucumber-reports/TestngCucumber.xml",
                "html:target/cucumber-reports"}
)

public class CucumberRunner extends AbstractTestNGCucumberTests
{
	

 
 
}