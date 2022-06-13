package com.qa.responseverification;


import com.qa.utils.ExtentReportListener;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import org.testng.Assert;
import org.json.*;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    public static void verifyFemales(Response response){
        String name;
        List<String> female_name = validFemalesName();
        try {
            JSONObject jsonObject = new JSONObject(response.getBody().asString());
            if (jsonObject.has("results") || jsonObject.get("results") != null) {
                JSONArray jsonArray = (JSONArray) jsonObject.get("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    if (jsonObject1.get("gender").equals("female")) {
                        name = jsonObject1.get("name").toString();
                        Assert.assertTrue(female_name.contains(name));
                        test.log(LogStatus.PASS, name+" is valid" );
                        //female_name.add(jsonObject1.get("name").toString());
                    }
                }

            } else {
                test.log(LogStatus.ERROR, "key is not present");
            }
        }catch(AssertionError e){
            test.log(LogStatus.ERROR, "name is not valid");
        }catch (Exception e){
            test.log(LogStatus.ERROR, e.fillInStackTrace());
        }

    }

    public static ArrayList<String> validFemalesName(){
        ArrayList<String> names = new ArrayList<>();
        names.add("Leia Organa");
        names.add("Beru Whitesun lars");
        return names;
    }
    public static void verifyResponseContent(Response response, String Key, String expectedValue){

    }

}
