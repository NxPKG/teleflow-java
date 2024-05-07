package com.teleflow.api.events;

import com.teleflow.api.events.pojos.BulkTriggerEventRequest;
import com.teleflow.api.events.requests.TriggerEventRequest;
import com.teleflow.api.events.responses.BulkTriggerEventResponse;
import com.teleflow.api.events.responses.CancelEventResponse;
import com.teleflow.api.events.responses.TriggerEventResponse;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import lombok.RequiredArgsConstructor;
import retrofit2.Response;

import java.io.IOException;

@RequiredArgsConstructor
public class EventsHandler {

    private final RestHandler restHandler;
    private final EventsApi eventsApi;

    public EventsHandler(RestHandler restHandler){
        this.restHandler = restHandler;
        this.eventsApi = restHandler.buildRetrofit().create(EventsApi.class);
    }

    public TriggerEventResponse triggerEvent(TriggerEventRequest request) throws IOException, TeleflowNetworkException {
        Response<TriggerEventResponse> response = eventsApi.triggerEvent(request).execute();
        return restHandler.extractResponse(response);
    }

    public BulkTriggerEventResponse bulkTriggerEvent(BulkTriggerEventRequest request) throws IOException, TeleflowNetworkException {
        Response<BulkTriggerEventResponse> response = eventsApi.bulkTriggerEvent(request).execute();
        return restHandler.extractResponse(response);
    }

    public TriggerEventResponse broadcastEvent(TriggerEventRequest request) throws IOException, TeleflowNetworkException {
        Response<TriggerEventResponse> response = eventsApi.broadcastEvent(request).execute();
        return restHandler.extractResponse(response);
    }

    public CancelEventResponse cancelTriggeredEvent(String transactionId) throws IOException, TeleflowNetworkException {
        Response<CancelEventResponse> response = eventsApi.cancelTriggeredEvent(transactionId).execute();
        return restHandler.extractResponse(response);
    }
}