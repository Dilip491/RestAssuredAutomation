package RestAssuredAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC005_Get_Request_ValidateResponseFields {

    @Test
    void validateResponsefields(){
        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/Books/";
        RequestSpecification httprequest=RestAssured.given();

        Response response=httprequest.request(Method.GET,"/1");

        String responsebody=response.getBody().asString();
        System.out.println("The response body is :"+responsebody);

        Assert.assertEquals(responsebody.contains("Book 1"),true);

        JsonPath jsonPath=response.jsonPath();

        int id=jsonPath.get("id");
        System.out.println(id);

    }
}
