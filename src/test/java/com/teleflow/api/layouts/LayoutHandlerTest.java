package com.teleflow.api.layouts;

import com.teleflow.api.layouts.requests.FilterLayoutRequest;
import com.teleflow.api.layouts.requests.LayoutRequest;
import com.teleflow.api.layouts.responses.CreateLayoutResponse;
import com.teleflow.api.layouts.responses.CreateLayoutResponseData;
import com.teleflow.api.layouts.responses.DeleteLayoutResponse;
import com.teleflow.api.layouts.responses.FilterLayoutResponse;
import com.teleflow.api.layouts.responses.GetLayoutResponse;
import com.teleflow.api.layouts.responses.LayoutResponse;
import com.teleflow.api.layouts.responses.SetDefaultLayoutResponse;
import com.teleflow.common.base.TeleflowConfig;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import com.google.gson.Gson;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertThrows;

public class LayoutHandlerTest extends TestCase {

    private LayoutHandler layoutHandler;

    private MockWebServer mockWebServer;

    @Override
    protected void setUp() {
        mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        layoutHandler = new LayoutHandler(restHandler);
    }

    public void test_createLayout() throws IOException, TeleflowNetworkException, InterruptedException {
        LayoutRequest layoutRequest = new LayoutRequest();
        layoutRequest.setName("name");
        layoutRequest.setContent("content");
        layoutRequest.setDescription("desc");
        layoutRequest.setIsDefault(false);

        CreateLayoutResponse createLayoutResponse = new CreateLayoutResponse();
        CreateLayoutResponseData createLayoutResponseData = new CreateLayoutResponseData();
        createLayoutResponseData.setId("id");
        createLayoutResponse.setData(createLayoutResponseData);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(createLayoutResponse)));

        CreateLayoutResponse response = layoutHandler.createLayout(layoutRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/layouts", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(createLayoutResponse), gson.toJson(response));
    }

    public void test_filterLayoutsNoParams() throws IOException, TeleflowNetworkException, InterruptedException {
        FilterLayoutRequest filterLayoutRequest = new FilterLayoutRequest();
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(new FilterLayoutResponse())));

        layoutHandler.filterLayouts(filterLayoutRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/layouts", request.getPath());
        assertEquals("GET", request.getMethod());
    }

    public void test_filterLayoutsWithParams() throws IOException, TeleflowNetworkException, InterruptedException {
        FilterLayoutRequest filterLayoutRequest = new FilterLayoutRequest();
        filterLayoutRequest.setPage(1);
        filterLayoutRequest.setPageSize(10);

        FilterLayoutResponse filterLayoutResponse = new FilterLayoutResponse();
        filterLayoutResponse.setPage(1);
        filterLayoutResponse.setPageSize(10);
        LayoutResponse layoutResponse = new LayoutResponse();
        layoutResponse.setName("name");
        layoutResponse.setContent("content");
        filterLayoutResponse.setData(List.of(layoutResponse));

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(filterLayoutResponse)));

        FilterLayoutResponse response = layoutHandler.filterLayouts(filterLayoutRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/layouts?pageSize=10&page=1", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(filterLayoutResponse), gson.toJson(response));
    }

    public void test_getLayout() throws IOException, TeleflowNetworkException, InterruptedException {
        FilterLayoutRequest filterLayoutRequest = new FilterLayoutRequest();
        filterLayoutRequest.setPage(1);
        filterLayoutRequest.setPageSize(10);

        GetLayoutResponse getLayoutResponse = new GetLayoutResponse();
        LayoutResponse layoutResponse = new LayoutResponse();
        layoutResponse.setName("name");
        layoutResponse.setContent("content");
        getLayoutResponse.setData(layoutResponse);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(getLayoutResponse)));

        GetLayoutResponse response = layoutHandler.getLayout("bat-123");
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/layouts/bat-123", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(getLayoutResponse), gson.toJson(response));
    }

    public void test_deleteLayoutFailure() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(400).setBody("{}"));
        TeleflowNetworkException networkException = assertThrows(TeleflowNetworkException.class,
                () -> layoutHandler.deleteLayout("bat-123"));

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/layouts/bat-123", request.getPath());
        assertEquals("DELETE", request.getMethod());
    }

    public void test_deleteLayoutSuccess() throws IOException, TeleflowNetworkException, InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(201).setBody("{}"));
        DeleteLayoutResponse response = layoutHandler.deleteLayout("bat-123");
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/layouts/bat-123", request.getPath());
        assertEquals("DELETE", request.getMethod());
        assertTrue(response.getAcknowledged());
    }

    public void test_updateLayout() throws IOException, TeleflowNetworkException, InterruptedException {
        LayoutRequest layoutRequest = new LayoutRequest();
        layoutRequest.setName("name");
        layoutRequest.setContent("content");
        layoutRequest.setDescription("desc");
        layoutRequest.setIsDefault(false);

        GetLayoutResponse getLayoutResponse = new GetLayoutResponse();
        LayoutResponse layoutResponse = new LayoutResponse();
        layoutResponse.setName("name");
        layoutResponse.setContent("content");
        getLayoutResponse.setData(layoutResponse);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(getLayoutResponse)));

        GetLayoutResponse response = layoutHandler.updateLayout("bat-123", layoutRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/layouts/bat-123", request.getPath());
        assertEquals("PATCH", request.getMethod());
        assertEquals(gson.toJson(getLayoutResponse), gson.toJson(response));
    }

    public void test_setDefaultLayoutFailure() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(400).setBody("{}"));
        TeleflowNetworkException networkException = assertThrows(TeleflowNetworkException.class,
                () -> layoutHandler.setDefaultLayout("bat-123"));

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/layouts/bat-123/default", request.getPath());
        assertEquals("POST", request.getMethod());
    }

    public void test_setDefaultLayoutSuccess() throws IOException, TeleflowNetworkException, InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(201).setBody("{}"));
        SetDefaultLayoutResponse response = layoutHandler.setDefaultLayout("bat-123");

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/layouts/bat-123/default", request.getPath());
        assertEquals("POST", request.getMethod());
        assertTrue(response.getAcknowledged());
    }
}