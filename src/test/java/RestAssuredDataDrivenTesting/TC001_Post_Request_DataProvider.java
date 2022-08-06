package RestAssuredDataDrivenTesting;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC001_Post_Request_DataProvider {

    @Test(dataProvider = "newbooksdataprovider")
    void createDatausingDataProvider(String etitle,String edesc,String eexcerpt,String edate) {

        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1";
        RequestSpecification httprequest=RestAssured.given();

        JSONObject requestparams=new JSONObject();

        requestparams.put("title",etitle);
        requestparams.put("description",edesc);
        requestparams.put("excerpt",eexcerpt);
        requestparams.put("publishDate",edate);

        httprequest.header("Content-Type","application/json");
        httprequest.body(requestparams.toJSONString());

        Response response=httprequest.request(Method.POST,"/Books");
        String responsebody=response.getBody().asString();
        System.out.println("The response body is :"+responsebody);

        Assert.assertEquals(responsebody.contains(etitle),true);
        Assert.assertEquals(responsebody.contains(edesc),true);
        Assert.assertEquals(responsebody.contains(eexcerpt),true);
        Assert.assertEquals(responsebody.contains(edate),true);


    }

    @DataProvider(name="newbooksdataprovider")
    Object[][] getbookdata(){
        String[][] bookdata={{"Book 10","practice Book10","Dummy data10","2022-08-02T09:35:28.086Z"},
                            {"Book 11","practice Book11","Dummy data11","2022-08-02T10:35:28.086Z"},
                            {"Book 12","practice Book12","Dummy data12","2022-08-02T11:35:28.086Z"}};
        return(bookdata);
    }
}
