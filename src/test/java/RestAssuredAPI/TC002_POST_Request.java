package RestAssuredAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_POST_Request {


    @Test
    void createBook(){



      RequestSpecification httprequest=RestAssured.given();
      httprequest.baseUri("https://fakerestapi.azurewebsites.net/api/v1/");

      JSONObject requestparams=new JSONObject();
        requestparams.put("id","7");
        requestparams.put("title","Book 7");
        requestparams.put("description","practice Book");
        requestparams.put("excerpt","Dummy data");
        requestparams.put("publishDate","2022-08-01T18:35:28.086Z");

        httprequest.header("Content-Type","application/json");
        httprequest.body(requestparams.toJSONString());

        Response response=httprequest.request(Method.POST,"Books");

        String responsebody=response.getBody().asString();
        System.out.println("The response body is :"+responsebody);

        int statuscode=response.getStatusCode();
        System.out.println("The status code is :"+statuscode);
        Assert.assertEquals(statuscode,200);

        String description=response.jsonPath().get("description");
        System.out.println("The description of the book is :"+description);
        Assert.assertEquals(description,"practice Book");
    }

}
