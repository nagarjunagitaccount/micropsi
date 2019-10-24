package com.we.api.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class reports 
{
	

	public static ExtentReports eReport;
	public static com.relevantcodes.extentreports.ExtentTest eTest;
	public static String dateStr;

	public static String notifSubject = "WE Platform Selenium - All Tests Passed";
	public static String testResults = System.lineSeparator() + "TEST RESULTS";
	public void configureExtentReport() throws IOException {
		System.out.println("Starting to configure test report");

		SimpleDateFormat df = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		dateStr = df.format(new java.util.Date());
		final String filePath = System.getProperty("user.dir") + "/reports/TestReport.html";
		eReport = new ExtentReports(filePath, true);
		System.out.println("Configured test report");
	}

	public void startReport(String testName) {
		System.out.println("Test Name : " + testName);
		eTest = eReport.startTest(testName);
	}

	public void printOnReport(String messageToPrint) {
		System.out.println(messageToPrint);
		eTest.log(LogStatus.PASS, messageToPrint);
		Reporter.log(messageToPrint);
	}

	public void print_URL_OnReport(String url) {
		System.out.println("URL : " + url);
		eTest.log(LogStatus.INFO, "URL : " + url);
		Reporter.log("URL : " + url);
	}

	public void print_Username_OnReport(String username) {
		System.out.println("User : " + username);
		eTest.log(LogStatus.INFO, "User : " + username);
		Reporter.log("User : " + username);
	}

	public void endExtentReport(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			eTest.log(LogStatus.FAIL, "Test failed : " + result.getName());
			eTest.log(LogStatus.FAIL, result.getThrowable());
		
		
			testResults += System.lineSeparator() + result.getName() + " - Failed!";
			notifSubject = "WE Platform Selenium - Failures Occurred!";

		} else if (result.getStatus() == ITestResult.SKIP) {
			testResults += System.lineSeparator() + result.getName() + " - Skipped!";
			eTest.log(LogStatus.SKIP, "Test skipped : " + result.getName());
			notifSubject = "WE Platform Selenium - Tests Were Skipped!";
		} else {
			testResults += System.lineSeparator() + result.getName() + " - Passed!";
			eTest.log(LogStatus.PASS, "Test passed : " + result.getName());
		}
		eReport.endTest(eTest);
		eReport.flush();
	}
}
