package com.we.tests;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.api.pojo.Address;
import com.api.resources.resources;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.we.api.utilities.DataAccessConf;
import com.we.api.utilities.reports;

import groovyjarjarasm.asm.commons.Method;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FindoneTest  {


	
	@Test
	 public void Findonebyaddressbasic()
	 {
	 	            RestAssured.baseURI=DataAccessConf.get().gethost();
	                Address address=new Address("John","Kimberlin","5940 Via Real","Carpinteria","CA","93013");
        
	 	            Response respa=
	 	            	given()
	 	            	.log().uri().log().body().log().headers().
	 			    header("Content-Type","application/json").
	 				header("authorization",DataAccessConf.get().getapikey()).
	 				request().body(address).
	 				when().
	 				post(resources.getfindonebyaddressbybasic()).
	 				then().assertThat().statusCode(200).extract().response();
	 	            
	 			    System.out.println(respa.asString());
	 			    int statusCode = respa.getStatusCode();
	 			    
	 			    String p2g = respa.body().jsonPath().getJsonObject("giving.p2g_score.text").toString();
	 			    String haschildren =respa.body().jsonPath().getJsonObject("demographics.has_children").toString();
	 			    String fname =respa.body().jsonPath().getJsonObject("identity.first_name").toString();
	 			    String lname =respa.body().jsonPath().getJsonObject("identity.last_name").toString();

	 			   Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
	 			   Assert.assertEquals(p2g, "2|3 - Above Average", "P2gis correct");
	 			   Assert.assertEquals(haschildren, "true", "Has children details is correct");
	 			   Assert.assertEquals(fname.toUpperCase(), "JOHN", "firstname  is correct");
	 			   
	 			   Assert.assertEquals(lname.toUpperCase(), "KIMBERLIN", "Last name  is correct");
	 		
	 	          
	 }

	
}
