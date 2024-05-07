package com.teleflow.api.feeds;

import com.teleflow.api.feeds.request.FeedRequest;
import com.teleflow.api.feeds.response.BulkFeedsResponse;
import com.teleflow.api.feeds.response.FeedResponse;
import com.teleflow.api.feeds.response.FeedResponseData;
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

public class FeedHandlerTest extends TestCase {

    private FeedsHandler feedsHandler;

    private MockWebServer mockWebServer;

    @Override
    protected void setUp() {
    	mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        feedsHandler = new FeedsHandler(restHandler);
    }

    public void test_createFeed() throws IOException, TeleflowNetworkException, InterruptedException {
        FeedRequest feedRequest = new FeedRequest();
        feedRequest.setName("name");

        FeedResponse feedResponse = new FeedResponse();
        FeedResponseData data = new FeedResponseData();
        data.setId("id");
        data.setName("name");
        data.setIdentifier("identifier");
        data.setOrganizationId("organizationId");
        data.setEnvironmentId("environmentId");
        feedResponse.setData(data);
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(feedResponse)));

        FeedResponse response = feedsHandler.createFeed(feedRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/feeds", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(feedResponse), gson.toJson(response));
    }

    public void test_getFeeds() throws IOException, TeleflowNetworkException, InterruptedException {
        BulkFeedsResponse feedResponse = new BulkFeedsResponse();
        feedResponse.setData(Collections.singletonList(new FeedResponseData()));
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(feedResponse)));

        BulkFeedsResponse response = feedsHandler.getFeeds();
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/feeds", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(feedResponse), gson.toJson(response));
    }

    public void test_deleteFeed() throws IOException, TeleflowNetworkException, InterruptedException {
        BulkFeedsResponse bulkFeedsResponse = new BulkFeedsResponse();
        bulkFeedsResponse.setData(Collections.singletonList(new FeedResponseData()));

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(bulkFeedsResponse)));

        BulkFeedsResponse response = feedsHandler.deleteFeed("id");
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/feeds/id", request.getPath());
        assertEquals("DELETE", request.getMethod());
        assertEquals(gson.toJson(bulkFeedsResponse), gson.toJson(response));
    }
}
