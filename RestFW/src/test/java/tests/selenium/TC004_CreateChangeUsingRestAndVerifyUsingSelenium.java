package tests.selenium;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;
import lib.selenium.PreAndPost;
import lib.utils.HTMLReporter;
import pages.selenium.LoginPage;

public class TC004_CreateChangeUsingRestAndVerifyUsingSelenium extends PreAndPost{

	@BeforeTest
	public void setValues() {

		testCaseName = "Search Change Request (Using Selenium) After Creating Change Request (Using REST Assured)";
		testDescription = "Create Change Request (Using REST Assured) and Search with Selenium";
		nodes = "Change Management";
		authors = "Vinoth";
		category = "UI & API";
		dataSheetName = "TC004";

	}

	@Test(dataProvider = "fetchData")
	public void createChange(String filter) {

		// Post the request
		Response response = RESTAssuredBase.post("table/change_request");

		RESTAssuredBase.verifyResponseCode(response, 201);

		//Verify the Content by Specific Key
		changeRequestNumber = RESTAssuredBase.getContentWithKey(response, "result.number");

		// Selenium - Find Change Request		
		new LoginPage(driver,test)
			.loginApp()
			.searchUsingFilter(filter)
			.clickAll() 
			.typeAndEnterSearch(changeRequestNumber)
			.verifyResult(changeRequestNumber);
	
	}


}





