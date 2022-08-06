package RestAssuredAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_Get_Request {

    @Test
    void getBookingId(){

       //Specify the URI
        RestAssured.baseURI="https://reqres.in/api";
        //https://dummy.restapiexample.com/

        //Create the object of the request
        RequestSpecification httprequest=RestAssured.given();

        //send the request and get the response
        Response response=httprequest.request(Method.GET,"/users/1");

        System.out.println(response);
        String responsebody=response.getBody().asString();
        System.out.println("Response body is :"+responsebody);

        int statuscode=response.getStatusCode();
        System.out.println("The Statuscode is :"+statuscode);
        Assert.assertEquals(statuscode,200);

        String statusline=response.getStatusLine();
        System.out.println("The status line is :"+statusline);
        Assert.assertEquals(statusline,"HTTP/1.1 200 OK");


    }
}
