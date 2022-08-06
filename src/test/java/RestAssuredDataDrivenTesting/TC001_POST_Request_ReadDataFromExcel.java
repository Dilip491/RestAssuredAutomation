package RestAssuredDataDrivenTesting;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class TC001_POST_Request_ReadDataFromExcel {


    @Test(dataProvider = "readbookdatausingexcel")
    void createnewBook(String etitle,String edesc,String ecount,String eexcerpt,String edate){
        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1";
        RequestSpecification httprequest=RestAssured.given();

        JSONObject requestparams=new JSONObject();
        requestparams.put("tile",etitle);
        requestparams.put("description",edesc);
        requestparams.put("pageCount",ecount);
        requestparams.put("excerpt",eexcerpt);
        requestparams.put("publishDate",edate);

        httprequest.header("Content-Type","application/json");
        httprequest.body(requestparams.toJSONString());

        Response response=httprequest.request(Method.POST,"/Books");

        int statuscode= response.getStatusCode();
        System.out.println("The Status code is :"+statuscode);
        Assert.assertEquals(statuscode,200);

        String responsebody=response.getBody().asString();
        System.out.println("The response body is :"+responsebody);
        Assert.assertTrue(responsebody!=null);

        Assert.assertEquals(responsebody.contains(etitle),true);
        Assert.assertEquals(responsebody.contains(edesc),true);
        Assert.assertEquals(responsebody.contains(ecount),true);
        Assert.assertEquals(responsebody.contains(eexcerpt),true);
        Assert.assertEquals(responsebody.contains(edate),true);


    }

      @DataProvider(name="readbookdatausingexcel")
      Object[][] getbookdata() throws IOException {
            String filepath=System.getProperty("user.dir")+"/src/test/java/RestAssuredDataDrivenTesting/Datadriven.xlsx";
            int rowcount=XLUtility.getrowcount(filepath,"Sheet1");
            int cellcount=XLUtility.getcellcount(filepath,"Sheet1",1);
            String empdata[][]=new String[rowcount][cellcount];

            for(int i=1;i<=rowcount;i++){
                for (int j=0;j<cellcount;j++){
                    empdata[i-1][j]=XLUtility.getcelldata(filepath,"Sheet1",i,j);

                }
            } return empdata;

      }

    }


