package com.teleflow.api.blueprints;

import com.teleflow.api.blueprints.pojos.Blueprint;
import com.teleflow.api.blueprints.responses.BlueprintsByCategoryResponse;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import retrofit2.Response;

import java.io.IOException;

public class BlueprintsHandler {

    private final RestHandler restHandler;

    private final BlueprintsApi blueprintsApi;

    public BlueprintsHandler(RestHandler restHandler) {
        this.restHandler = restHandler;
        this.blueprintsApi = restHandler.buildRetrofit().create(BlueprintsApi.class);
    }

    public BlueprintsByCategoryResponse getBlueprintsByCategory() throws IOException, TeleflowNetworkException {
        Response<BlueprintsByCategoryResponse> response = blueprintsApi.getBlueprintsByCategory().execute();
        return restHandler.extractResponse(response);
    }

    public Blueprint getBlueprint(String templateId) throws IOException, TeleflowNetworkException {
        Response<Blueprint> response = blueprintsApi.getBlueprint(templateId).execute();
        return restHandler.extractResponse(response);
    }
}