package com.teleflow.api.events;

import com.teleflow.api.common.SubscriberRequest;
import com.teleflow.api.events.pojos.BulkTriggerEventRequest;
import com.teleflow.api.events.requests.Topic;
import com.teleflow.api.events.requests.TriggerEventRequest;
import com.teleflow.api.events.responses.BulkTriggerEventResponse;
import com.teleflow.api.events.responses.CancelEventResponse;
import com.teleflow.api.events.responses.TriggerEventResponse;
import com.teleflow.api.events.responses.TriggerEventResponseData;
import com.teleflow.api.tenants.pojos.Tenant;
import com.teleflow.common.base.TeleflowConfig;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import com.google.gson.Gson;
import junit.framework.TestCase;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EventsHandlerTest extends TestCase {

    private EventsHandler eventsHandler;
    private MockWebServer mockWebServer;

    @Override
    protected void setUp() {
        mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        eventsHandler = new EventsHandler(restHandler);
    }

    public void test_triggerEventToSubscriber() throws IOException, TeleflowNetworkException, InterruptedException {
        TriggerEventRequest triggerEventRequest = new TriggerEventRequest();
        triggerEventRequest.setName("name");

        SubscriberRequest subscriberRequest = new SubscriberRequest();
        subscriberRequest.setFirstName("fName");
        subscriberRequest.setLastName("lName");
        subscriberRequest.setEmail("mail@sample.com");
        subscriberRequest.setSubscriberId("subId");

        triggerEventRequest.setTo(subscriberRequest);
        triggerEventRequest.setPayload(Collections.singletonMap("customVariables", "Hello"));
        triggerEventRequest.setActor("actor");
        triggerEventRequest.setTenant("tenant");

        TriggerEventResponse triggerEventResponse = new TriggerEventResponse();
        TriggerEventResponseData data = new TriggerEventResponseData();
        data.setAcknowledged(true);
        data.setStatus("done");
        data.setTransactionId("id");
        triggerEventResponse.setData(data);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(triggerEventResponse)));

        TriggerEventResponse response = eventsHandler.triggerEvent(triggerEventRequest);

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/events/trigger", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(triggerEventResponse), gson.toJson(response));
    }

    public void test_triggerEventToTopic() throws IOException, TeleflowNetworkException, InterruptedException {
        TriggerEventRequest triggerEventRequest = new TriggerEventRequest();
        triggerEventRequest.setName("name");

        Topic topic = new Topic();
        topic.setType("topic");
        topic.setTopicKey("topicKey");

        triggerEventRequest.setTo(topic);
        triggerEventRequest.setPayload(Collections.singletonMap("customVariables", "Hello"));

        Map<String, Object> actorMap = new HashMap<>();
        actorMap.put("subscriberId", "sId");
        actorMap.put("email", "email@mail.com");
        actorMap.put("firstName", "fName");
        actorMap.put("lastName", "lName");
        actorMap.put("phone", "phoneNo");
        actorMap.put("avatar", "avatarUrl");
        actorMap.put("locale", "locale");
        actorMap.put("data", new Object());
        triggerEventRequest.setActor(actorMap);

        Tenant tenant = new Tenant();
        tenant.setIdentifier("identifier");
        tenant.setName("name");
        tenant.setData(new Object());
        triggerEventRequest.setTenant(tenant);

        TriggerEventResponse triggerEventResponse = new TriggerEventResponse();
        TriggerEventResponseData data = new TriggerEventResponseData();
        data.setAcknowledged(true);
        data.setStatus("done");
        data.setTransactionId("id");
        triggerEventResponse.setData(data);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(triggerEventResponse)));

        TriggerEventResponse response = eventsHandler.triggerEvent(triggerEventRequest);

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/events/trigger", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(triggerEventResponse), gson.toJson(response));
    }

    public void test_bulkTriggerEventToSubscriber() throws IOException, TeleflowNetworkException, InterruptedException {
        BulkTriggerEventRequest bulkTriggerEventRequest = new BulkTriggerEventRequest();

        TriggerEventRequest triggerEventRequest = new TriggerEventRequest();
        triggerEventRequest.setName("name");

        SubscriberRequest subscriberRequest = new SubscriberRequest();
        subscriberRequest.setFirstName("fName");
        subscriberRequest.setLastName("lName");
        subscriberRequest.setEmail("mail@sample.com");
        subscriberRequest.setSubscriberId("subId");

        triggerEventRequest.setTo(subscriberRequest);
        triggerEventRequest.setPayload(Collections.singletonMap("customVariables", "Hello"));

        TriggerEventResponseData data = new TriggerEventResponseData();
        data.setAcknowledged(true);
        data.setStatus("done");
        data.setTransactionId("id");

        BulkTriggerEventResponse bulkTriggerEventResponse = new BulkTriggerEventResponse();
        bulkTriggerEventResponse.setData(Collections.singletonList(data));

        bulkTriggerEventRequest.setEvents(Collections.singletonList(triggerEventRequest));

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(bulkTriggerEventResponse)));

        BulkTriggerEventResponse response = eventsHandler.bulkTriggerEvent(bulkTriggerEventRequest);

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/events/trigger/bulk", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(bulkTriggerEventResponse), gson.toJson(response));
    }

    public void test_broadcastEventToSubscriber() throws IOException, TeleflowNetworkException, InterruptedException {
        TriggerEventRequest triggerEventRequest = new TriggerEventRequest();
        triggerEventRequest.setName("name");

        SubscriberRequest subscriberRequest = new SubscriberRequest();
        subscriberRequest.setFirstName("fName");
        subscriberRequest.setLastName("lName");
        subscriberRequest.setEmail("mail@sample.com");
        subscriberRequest.setSubscriberId("subId");

        triggerEventRequest.setTo(subscriberRequest);
        triggerEventRequest.setPayload(Collections.singletonMap("customVariables", "Hello"));

        TriggerEventResponse triggerEventResponse = new TriggerEventResponse();
        TriggerEventResponseData data = new TriggerEventResponseData();
        data.setAcknowledged(true);
        data.setStatus("done");
        data.setTransactionId("id");
        triggerEventResponse.setData(data);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(triggerEventResponse)));

        TriggerEventResponse response = eventsHandler.broadcastEvent(new TriggerEventRequest());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/events/trigger/broadcast", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(triggerEventResponse), gson.toJson(response));
    }

    public void test_cancelTriggeredEvent() throws IOException, TeleflowNetworkException, InterruptedException {
        CancelEventResponse cancelEventResponse = new CancelEventResponse();
        cancelEventResponse.setData(true);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(cancelEventResponse)));

        String eventId = "id";
        CancelEventResponse response = eventsHandler.cancelTriggeredEvent(eventId);

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/events/trigger/"+eventId, request.getPath());
        assertEquals("DELETE", request.getMethod());
        assertEquals(gson.toJson(cancelEventResponse), gson.toJson(response));
    }
}