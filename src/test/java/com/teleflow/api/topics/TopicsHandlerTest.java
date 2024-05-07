package com.teleflow.api.topics;

import com.teleflow.api.topics.requests.FilterTopicsRequest;
import com.teleflow.api.topics.requests.RenameTopicRequest;
import com.teleflow.api.topics.requests.SubscriberAdditionRequest;
import com.teleflow.api.topics.requests.TopicRequest;
import com.teleflow.api.topics.responses.CheckTopicSubscriberResponse;
import com.teleflow.api.topics.responses.DeleteTopicResponse;
import com.teleflow.api.topics.responses.Failed;
import com.teleflow.api.topics.responses.FilterTopicsResponse;
import com.teleflow.api.topics.responses.SubscriberAdditionResponse;
import com.teleflow.api.topics.responses.SubscriberAdditionResponseData;
import com.teleflow.api.topics.responses.SubscriberRemovalResponse;
import com.teleflow.api.topics.responses.TopicResponse;
import com.teleflow.api.topics.responses.TopicResponseData;
import com.teleflow.common.base.TeleflowConfig;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import com.google.gson.Gson;
import junit.framework.TestCase;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertThrows;

public class TopicsHandlerTest extends TestCase {
    private TopicHandler topicHandler;

    private MockWebServer mockWebServer;

    @Override
    protected void setUp() {
        mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        topicHandler = new TopicHandler(restHandler);
    }

