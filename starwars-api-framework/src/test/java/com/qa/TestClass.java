package com.qa;

import com.qa.apiconfigs.APIPath;
import com.qa.apiconfigs.HeaderConfig;
import com.qa.responseverification.ResponseVerifications;
import com.qa.utils.ConfigReader;
import com.qa.utils.ExtentReportListener;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners(ExtentReportListener.class)
public class TestClass extends ExtentReportListener{
    ConfigReader cr;
    Response response;

    @BeforeClass
    public void init(){
        cr = new ConfigReader();
        RestAssured.baseURI = cr.readUrl();
    }

    @Test
    public void verifyAPI(){
        test.log(LogStatus.INFO, "Starting test ...");
        response = given().
                    headers(HeaderConfig.defaultHeaders()).
                when().get(APIPath.GET_LIST_OF_PEOPLE);
        test.log(LogStatus.INFO,response.getBody().prettyPrint());
        ResponseVerifications.verifyStatusCode(response, 201);

    }
}
