package TestRunner;		

import java.io.File;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;		



import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="src/test/java/features", glue = "stepdefination")
public class CucumberRunner extends AbstractTestNGCucumberTests
{
	

 
 
}