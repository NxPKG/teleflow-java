package com.teleflow.api.changes;

import com.teleflow.api.changes.request.ApplyChangesRequest;
import com.teleflow.api.changes.request.GetChangesRequest;
import com.teleflow.api.changes.responses.ApplyChangesResponse;
import com.teleflow.api.changes.responses.ChangeCountResponse;
import com.teleflow.api.changes.responses.ChangesData;
import com.teleflow.api.changes.responses.GetChangesResponse;
import com.teleflow.common.base.TeleflowConfig;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import com.google.gson.Gson;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.Collections;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class ChangeHandlerTest extends TestCase {

    private ChangeHandler changeHandler;

    private MockWebServer mockWebServer;

    @Override
    protected void setUp() {
        mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        changeHandler = new ChangeHandler(restHandler);
    }

    public void test_getChanges() throws IOException, TeleflowNetworkException, InterruptedException {
        GetChangesResponse changesResponse = new GetChangesResponse();
        changesResponse.setPage(2);
        changesResponse.setPageSize(20);
        changesResponse.setTotalCount(200);
        changesResponse.setData(Collections.singletonList(new ChangesData()));

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(changesResponse)));

        GetChangesRequest changesRequest = new GetChangesRequest();
        changesRequest.setPage(2);
        changesRequest.setLimit(20);
        changesRequest.setPromoted("promoted");

        GetChangesResponse response = changeHandler.getChanges(changesRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/changes?limit=20&page=2&promoted=promoted", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(changesResponse), gson.toJson(response));
    }

    public void test_getChangesCount() throws IOException, TeleflowNetworkException, InterruptedException {
        ChangeCountResponse changeCountResponse = new ChangeCountResponse();
        changeCountResponse.setData(1);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(changeCountResponse)));

        ChangeCountResponse response = changeHandler.getChangesCount();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/changes/count", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(changeCountResponse), gson.toJson(response));
    }

    public void test_applyChanges() throws IOException, TeleflowNetworkException, InterruptedException {
        ApplyChangesRequest changesRequest = new ApplyChangesRequest();
        changesRequest.setChangeIds(Collections.singletonList(new Object()));

        ApplyChangesResponse changesResponse = new ApplyChangesResponse();
        changesResponse.setData(Collections.singletonList(new ChangesData()));

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(changesResponse)));

        ApplyChangesResponse response = changeHandler.applyChanges(changesRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/changes/bulk/apply", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(changesResponse), gson.toJson(response));
    }

    public void test_applyChange() throws IOException, TeleflowNetworkException, InterruptedException {
        ApplyChangesResponse changesResponse = new ApplyChangesResponse();
        changesResponse.setData(Collections.singletonList(new ChangesData()));

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(changesResponse)));

        ApplyChangesResponse response = changeHandler.applyChange("bat");
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/changes/bat/apply", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(changesResponse), gson.toJson(response));
    }


}
