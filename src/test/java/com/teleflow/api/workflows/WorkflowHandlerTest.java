package com.teleflow.api.workflows;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;

import com.teleflow.api.common.NotificationGroup;
import com.teleflow.api.common.PreferenceSettings;
import com.teleflow.api.common.Trigger;
import com.teleflow.api.workflows.requests.UpdateWorkflowRequest;
import com.teleflow.api.workflows.requests.UpdateWorkflowStatusRequest;
import com.teleflow.api.workflows.requests.WorkflowRequest;
import com.teleflow.api.workflows.responses.BulkWorkflowResponse;
import com.teleflow.api.workflows.responses.DeleteWorkflowResponse;
import com.teleflow.api.workflows.responses.SingleWorkflowResponse;
import com.teleflow.api.workflows.responses.WorkflowResponse;
import com.teleflow.common.base.TeleflowConfig;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import junit.framework.TestCase;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class WorkflowHandlerTest extends TestCase {
	
    private WorkflowHandler workflowHandler;
    private MockWebServer mockWebServer;

    @Override
    protected void setUp() {
    	mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        workflowHandler = new WorkflowHandler(restHandler);
    }

    public void test_getWorkflows() throws IOException, TeleflowNetworkException, InterruptedException {
        BulkWorkflowResponse workflowResponse = new BulkWorkflowResponse();
        workflowResponse.setPage(2L);
        workflowResponse.setPageSize(20L);
        workflowResponse.setTotalCount(200L);
        workflowResponse.setData(Collections.singletonList(new WorkflowResponse()));
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(workflowResponse)));
        
        BulkWorkflowResponse response = workflowHandler.getWorkflows(12,13);
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/workflows?limit=13&page=12", request.getPath());
        assertEquals("GET", request.getMethod());
        assertNotNull(response);
        assertEquals(gson.toJson(workflowResponse), gson.toJson(response));
    }


    public void test_createWorkflow() throws IOException, TeleflowNetworkException, InterruptedException {
        WorkflowRequest workflowRequest = new WorkflowRequest();
        workflowRequest.setDescription("Desc");
        workflowRequest.setActive(false);
        workflowRequest.setName("name");
        workflowRequest.setDraft(false);
        PreferenceSettings preferenceSettings = new PreferenceSettings();
        preferenceSettings.setEmail(true);
        preferenceSettings.setSms(true);
        preferenceSettings.setInApp(true);
        preferenceSettings.setPush(true);
        preferenceSettings.setChat(true);
        workflowRequest.setPreferenceSettings(preferenceSettings);
        workflowRequest.setCritical(false);
        workflowRequest.setTags(List.of());
        workflowRequest.setSteps(List.of());
        workflowRequest.setNotificationGroupId("notificationId");
        NotificationGroup notificationGroup1 = new NotificationGroup();
        notificationGroup1.setId("id");
        notificationGroup1.setName("name");
        notificationGroup1.setEnvironmentId("environmentId");
        notificationGroup1.setOrganizationId("organizationId");
        notificationGroup1.setParentId("parentId");



        SingleWorkflowResponse singleWorkflowResponse = new SingleWorkflowResponse();
        WorkflowResponse data = new WorkflowResponse();
        data.setId("id");
        data.setDescription("Desc");
        data.setActive(false);
        data.setName("name");
        data.setDraft(false);
        PreferenceSettings preferenceSettings1 = new PreferenceSettings();
        preferenceSettings1.setEmail(true);
        preferenceSettings1.setSms(true);
        preferenceSettings1.setInApp(true);
        preferenceSettings1.setPush(true);
        preferenceSettings1.setChat(true);
        data.setPreferenceSettings(preferenceSettings1);
        data.setCritical(false);
        data.setTags(List.of());
        data.setSteps(List.of());
        data.setOrganizationId("organizationId");
        data.setCreatorId("creatorId");
        data.setEnvironmentId("environmentId");
        data.setTriggers(Collections.singletonList(new Trigger()));
        data.setNotificationGroupId("notificationId");
        data.setDeleted(false);
        data.setDeletedAt("deletedAt");
        data.setDeletedBy("deletedBy");
        NotificationGroup notificationGroup2 = new NotificationGroup();
        notificationGroup2.setId("id");
        notificationGroup2.setName("name");
        notificationGroup2.setEnvironmentId("environmentId");
        notificationGroup2.setOrganizationId("organizationId");
        notificationGroup2.setParentId("parentId");
        data.setNotificationGroup(notificationGroup2);
        data.setIsBlueprint(false);
        singleWorkflowResponse.setData(data);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(singleWorkflowResponse)));
        
        SingleWorkflowResponse response = workflowHandler.createWorkflow(workflowRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/workflows", request.getPath());
        assertEquals("POST", request.getMethod());
        assertNotNull(response);
        assertEquals(singleWorkflowResponse, response);
    }

    public void test_updateWorkflow() throws IOException, TeleflowNetworkException, InterruptedException {
        UpdateWorkflowRequest workflowRequest = new UpdateWorkflowRequest();
        workflowRequest.setDescription("Desc");
        workflowRequest.setActive(false);
        workflowRequest.setName("name");
        workflowRequest.setDraft(false);
        PreferenceSettings preferenceSettings = new PreferenceSettings();
        preferenceSettings.setEmail(true);
        preferenceSettings.setSms(true);
        preferenceSettings.setInApp(true);
        preferenceSettings.setPush(true);
        preferenceSettings.setChat(true);
        workflowRequest.setPreferenceSettings(preferenceSettings);
        workflowRequest.setCritical(false);
        workflowRequest.setTags(List.of());
        workflowRequest.setSteps(List.of());
        workflowRequest.setNotificationGroupId("notificationId");
        NotificationGroup notificationGroup1 = new NotificationGroup();
        notificationGroup1.setId("id");
        notificationGroup1.setName("name");
        notificationGroup1.setEnvironmentId("environmentId");
        notificationGroup1.setOrganizationId("organizationId");
        notificationGroup1.setParentId("parentId");

        SingleWorkflowResponse singleWorkflowResponse = new SingleWorkflowResponse();
        WorkflowResponse data = new WorkflowResponse();
        data.setId("id");
        data.setDescription("Desc");
        data.setActive(false);
        data.setName("name");
        data.setDraft(false);
        PreferenceSettings preferenceSettings1 = new PreferenceSettings();
        preferenceSettings1.setEmail(true);
        preferenceSettings1.setSms(true);
        preferenceSettings1.setInApp(true);
        preferenceSettings1.setPush(true);
        preferenceSettings1.setChat(true);
        data.setPreferenceSettings(preferenceSettings1);
        data.setCritical(false);
        data.setTags(List.of());
        data.setSteps(List.of());
        data.setOrganizationId("organizationId");
        data.setCreatorId("creatorId");
        data.setEnvironmentId("environmentId");
        data.setTriggers(Collections.singletonList(new Trigger()));
        data.setNotificationGroupId("notificationId");
        data.setDeleted(false);
        data.setDeletedAt("deletedAt");
        data.setDeletedBy("deletedBy");
        NotificationGroup notificationGroup2 = new NotificationGroup();
        notificationGroup2.setId("id");
        notificationGroup2.setName("name");
        notificationGroup2.setEnvironmentId("environmentId");
        notificationGroup2.setOrganizationId("organizationId");
        notificationGroup2.setParentId("parentId");
        data.setNotificationGroup(notificationGroup2);
        data.setIsBlueprint(false);
        singleWorkflowResponse.setData(data);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(singleWorkflowResponse)));

        SingleWorkflowResponse response = workflowHandler.updateWorkflow("workflowId", workflowRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/workflows/workflowId", request.getPath());
        assertEquals("PUT", request.getMethod());
        assertNotNull(response);
        assertEquals(singleWorkflowResponse, response);
    }

    public void test_deleteWorkflow() throws IOException, TeleflowNetworkException, InterruptedException {
        DeleteWorkflowResponse deleteWorkflowResponse = new DeleteWorkflowResponse();
        deleteWorkflowResponse.setData(false);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(deleteWorkflowResponse)));
        
        DeleteWorkflowResponse response = workflowHandler.deleteWorkflow("workflowId");
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/workflows/workflowId", request.getPath());
        assertEquals("DELETE", request.getMethod());
        assertNotNull(response);
        assertEquals(deleteWorkflowResponse, response);
    }

    public void test_getWorkflow() throws IOException, TeleflowNetworkException, InterruptedException {
        SingleWorkflowResponse singleWorkflowResponse = new SingleWorkflowResponse();
        WorkflowResponse data = new WorkflowResponse();
        data.setId("id");
        data.setDescription("Desc");
        data.setActive(false);
        data.setName("name");
        data.setDraft(false);
        PreferenceSettings preferenceSettings1 = new PreferenceSettings();
        preferenceSettings1.setEmail(true);
        preferenceSettings1.setSms(true);
        preferenceSettings1.setInApp(true);
        preferenceSettings1.setPush(true);
        preferenceSettings1.setChat(true);
        data.setPreferenceSettings(preferenceSettings1);
        data.setCritical(false);
        data.setTags(List.of());
        data.setSteps(List.of());
        data.setOrganizationId("organizationId");
        data.setCreatorId("creatorId");
        data.setEnvironmentId("environmentId");
        data.setTriggers(Collections.singletonList(new Trigger()));
        data.setNotificationGroupId("notificationId");
        data.setDeleted(false);
        data.setDeletedAt("deletedAt");
        data.setDeletedBy("deletedBy");
        NotificationGroup notificationGroup2 = new NotificationGroup();
        notificationGroup2.setId("id");
        notificationGroup2.setName("name");
        notificationGroup2.setEnvironmentId("environmentId");
        notificationGroup2.setOrganizationId("organizationId");
        notificationGroup2.setParentId("parentId");
        data.setNotificationGroup(notificationGroup2);
        data.setIsBlueprint(false);
        singleWorkflowResponse.setData(data);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(singleWorkflowResponse)));
        
        SingleWorkflowResponse response = workflowHandler.getWorkflow("workflowId");
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/workflows/workflowId", request.getPath());
        assertEquals("GET", request.getMethod());
        assertNotNull(response);
        assertEquals(singleWorkflowResponse, response);
    }

    public void test_updateWorkflowStatus() throws IOException, TeleflowNetworkException, InterruptedException {
        SingleWorkflowResponse singleWorkflowResponse = new SingleWorkflowResponse();
        WorkflowResponse data = new WorkflowResponse();
        data.setId("id");
        data.setDescription("Desc");
        data.setActive(false);
        data.setName("name");
        data.setDraft(false);
        PreferenceSettings preferenceSettings1 = new PreferenceSettings();
        preferenceSettings1.setEmail(true);
        preferenceSettings1.setSms(true);
        preferenceSettings1.setInApp(true);
        preferenceSettings1.setPush(true);
        preferenceSettings1.setChat(true);
        data.setPreferenceSettings(preferenceSettings1);
        data.setCritical(false);
        data.setTags(List.of());
        data.setSteps(List.of());
        data.setOrganizationId("organizationId");
        data.setCreatorId("creatorId");
        data.setEnvironmentId("environmentId");
        data.setTriggers(Collections.singletonList(new Trigger()));
        data.setNotificationGroupId("notificationId");
        data.setDeleted(false);
        data.setDeletedAt("deletedAt");
        data.setDeletedBy("deletedBy");
        NotificationGroup notificationGroup2 = new NotificationGroup();
        notificationGroup2.setId("id");
        notificationGroup2.setName("name");
        notificationGroup2.setEnvironmentId("environmentId");
        notificationGroup2.setOrganizationId("organizationId");
        notificationGroup2.setParentId("parentId");
        data.setNotificationGroup(notificationGroup2);
        data.setIsBlueprint(false);
        singleWorkflowResponse.setData(data);

        UpdateWorkflowStatusRequest updateWorkflowStatusRequest = new UpdateWorkflowStatusRequest();
        updateWorkflowStatusRequest.setActive(false);
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(singleWorkflowResponse)));

        SingleWorkflowResponse response = workflowHandler.updateWorkflowStatus("workflowId",updateWorkflowStatusRequest);
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/workflows/workflowId/status", request.getPath());
        assertEquals("PUT", request.getMethod());
        assertNotNull(response);
        assertEquals(singleWorkflowResponse, response);
    }


}
