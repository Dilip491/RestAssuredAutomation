package RestAssuredAPI;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TC004_Get_Request_GetAllHeaders {

    @Test
    void getAllHeaders() {

        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/Books/";
        RequestSpecification httprequest=RestAssured.given();

        Response response=httprequest.request(Method.GET,"/1");

        String responsebdy=response.getBody().asString();
        System.out.println("The response body is :"+responsebdy);

         Headers allheader=response.headers();
         for(Header header:allheader){
             System.out.println(header.getName()+" -> "+header.getValue());
         }

    }
}
    