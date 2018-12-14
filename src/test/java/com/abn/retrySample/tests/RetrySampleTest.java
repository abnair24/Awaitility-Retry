package com.abn.retrySample.tests;

import com.abn.retrySample.request.PetRequest;
import com.abn.retrySample.response.GetPetByIdResponse;
import com.abn.retrySample.retry.RetryClient;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class RetrySampleTest {

    @Test
    public void getPetById() throws Exception {

        GetPetByIdResponse getPetByIdResponse = new RetryClient<GetPetByIdResponse>()
                .runCallable(() -> new PetRequest().getPetById(9));

        Assert.assertEquals(getPetByIdResponse.getStatusCode(),200);
    }


    @Test
    public void getPetByIdRetryTest() throws Exception {
        GetPetByIdResponse getPetByIdResponse = new RetryClient<GetPetByIdResponse>()
                .runCallable(() -> new PetRequest().getPetById(1));


    }
}
