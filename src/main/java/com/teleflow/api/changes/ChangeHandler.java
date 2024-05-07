package com.teleflow.api.changes;

import com.teleflow.api.changes.request.ApplyChangesRequest;
import com.teleflow.api.changes.request.GetChangesRequest;
import com.teleflow.api.changes.responses.ApplyChangesResponse;
import com.teleflow.api.changes.responses.ChangeCountResponse;
import com.teleflow.api.changes.responses.GetChangesResponse;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import lombok.RequiredArgsConstructor;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ChangeHandler {

    private final RestHandler restHandler;

    private final ChangeApi changeApi;

    private static final String ENDPOINT = "changes";

    public ChangeHandler(RestHandler restHandler) {
        this.restHandler = restHandler;
        this.changeApi = restHandler.buildRetrofit().create(ChangeApi.class);
    }


    public GetChangesResponse getChanges(GetChangesRequest request) throws IOException, TeleflowNetworkException {
        Map<String, Object> params = new HashMap<>();
        if (request.getPage() != null) params.put("page", request.getPage());
        if (request.getLimit() != null) params.put("limit", request.getLimit());
        params.put("promoted", request.getPromoted());
        Response<GetChangesResponse> response = changeApi.getChanges(params).execute();
        return restHandler.extractResponse(response);
    }

    public ChangeCountResponse getChangesCount() throws IOException, TeleflowNetworkException {
        Response<ChangeCountResponse> response = changeApi.getChangesCount().execute();
        return restHandler.extractResponse(response);
    }

    public ApplyChangesResponse applyChanges(ApplyChangesRequest request) throws IOException, TeleflowNetworkException {
        Response<ApplyChangesResponse> response = changeApi.applyChanges(request).execute();
        return restHandler.extractResponse(response);
    }

    public ApplyChangesResponse applyChange(String changeId) throws IOException, TeleflowNetworkException {
        Response<ApplyChangesResponse> response = changeApi.applyChange(changeId).execute();
        return restHandler.extractResponse(response);
    }


}
