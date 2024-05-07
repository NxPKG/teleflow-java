package com.teleflow.api.workflowgroups;

import java.io.IOException;
import java.util.Collections;

import com.google.gson.Gson;

import com.teleflow.api.workflowgroups.request.WorkflowGroupRequest;
import com.teleflow.api.workflowgroups.responses.DeleteWorkflowGroup;
import com.teleflow.api.workflowgroups.responses.GetWorkflowGroupsResponse;
import com.teleflow.api.workflowgroups.responses.WorkflowGroupResponse;
import com.teleflow.api.workflowgroups.responses.WorkflowGroupResponseData;
import com.teleflow.common.base.TeleflowConfig;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import junit.framework.TestCase;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class WorkflowGroupHandlerTest extends TestCase {
    private WorkflowGroupHandler workflowGroupHandler;

    private MockWebServer mockWebServer;

    @Override
    protected void setUp() {
        mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        workflowGroupHandler = new WorkflowGroupHandler(restHandler);
    }

    public void test_createWorkflowGroup() throws InterruptedException, TeleflowNetworkException, IOException {
        WorkflowGroupRequest workflowGroupRequest = new WorkflowGroupRequest();
        workflowGroupRequest.setName("fname");

        WorkflowGroupResponse workflowGroupResponse = new WorkflowGroupResponse();
        WorkflowGroupResponseData data = new WorkflowGroupResponseData();
        data.set_id("id");
        data.setName("name");
        data.set_organizationId("organizationId");
        data.set_environmentId("environmentId");
        data.set_parentId("parentId");
        workflowGroupResponse.setData(data);

         Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(workflowGroupResponse)));

        WorkflowGroupResponse response = workflowGroupHandler.createWorkflowGroup(workflowGroupRequest);
        RecordedRequest request = mockWebServer.takeRequest();

        assertEquals("/notification-groups", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(workflowGroupResponse), gson.toJson(response));
    }

    public void test_getWorkflowGroups() throws InterruptedException, TeleflowNetworkException, IOException {
        GetWorkflowGroupsResponse getWorkflowGroupsResponse = new GetWorkflowGroupsResponse();
        getWorkflowGroupsResponse.setData(Collections.singletonList(new WorkflowGroupResponseData()));
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(getWorkflowGroupsResponse)));

        GetWorkflowGroupsResponse response = workflowGroupHandler.getWorkflowGroups();
        RecordedRequest request = mockWebServer.takeRequest();

        assertEquals("/notification-groups", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(getWorkflowGroupsResponse), gson.toJson(response));
    }

    public void test_getWorkflowGroup() throws TeleflowNetworkException, IOException, InterruptedException {

        WorkflowGroupResponse workflowGroupResponse = new WorkflowGroupResponse();
        WorkflowGroupResponseData data = new WorkflowGroupResponseData();
        data.set_id("id");
        data.setName("name");
        data.set_organizationId("organizationId");
        data.set_environmentId("environmentId");
        data.set_parentId("parentId");
        workflowGroupResponse.setData(data);
       
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(workflowGroupResponse)));

        WorkflowGroupResponse response = workflowGroupHandler.getWorkflowGroup(data.get_id());
        RecordedRequest request = mockWebServer.takeRequest();

        assertEquals("/notification-groups/id", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(workflowGroupResponse), gson.toJson(response));
    }

    public void test_updateWorkflowGroup() throws TeleflowNetworkException, IOException, InterruptedException {

        WorkflowGroupRequest workflowGroupRequest = new WorkflowGroupRequest();
        workflowGroupRequest.setName("fname");

        WorkflowGroupResponse workflowGroupResponse = new WorkflowGroupResponse();
        WorkflowGroupResponseData data = new WorkflowGroupResponseData();
        data.set_id("id");
        data.setName("name");
        data.set_organizationId("organizationId");
        data.set_environmentId("environmentId");
        data.set_parentId("parentId");
        workflowGroupResponse.setData(data);
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(workflowGroupResponse)));

        WorkflowGroupResponse response = workflowGroupHandler.updateWorkflowGroup(data.get_id(), workflowGroupRequest);
        RecordedRequest request = mockWebServer.takeRequest();

        assertEquals("/notification-groups/id", request.getPath());
        assertEquals("PUT", request.getMethod());
        assertEquals(gson.toJson(workflowGroupResponse), gson.toJson(response));
    }

    public void test_deleteWorkflowGroup() throws TeleflowNetworkException, IOException, InterruptedException {
        DeleteWorkflowGroup deleteWorkflowGroup = new DeleteWorkflowGroup();
        deleteWorkflowGroup.setAcknowledged(false);
        deleteWorkflowGroup.setStatus("success");
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(deleteWorkflowGroup)));

        DeleteWorkflowGroup response = workflowGroupHandler.deleteWorkflowGroup("id");
        RecordedRequest request = mockWebServer.takeRequest();

        assertEquals("/notification-groups/id", request.getPath());
        assertEquals("DELETE", request.getMethod());
        assertEquals(gson.toJson(deleteWorkflowGroup), gson.toJson(response));
    }

}
