package com.abn.retrySample.retry;

import com.abn.retrySample.response.BaseResponse;
import org.awaitility.Duration;
import org.awaitility.core.ConditionTimeoutException;
import org.awaitility.core.Predicate;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.with;

public class GenericRetryClient<T extends BaseResponse> {

    private T retryOnStatusCode(Callable<T> function, T response)  {

        T resp =null;
        try {
            resp = with()
                    .pollInterval(Duration.FIVE_HUNDRED_MILLISECONDS)
                    .conditionEvaluationListener(condition -> Reporter
                            .log(String.format("<%s> -- Remaining wait time %s ms",response.getStatusCode(),
                                    condition.getRemainingTimeInMS()),true))
                    .await()
                    .atMost(5, TimeUnit.SECONDS)
                    .until(function, isStatusSuccess());

        } catch(ConditionTimeoutException ex) {
            Assert.fail("------------ Retry Failed ------------ ");
        }
        return resp;
    }

    private static Predicate<BaseResponse> isStatusSuccess() {

        return p -> p.getStatusCode() == 200;
    }

    public T runCallable(Callable<T> function) throws Exception {

        T response = function.call();
        if( response.getStatusCode() != 200) {

            Reporter.log(String.format("Invoking retry for HTTP code <%s> ",
                    response.getStatusCode()),true);

            response = retryOnStatusCode(function, response);
        }
        return response;
    }

}
