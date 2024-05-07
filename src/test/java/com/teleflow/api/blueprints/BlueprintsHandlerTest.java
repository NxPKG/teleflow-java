package com.teleflow.api.blueprints;

import com.teleflow.api.blueprints.pojos.Blueprint;
import com.teleflow.api.blueprints.responses.BlueprintsResponseData;
import com.teleflow.api.blueprints.pojos.General;
import com.teleflow.api.blueprints.pojos.Popular;
import com.teleflow.api.blueprints.responses.BlueprintsByCategoryResponse;
import com.teleflow.common.base.TeleflowConfig;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import com.google.gson.Gson;
import junit.framework.TestCase;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import java.io.IOException;
import java.util.List;

public class BlueprintsHandlerTest extends TestCase {

    private BlueprintsHandler blueprintsHandler;

    private MockWebServer mockWebServer;

    @Override
    protected void setUp() {
        mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        blueprintsHandler = new BlueprintsHandler(restHandler);
    }

    public void test_getBlueprintsByCategory() throws IOException, TeleflowNetworkException, InterruptedException {
        BlueprintsResponseData data = new BlueprintsResponseData();
        data.setGeneral(List.of(new General()));
        data.setPopular(new Popular());
        BlueprintsByCategoryResponse byCategoryResponse = new BlueprintsByCategoryResponse();
        byCategoryResponse.setData(data);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(byCategoryResponse)));

        BlueprintsByCategoryResponse response = blueprintsHandler.getBlueprintsByCategory();
        RecordedRequest request = mockWebServer.takeRequest();

        assertEquals("/blueprints/group-by-category", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(byCategoryResponse), gson.toJson(response));
    }

    public void test_getBlueprint() throws IOException, TeleflowNetworkException, InterruptedException {
        Blueprint blueprint = new Blueprint();
        blueprint.setName("print");
        blueprint.setActive(true);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(blueprint)));

        String templateId = "tId";
        Blueprint response = blueprintsHandler.getBlueprint(templateId);
        RecordedRequest request = mockWebServer.takeRequest();

        assertEquals("/blueprints/" + templateId, request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(blueprint), gson.toJson(response));
    }
}