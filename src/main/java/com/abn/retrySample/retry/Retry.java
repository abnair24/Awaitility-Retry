package com.abn.retrySample.retry;

import com.abn.retrySample.request.PetRequest;
import com.abn.retrySample.response.GetPetByIdResponse;
import org.awaitility.Duration;
import java.util.concurrent.Callable;
import static org.awaitility.Awaitility.with;

public class Retry {

    private Callable<GetPetByIdResponse> retryMethod(int id) {
        return new Callable<GetPetByIdResponse>() {
            @Override
            public GetPetByIdResponse call() throws Exception {
                return new PetRequest().getPetById(id);
            }
        };
    }

    public GetPetByIdResponse run(int petId) {
        return with()
                .pollInterval(Duration.ONE_SECOND)
                .await()
                .until(() -> new PetRequest().getPetById(petId), t ->t.getStatusCode()== 200);
    }
}