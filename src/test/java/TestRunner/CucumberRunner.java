package TestRunner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import com.vimalselvam.cucumber.listener.ExtentCucumberFormatter;
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

@CucumberOptions(
        features="src/test/java/features", glue = "stepdefination",
        plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "testng:target/cucumber-reports/TestngCucumber.xml",
                "com.cucumber.listener.ExtentCucumberFormatter" ,
                "html:target/cucumber-reports"},
        tags = {"~@findonebyaddressbasic"}
)


public class CucumberRunner extends AbstractTestNGCucumberTests
{
    @BeforeClass
    public static void setup() throws IOException {


        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_hhmmss");
        Date curDate = new Date();
        String strDate = sdf.format(curDate);
        String fileName = System.getProperty("user.dir") + "//target//Extent_Reports//" + strDate + ".html";

        File newFile = new File(fileName);
        ExtentCucumberFormatter.initiateExtentCucumberFormatter(newFile, true);

        // Loads the extent config xml to customize on the report.
        ExtentCucumberFormatter.loadConfig(new File("src/main/resources/extent-config.xml"));

        // User can add the system information as follows

        // Also you can add system information using a hash map
        Map systemInfo = new HashMap();
        systemInfo.put("Cucumber version", "v1.2.3");
        systemInfo.put("Extent Cucumber Reporter version", "v1.1.0");
        ExtentCucumberFormatter.addSystemInfo(systemInfo);


    }



}