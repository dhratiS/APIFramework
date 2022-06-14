package com.qa.responseverification;


import com.qa.pojo.PeoplePojo;
import com.qa.pojo.Result;
import com.qa.utils.ExtentReportListener;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;

public class ResponseVerificationUsingPOJO extends ExtentReportListener {
    //verify all name who does not have vehicles
    public static void verifyNameWhoDoesNotHaveVehicles(Response response){
        ArrayList<String> nonVehicleUser = nonVehicleUseList();
        try{
            PeoplePojo peoplePojo = response.as(PeoplePojo.class);
            ArrayList<Result> results = peoplePojo.getResults();
            for(int i = 0; i < results.size(); i++){
                if(results.get(i).getVehicles().isEmpty()){
                    Assert.assertTrue(nonVehicleUser.contains(results.get(i).getName()));
                    test.log(LogStatus.PASS, results.get(i).getName()+" is present");
                }
            }
        }catch (AssertionError e){
            test.log(LogStatus.FAIL, "assertion failed");

        }catch (Exception e){
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }
    public static ArrayList<String> nonVehicleUseList(){
        ArrayList<String> rr = new ArrayList<>();
        rr.add("C-3PO");
        rr.add("R2-D2");
        rr.add("Darth Vader");
        rr.add("Owen Lars");
        rr.add("Biggs Darklighter");
        rr.add("Beru Whitesun lars");
        rr.add("R5-D4");


        return rr;
    }
}