package com.teleflow.api.events;

import com.teleflow.api.events.pojos.BulkTriggerEventRequest;
import com.teleflow.api.events.requests.TriggerEventRequest;
import com.teleflow.api.events.responses.BulkTriggerEventResponse;
import com.teleflow.api.events.responses.CancelEventResponse;
import com.teleflow.api.events.responses.TriggerEventResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventsApi {
    String ENDPOINT = "events/trigger";

    @POST(ENDPOINT)
    Call<TriggerEventResponse> triggerEvent(@Body TriggerEventRequest request);

    @POST(ENDPOINT + "/bulk")
    Call<BulkTriggerEventResponse> bulkTriggerEvent(@Body BulkTriggerEventRequest request);

    @POST(ENDPOINT + "/broadcast")
    Call<TriggerEventResponse> broadcastEvent(@Body TriggerEventRequest request);

    @DELETE(ENDPOINT + "/{transactionId}")
    Call<CancelEventResponse> cancelTriggeredEvent(@Path("transactionId") String transactionId);
}
