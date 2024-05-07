package com.teleflow.api.workflowgroups;

import java.io.IOException;

import com.teleflow.api.workflowgroups.request.WorkflowGroupRequest;
import com.teleflow.api.workflowgroups.responses.DeleteWorkflowGroup;
import com.teleflow.api.workflowgroups.responses.GetWorkflowGroupsResponse;
import com.teleflow.api.workflowgroups.responses.WorkflowGroupResponse;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkflowGroupHandler {

    private final RestHandler restHandler;

    private final WorkflowGroupApi workflowGroupApi;

    public WorkflowGroupHandler(RestHandler restHandler) {
        this.restHandler = restHandler;
        this.workflowGroupApi = restHandler.buildRetrofit().create(WorkflowGroupApi.class);
    }


    public WorkflowGroupResponse createWorkflowGroup(WorkflowGroupRequest request) throws TeleflowNetworkException, IOException {
        return restHandler.extractResponse(this.workflowGroupApi.createWorkflowGroup(request).execute());
    }

    public GetWorkflowGroupsResponse getWorkflowGroups() throws TeleflowNetworkException, IOException {
        return restHandler.extractResponse(this.workflowGroupApi.getWorkflowGroups().execute());
    }

    public WorkflowGroupResponse getWorkflowGroup(String id) throws TeleflowNetworkException, IOException {
        return restHandler.extractResponse(this.workflowGroupApi.getWorkflowGroup(id).execute());
    }

    public WorkflowGroupResponse updateWorkflowGroup(String id, WorkflowGroupRequest request) throws TeleflowNetworkException, IOException {
        return restHandler.extractResponse(this.workflowGroupApi.updateWorkflowGroup(id, request).execute());
    }

    public DeleteWorkflowGroup deleteWorkflowGroup(String id) throws TeleflowNetworkException, IOException {
        return restHandler.extractResponse(this.workflowGroupApi.deleteWorkflowGroup(id).execute());
    }
}
