package TestRunner;

import com.cucumber.listener.Reporter;
import com.we.api.utilities.DataAccessConf;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue= {"stepdefination"},
        plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/extentreport.html","pretty", "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "testng:target/cucumber-reports/TestngCucumber.xml",
                "html:target/cucumber-reports"},

        monochrome = true
)
public class CucumberRunnertestng
{

    @AfterClass
    public static void setup() throws FileNotFoundException {
       // FileInputStream fs = new FileInputStream("./src/main/resources/extent-config.xml");
       // InputStream stream = DataAccessConf.class.getResourceAsStream("/extent-config.xml");
       // InputStream istream = this.getClass().getClassLoader().getResourceAsStream("config.xml");
       // FileInputStream fs = new FileInputStream("./extent-config.xml");
        Reporter.loadXMLConfig("src/main/resources/extent-config.xml");
       //Reporter.loadXMLConfig(new File("../src/main/resources/extent-config.xml"));
        Reporter.setSystemInfo("Test User", System.getProperty("user.name"));
        Reporter.setSystemInfo("User Name", "TEST");
        Reporter.setSystemInfo("Application Name", "Test App ");
        Reporter.setSystemInfo("Operating System Type", System.getProperty("os.name").toString());
        Reporter.setSystemInfo("Environment", "Production");
        Reporter.setTestRunnerOutput("Test Execution Cucumber Report");
    }
}