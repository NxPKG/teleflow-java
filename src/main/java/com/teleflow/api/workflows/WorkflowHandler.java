package com.teleflow.api.workflows;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.teleflow.api.workflows.requests.UpdateWorkflowRequest;
import com.teleflow.api.workflows.requests.UpdateWorkflowStatusRequest;
import com.teleflow.api.workflows.requests.WorkflowRequest;
import com.teleflow.api.workflows.responses.BulkWorkflowResponse;
import com.teleflow.api.workflows.responses.DeleteWorkflowResponse;
import com.teleflow.api.workflows.responses.SingleWorkflowResponse;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import retrofit2.Response;

public class WorkflowHandler {

    private final RestHandler restHandler;
    
    private final WorkflowApi workflowApi;

    public WorkflowHandler(RestHandler restHandler) {
		this.restHandler = restHandler;
		this.workflowApi = restHandler.buildRetrofit().create(WorkflowApi.class);
	}

	public BulkWorkflowResponse getWorkflows(Integer page, Integer limit) throws IOException, TeleflowNetworkException  {
        Map<String, Object> params = new HashMap<>();
        if (page != null) params.put("page", page);
        if (limit != null) params.put("limit", limit);
        Response<BulkWorkflowResponse> response = workflowApi.getWorkflows(params).execute();
        return restHandler.extractResponse(response);
    }

    public SingleWorkflowResponse createWorkflow(WorkflowRequest request) throws IOException, TeleflowNetworkException  {
    	Response<SingleWorkflowResponse> response = workflowApi.createWorkflow(request).execute();
        return restHandler.extractResponse(response);
    }

    public SingleWorkflowResponse updateWorkflow(String workflowId, UpdateWorkflowRequest request) throws IOException, TeleflowNetworkException {
    	Response<SingleWorkflowResponse> response = workflowApi.updateWorkflow(workflowId , request).execute();
        return restHandler.extractResponse(response);
    }

    public DeleteWorkflowResponse deleteWorkflow(String workflowId) throws IOException, TeleflowNetworkException {
    	Response<DeleteWorkflowResponse> response = workflowApi.deleteWorkflow(workflowId).execute();
        return restHandler.extractResponse(response);
    }

    public SingleWorkflowResponse getWorkflow(String workflowId) throws IOException, TeleflowNetworkException {
    	Response<SingleWorkflowResponse> response = workflowApi.getWorkflow(workflowId).execute();
        return restHandler.extractResponse(response);
    }

    public SingleWorkflowResponse updateWorkflowStatus(String workflowId, UpdateWorkflowStatusRequest request) throws IOException, TeleflowNetworkException {
    	Response<SingleWorkflowResponse> response = workflowApi.updateWorkflowStatus(workflowId , request).execute();
        return restHandler.extractResponse(response);
    }

}