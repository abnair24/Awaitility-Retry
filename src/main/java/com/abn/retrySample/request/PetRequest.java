package com.abn.retrySample.request;

import com.abn.retrySample.response.GetPetByIdResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Reporter;

import java.math.BigInteger;

import static io.restassured.RestAssured.given;

public class PetRequest {

    public GetPetByIdResponse getPetById(int id) {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/store/order/"+id);

        Reporter.log(String.format("%s - %s ",response.getStatusCode(),response.asString()),true);

        GetPetByIdResponse getPetByIdResponse = response.as(GetPetByIdResponse.class);
        getPetByIdResponse.setStatusCode(response.getStatusCode());

        return getPetByIdResponse;

    }
}
