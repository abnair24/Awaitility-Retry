package com.abn.retrySample.tests;

import com.abn.retrySample.request.PetRequest;
import com.abn.retrySample.response.GetPetByIdResponse;
import com.abn.retrySample.retry.GenericRetryClient;
import com.abn.retrySample.retry.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RetrySampleTest {

    @Test
    public void getPetByIdTest() throws Exception {

        GetPetByIdResponse getPetByIdResponse = new GenericRetryClient<GetPetByIdResponse>()
                .runCallable(() -> new PetRequest().getPetById(9));

        Assert.assertEquals(getPetByIdResponse.getStatusCode(),200);
    }


    @Test
    public void getPetByIdGenericRetryTest() throws Exception {
        GetPetByIdResponse getPetByIdResponse = new GenericRetryClient<GetPetByIdResponse>()
                .runCallable(() -> new PetRequest().getPetById(1));


    }

    @Test
    public void getPetByIdRetryTest() throws Exception {
        GetPetByIdResponse getPetByIdResponse  = new Retry().run(1);
        Assert.assertEquals(getPetByIdResponse.getStatusCode(),200);
    }
}