    public void test_createTopic() throws IOException, TeleflowNetworkException, InterruptedException {
        TopicRequest topicRequest = new TopicRequest();
        topicRequest.setKey("key");
        topicRequest.setName("name");

        TopicResponse topicResponse = new TopicResponse();
        TopicResponseData data = new TopicResponseData();
        data.setId("id");
        data.setEnvironmentId("environmentId");
        data.setOrganizationId("organizationId");
        data.setSubscriberId("subscribeId");
        data.setTopicId("topicId");
        data.setTopicKey("topickey");
        data.setExternalSubscriberId("extSubscriberId");
        data.setKey("ky");
        data.setName("name");
        data.setSubscribers(Collections.singletonList(new Object()));
        topicResponse.setData(data);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(topicResponse)));

        TopicResponse response = topicHandler.createTopic(topicRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/topics", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(topicResponse), gson.toJson(response));
    }

    public void test_filterTopics() throws IOException, TeleflowNetworkException, InterruptedException {
        FilterTopicsResponse topicsResponse = new FilterTopicsResponse();
        topicsResponse.setData(Collections.singletonList(new TopicResponseData()));
        topicsResponse.setPage(2);
        topicsResponse.setPageSize(20);
        topicsResponse.setTotalCount(200);
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(topicsResponse)));

        FilterTopicsRequest topicsRequest = new FilterTopicsRequest();
        topicsRequest.setPage(2);
        topicsRequest.setPageSize(20);
        topicsRequest.setKey("key");

        FilterTopicsResponse response = topicHandler.filterTopics(topicsRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/topics?pageSize=20&page=2&key=key", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(topicsResponse), gson.toJson(response));
    }

    public void test_addSubscriberToTopic() throws IOException, TeleflowNetworkException, InterruptedException {
        SubscriberAdditionResponse additionResponse = new SubscriberAdditionResponse();
        SubscriberAdditionResponseData additionResponseData = new SubscriberAdditionResponseData();
        Failed failed = new Failed();
        failed.setNotFound(List.of());
        additionResponseData.setSucceeded(List.of());
        additionResponseData.setFailed(failed);
        additionResponse.setData(additionResponseData);

        SubscriberAdditionRequest additionRequest = new SubscriberAdditionRequest();
        additionRequest.setSubscribers(Collections.singletonList("aSubscriberId"));

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(additionResponse)));

        SubscriberAdditionResponse response = topicHandler.addSubscriberToTopic(additionRequest,"id");
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/topics/id/subscribers", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(additionResponse), gson.toJson(response));
    }

    public void test_checkTopicSubscriber() throws IOException, TeleflowNetworkException, InterruptedException {
        CheckTopicSubscriberResponse responseData = new CheckTopicSubscriberResponse();
        responseData.setEnvironmentId("environmentId");
        responseData.setOrganizationId("organizationId");
        responseData.setSubscriberId("subscribeId");
        responseData.setTopicId("topicId");
        responseData.setTopicKey("topickey");
        responseData.setExternalSubscriberId("extSubscriberId");

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(responseData)));

        CheckTopicSubscriberResponse response = topicHandler.checkTopicSubscriber("topicKey","externalSubscriberId");
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/topics/topicKey/subscribers/externalSubscriberId", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(responseData), gson.toJson(response));
    }

    public void test_removeSubscriberFromTopicFailure() throws InterruptedException {
        SubscriberAdditionRequest additionRequest = new SubscriberAdditionRequest();
        additionRequest.setSubscribers(Collections.singletonList("aSubscriberId"));

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(400).setBody("{}"));

        TeleflowNetworkException networkException = assertThrows(TeleflowNetworkException.class,
                () -> topicHandler.removeSubscriberFromTopic(additionRequest,"topicKey"));
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/topics/topicKey/subscribers/removal", request.getPath());
        assertEquals("POST", request.getMethod());
    }
    public void test_removeSubscriberFromTopicSuccess() throws IOException, InterruptedException, TeleflowNetworkException {
        SubscriberAdditionRequest additionRequest = new SubscriberAdditionRequest();
        additionRequest.setSubscribers(Collections.singletonList("aSubscriberId"));


        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(201).setBody("{}"));

        SubscriberRemovalResponse response = topicHandler.removeSubscriberFromTopic(additionRequest,"topicKey");
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/topics/topicKey/subscribers/removal", request.getPath());
        assertEquals("POST", request.getMethod());
        assertTrue(response.getAcknowledged());
    }

    public void test_deleteTopicFailure() throws InterruptedException {
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(400).setBody(gson.toJson("{}")));

        TeleflowNetworkException networkException = assertThrows(TeleflowNetworkException.class,
                () -> topicHandler.deleteTopic("topicKey"));
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/topics/topicKey", request.getPath());
        assertEquals("DELETE", request.getMethod());
    }

    public void test_deleteTopicSuccess() throws IOException, InterruptedException, TeleflowNetworkException {
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(201).setBody("{}"));

        DeleteTopicResponse response = topicHandler.deleteTopic("topicKey");
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/topics/topicKey", request.getPath());
        assertEquals("DELETE", request.getMethod());
        assertNotNull(response);
        assertTrue(response.getAcknowledged());
    }
    public void test_getTopic() throws IOException, TeleflowNetworkException, InterruptedException {
        TopicResponse topicResponse = new TopicResponse();
        TopicResponseData data = new TopicResponseData();
        data.setId("id");
        data.setEnvironmentId("environmentId");
        data.setOrganizationId("organizationId");
        data.setSubscriberId("subscribeId");
        data.setTopicId("topicId");
        data.setTopicKey("topicKey");
        data.setTopicKey("topickey");
        data.setExternalSubscriberId("extSubscriberId");
        data.setKey("ky");
        data.setName("name");
        data.setSubscribers(Collections.singletonList(new Object()));
        topicResponse.setData(data);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(201).setBody(gson.toJson(topicResponse)));

        TopicResponse response = topicHandler.getTopic("topicKey");
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/topics/topicKey", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(topicResponse), gson.toJson(response));
    }

    public void test_renameTopic() throws IOException, TeleflowNetworkException, InterruptedException {
        TopicResponse topicResponse = new TopicResponse();
        TopicResponseData data = new TopicResponseData();
        data.setId("id");
        data.setEnvironmentId("environmentId");
        data.setOrganizationId("organizationId");
        data.setSubscriberId("subscribeId");
        data.setTopicId("topicId");
        data.setTopicKey("topickey");
        data.setExternalSubscriberId("extSubscriberId");
        data.setKey("ky");
        data.setName("name");
        data.setSubscribers(Collections.singletonList(new Object()));
        topicResponse.setData(data);

        RenameTopicRequest renameTopicRequest = new RenameTopicRequest();
        renameTopicRequest.setName("name");

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(201).setBody(gson.toJson(topicResponse)));

        TopicResponse response = topicHandler.renameTopic(renameTopicRequest,"topicKey");
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/topics/topicKey", request.getPath());
        assertEquals("PATCH", request.getMethod());
        assertEquals(gson.toJson(topicResponse), gson.toJson(response));
    }


}
