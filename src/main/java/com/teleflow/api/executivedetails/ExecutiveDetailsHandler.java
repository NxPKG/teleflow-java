package com.teleflow.api.executivedetails;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.teleflow.api.executivedetails.responses.ExecutiveDetailsResponse;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import retrofit2.Response;


public class ExecutiveDetailsHandler {

    private final RestHandler restHandler;

    private final ExecutiveDetailsApi executiveDetailsApi;

    public ExecutiveDetailsHandler(RestHandler restHandler) {
        this.restHandler = restHandler;
        this.executiveDetailsApi = restHandler.buildRetrofit().create(ExecutiveDetailsApi.class);
    }
    
    public ExecutiveDetailsResponse getExecutionDetails(String notificationId, String subscriberId) throws IOException, TeleflowNetworkException {
    	Map<String, Object> params = new HashMap<>();
        params.put("notificationId", notificationId);
        params.put("subscriberId", subscriberId);
    	Response<ExecutiveDetailsResponse> response = executiveDetailsApi.getExecutionDetails(params).execute();
    	return restHandler.extractResponse(response);
    }
    
}