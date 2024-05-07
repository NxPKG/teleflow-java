package com.teleflow.api.environments;

import com.teleflow.api.environments.pojos.ApiKey;
import com.teleflow.api.environments.pojos.Dns;
import com.teleflow.api.environments.pojos.Widget;
import com.teleflow.api.environments.requests.CreateEnvironmentRequest;
import com.teleflow.api.environments.requests.UpdateEnvironmentRequest;
import com.teleflow.api.environments.responses.ApiKeyResponse;
import com.teleflow.api.environments.responses.BulkEnvironmentResponse;
import com.teleflow.api.environments.responses.EnvironmentResponse;
import com.teleflow.api.environments.responses.SingleEnvironmentResponse;
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

public class EnvironmentHandlerTest extends TestCase {

    private EnvironmentHandler environmentHandler;

    private MockWebServer mockWebServer;

    @Override
    protected void setUp() {
        mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        environmentHandler = new EnvironmentHandler(restHandler);
    }

    public void test_getCurrentEnvironment() throws IOException, TeleflowNetworkException, InterruptedException {

        SingleEnvironmentResponse singleEnvironmentResponse = new SingleEnvironmentResponse();
        EnvironmentResponse data = new EnvironmentResponse();
        data.setId("id");
        data.setName("name");
        data.setIdentifier("identifier");
        data.setOrganizationId("organizationId");
        data.setApiKeys(Collections.singletonList(new ApiKey()));
        Widget widget = new Widget();
        widget.setNotificationCenterEncryption(false);
        data.setWidget(widget);
        data.setCreatedAt("createdAt");
        data.setUpdatedAt("updatedAt");
        data.setParentId("parentId");
        singleEnvironmentResponse.setData(data);
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(singleEnvironmentResponse)));

        SingleEnvironmentResponse response = environmentHandler.getCurrentEnvironment();
        RecordedRequest request = mockWebServer.takeRequest();

        assertEquals("/environments/me", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(singleEnvironmentResponse), gson.toJson(response));
    }

    public void test_createEnvironment() throws IOException, TeleflowNetworkException, InterruptedException {

        CreateEnvironmentRequest createEnvironmentRequest = new CreateEnvironmentRequest();
        createEnvironmentRequest.setName("name");
        createEnvironmentRequest.setParentId("parentId");

        SingleEnvironmentResponse singleEnvironmentResponse = new SingleEnvironmentResponse();
        EnvironmentResponse data = new EnvironmentResponse();
        data.setId("id");
        data.setName("name");
        data.setIdentifier("identifier");
        data.setOrganizationId("organizationId");
        data.setApiKeys(Collections.singletonList(new ApiKey()));
        Widget widget = new Widget();
        widget.setNotificationCenterEncryption(false);
        data.setWidget(widget);
        data.setCreatedAt("createdAt");
        data.setUpdatedAt("updatedAt");
        data.setParentId("parentId");
        singleEnvironmentResponse.setData(data);
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(singleEnvironmentResponse)));

        SingleEnvironmentResponse response = environmentHandler.createEnvironment(createEnvironmentRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/environments", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(singleEnvironmentResponse), gson.toJson(response));
    }

    public void test_getEnvironments() throws IOException, TeleflowNetworkException, InterruptedException {

        BulkEnvironmentResponse bulkEnvironmentResponse = new BulkEnvironmentResponse();
        bulkEnvironmentResponse.setData(Collections.singletonList(new EnvironmentResponse()));
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(bulkEnvironmentResponse)));

        BulkEnvironmentResponse response = environmentHandler.getEnvironments();
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/environments", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(bulkEnvironmentResponse), gson.toJson(response));
    }

    public void test_updateEnvironmentById() throws IOException, TeleflowNetworkException, InterruptedException {

        UpdateEnvironmentRequest updateEnvironmentRequest = new UpdateEnvironmentRequest();
        updateEnvironmentRequest.setName("name");
        updateEnvironmentRequest.setIdentifier("identifier");
        updateEnvironmentRequest.setParentId("parentId");
        Dns dns = new Dns();
        dns.setInboundParseDomain("inbound");
        updateEnvironmentRequest.setDns(dns);

        SingleEnvironmentResponse singleEnvironmentResponse = new SingleEnvironmentResponse();
        EnvironmentResponse data = new EnvironmentResponse();
        data.setId("id");
        data.setName("name");
        data.setIdentifier("identifier");
        data.setOrganizationId("organizationId");
        data.setApiKeys(Collections.singletonList(new ApiKey()));
        Widget widget = new Widget();
        widget.setNotificationCenterEncryption(false);
        data.setWidget(widget);
        data.setCreatedAt("createdAt");
        data.setUpdatedAt("updatedAt");
        data.setParentId("parentId");
        singleEnvironmentResponse.setData(data);
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(singleEnvironmentResponse)));

        SingleEnvironmentResponse response = environmentHandler.updateEnvironmentById("12",updateEnvironmentRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/environments/12", request.getPath());
        assertEquals("PUT", request.getMethod());
        assertEquals(gson.toJson(singleEnvironmentResponse), gson.toJson(response));
    }

    public void test_getApiKeys() throws IOException, TeleflowNetworkException, InterruptedException {
        ApiKeyResponse apiKeyResponse = new ApiKeyResponse();
        apiKeyResponse.setData(Collections.singletonList(new ApiKey()));
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(apiKeyResponse)));

        ApiKeyResponse response = environmentHandler.getApiKeys();
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/environments/api-keys", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(apiKeyResponse), gson.toJson(response));
    }

    public void test_regenerateApiKeys() throws IOException, TeleflowNetworkException, InterruptedException {

        ApiKeyResponse apiKeyResponse = new ApiKeyResponse();
        apiKeyResponse.setData(Collections.singletonList(new ApiKey()));

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(apiKeyResponse)));
        
        ApiKeyResponse response = environmentHandler.regenerateApiKeys();
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/environments/api-keys/regenerate", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(apiKeyResponse), gson.toJson(response));
    }



}
