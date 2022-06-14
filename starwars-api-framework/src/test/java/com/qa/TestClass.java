package com.qa;

import com.qa.apiconfigs.APIPath;
import com.qa.apiconfigs.HeaderConfig;
import com.qa.responseverification.ResponseVerificationUsingPOJO;
import com.qa.responseverification.ResponseVerifications;
import com.qa.utils.ConfigReader;
import com.qa.utils.ExtentReportListener;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
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
            //(expectedExceptions = JsonMappingException.class)
    public void verifyWithPOJO(){
        test.log(LogStatus.INFO, "Starting test to verify with pojos ...");
        try{
        Response responsePojo = given().headers(HeaderConfig.defaultHeaders()).
                                      expect().defaultParser(Parser.JSON).
                                        when().get(APIPath.GET_LIST_OF_PEOPLE);
        test.log(LogStatus.INFO,responsePojo.getBody().prettyPrint());
        ResponseVerificationUsingPOJO.verifyNameWhoDoesNotHaveVehicles(responsePojo);
        //test.log(LogStatus.INFO, responsePojo.getCount()+" this is the count");
        //test.log(LogStatus.INFO, responsePojo.getResults().get(1).getVehicles()+" this is result(1)");

        }catch(Exception e){
            test.log(LogStatus.ERROR, e.fillInStackTrace());
        }
    }

    //@Test
    public void verifyAPI(){
        test.log(LogStatus.INFO, "Starting test ...");
        response = given().
                    headers(HeaderConfig.defaultHeaders()).
                when().get(APIPath.GET_LIST_OF_PEOPLE).then().extract().response();
        test.log(LogStatus.INFO,response.getBody().prettyPrint());
        ResponseVerifications.verifyStatusCode(response, 200);
        ResponseVerifications.verifyFemales(response);

    }

}
