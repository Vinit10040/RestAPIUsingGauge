package com.thoughtworks.gauge.maven.EDGE_title_01;


import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import Config.ConfigReader;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Type;
import java.util.List;
import static org.junit.Assert.*;

public class Edge_Api {
   ConfigReader configreader=new ConfigReader();
   public Response response;
   

   
   @Step("User call getAllocatedSurveys api")
   public void getAllocatedBysurveys()
   {
	 //RestAssured.baseURI = "http://innovate-stage-209385288.us-east-1.elb.amazonaws.com/api/v2";
	  RestAssured.baseURI = configreader.getBaseURI();
	   
      response = RestAssured.given().header("X-access-token",configreader.getStageToken())
    		   .log().all().contentType(ContentType.JSON)
               .get(Endpoints.GETALLSURVEYS).andReturn();
              

   }

  
   @Step("User call getSurveytargeting api")
   public void getTargeting()
   {
	   RestAssured.baseURI = configreader.getBaseURI();
	   response = RestAssured.given().header("X-access-token",configreader.getStageToken())
    		   .log().all().contentType(ContentType.JSON)
               .get(Endpoints.GETSURVEYTARGETING).andReturn();
   }
   
   @Step("user should get Ok response")
   public void userShouldGetOkResponse()
   {
	   assertEquals(response.statusCode(),200);
	   //assertEquals(1,1);

		   
   }
   @Step("User call getAllocatedSurveysBySurveyId api")
   public void getAllocatedSurveysById() {
	   
	   RestAssured.baseURI = configreader.getBaseURI();
	   response = RestAssured.given().header("X-access-token",configreader.getStageToken())
    		   .log().all().contentType(ContentType.JSON)
               .get(Endpoints.GETALLOCATEDSURVEYSBYID).andReturn();
  
   }
   @Step("API should have CPI field")   
   public void getAllocatedCPIField()
   {
	   ResponseBody body=  response.getBody();
	   String bodyStringValue = body.asString();
	   Assert.assertTrue(bodyStringValue.contains("CPI"));
	   Assert.assertTrue(bodyStringValue.contains("Country"));
	   Assert.assertTrue(bodyStringValue.contains("LOI"));
	   
   }
   @Step("Validate response time of getAllocatedSurveysBySurveyId api")
   public void apiResponseTime()
   {
	   RestAssured.baseURI = configreader.getBaseURI();
	   response = (Response) RestAssured.given().header("X-access-token",configreader.getStageToken())
    		   .log().all().contentType(ContentType.JSON)
               .get(Endpoints.GETALLOCATEDSURVEYSBYID).then().time(lessThan(1000L)); 
   }
}
