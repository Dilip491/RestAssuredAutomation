package RestAssuredAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_Get_Request_HeaderValidation {

    @Test
    void validateHeader(){

        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/Books/";
        RequestSpecification httprequest=RestAssured.given();

        Response response=httprequest.request(Method.GET,"7");

        String responsebody=response.getBody().asString();
        System.out.println("Response body is :"+responsebody);

        String contenttype=response.header("Content-Type");
        System.out.println("Content type is :"+contenttype);
        Assert.assertEquals(contenttype,"application/json; charset=utf-8; v=1.0");

        String servername=response.header("Server");
        System.out.println("Server name is :"+servername);
        Assert.assertEquals(servername,"Kestrel");

        String encoding=response.header("Transfer-Encoding");
        System.out.println("Transfer encoding is :"+encoding);
        Assert.assertEquals(encoding,"chunked");
    }
}
