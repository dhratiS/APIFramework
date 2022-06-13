package com.qa.responseverification;

import com.qa.utils.ExtentReportListener;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseVerifications extends ExtentReportListener {
    public static void verifyStatusCode(Response response, int status){
        try{
            Assert.assertEquals(response.getStatusCode(), status);
            test.log(LogStatus.PASS, "code succesfully validated");
        }catch (AssertionError e){
            test.log(LogStatus.FAIL, e.fillInStackTrace());
            test.log(LogStatus.FAIL,
                    "Expected status code is :: " + status + " , instead getting :: " + response.getStatusCode());

        }catch (Exception e){
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }
    public void verifyResponseContent(Response response, String Key, String expectedValue){

    }

}
