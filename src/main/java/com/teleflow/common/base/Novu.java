package com.teleflow.common.base;

import java.io.IOException;

import com.teleflow.api.blueprints.BlueprintsHandler;
import com.teleflow.api.blueprints.pojos.Blueprint;
import com.teleflow.api.blueprints.responses.BlueprintsByCategoryResponse;
import com.teleflow.api.changes.ChangeHandler;
import com.teleflow.api.changes.request.ApplyChangesRequest;
import com.teleflow.api.changes.request.GetChangesRequest;
import com.teleflow.api.changes.responses.ApplyChangesResponse;
import com.teleflow.api.changes.responses.ChangeCountResponse;
import com.teleflow.api.changes.responses.GetChangesResponse;
import com.teleflow.api.common.SubscriberRequest;
import com.teleflow.api.environments.EnvironmentHandler;
import com.teleflow.api.environments.requests.CreateEnvironmentRequest;
import com.teleflow.api.environments.requests.UpdateEnvironmentRequest;
import com.teleflow.api.environments.responses.ApiKeyResponse;
import com.teleflow.api.environments.responses.BulkEnvironmentResponse;
import com.teleflow.api.environments.responses.SingleEnvironmentResponse;
import com.teleflow.api.events.EventsHandler;
import com.teleflow.api.events.pojos.BulkTriggerEventRequest;
import com.teleflow.api.events.requests.TriggerEventRequest;
import com.teleflow.api.events.responses.BulkTriggerEventResponse;
import com.teleflow.api.events.responses.CancelEventResponse;
import com.teleflow.api.events.responses.TriggerEventResponse;
import com.teleflow.api.executivedetails.ExecutiveDetailsHandler;
import com.teleflow.api.executivedetails.responses.ExecutiveDetailsResponse;
import com.teleflow.api.feeds.FeedsHandler;
import com.teleflow.api.feeds.request.FeedRequest;
import com.teleflow.api.feeds.response.BulkFeedsResponse;
import com.teleflow.api.feeds.response.FeedResponse;
import com.teleflow.api.inboundparse.InboundParseHandler;
import com.teleflow.api.inboundparse.responses.ValidateMxRecordResponse;
import com.teleflow.api.integrations.IntegrationsHandler;
import com.teleflow.api.integrations.requests.IntegrationRequest;
import com.teleflow.api.integrations.responses.BulkIntegrationResponse;
import com.teleflow.api.integrations.responses.ProviderWebhookStatusResponse;
import com.teleflow.api.integrations.responses.SingleIntegrationResponse;
import com.teleflow.api.layouts.LayoutHandler;
import com.teleflow.api.layouts.requests.FilterLayoutRequest;
import com.teleflow.api.layouts.requests.LayoutRequest;
import com.teleflow.api.layouts.responses.CreateLayoutResponse;
import com.teleflow.api.layouts.responses.DeleteLayoutResponse;
import com.teleflow.api.layouts.responses.FilterLayoutResponse;
import com.teleflow.api.layouts.responses.GetLayoutResponse;
import com.teleflow.api.layouts.responses.SetDefaultLayoutResponse;
import com.teleflow.api.messages.MessageHandler;
import com.teleflow.api.messages.requests.MessageRequest;
import com.teleflow.api.messages.responses.DeleteMessageResponse;
import com.teleflow.api.messages.responses.MessageResponse;
import com.teleflow.api.notifications.NotificationHandler;
import com.teleflow.api.notifications.requests.NotificationRequest;
import com.teleflow.api.notifications.responses.NotificationGraphStatsResponse;
import com.teleflow.api.notifications.responses.NotificationResponse;
import com.teleflow.api.notifications.responses.NotificationStatsResponse;
import com.teleflow.api.notifications.responses.NotificationsResponse;
import com.teleflow.api.organizations.OrganizationHandler;
import com.teleflow.api.organizations.requests.CreateOrganizationRequest;
import com.teleflow.api.organizations.requests.UpdateMemberRoleRequest;
import com.teleflow.api.organizations.requests.UpdateOrganizationBrandRequest;
import com.teleflow.api.organizations.requests.UpdateOrganizationNameRequest;
import com.teleflow.api.organizations.responses.MemberResponse;
import com.teleflow.api.organizations.responses.OrganizationResponse;
import com.teleflow.api.organizations.responses.FetchOrganizationResponse;
import com.teleflow.api.organizations.responses.FetchMembersResponse;
import com.teleflow.api.organizations.responses.UpdateOrganizationBrandResponse;
import com.teleflow.api.organizations.responses.UpdateOrganizationNameResponse;
import com.teleflow.api.subscribers.SubscribersHandler;
import com.teleflow.api.subscribers.requests.BulkSubscriberRequest;
import com.teleflow.api.subscribers.requests.MarkAllMessagesRequest;
import com.teleflow.api.subscribers.requests.MarkMessageActionAsSeenRequest;
import com.teleflow.api.subscribers.requests.MarkSubscriberFeedAsRequest;
import com.teleflow.api.subscribers.requests.UpdateSubscriberCredentialsRequest;
import com.teleflow.api.subscribers.requests.UpdateSubscriberOnlineStatusRequest;
import com.teleflow.api.subscribers.requests.UpdateSubscriberPreferenceRequest;
import com.teleflow.api.subscribers.requests.UpdateSubscriberRequest;
import com.teleflow.api.subscribers.responses.BulkSubscriberResponse;
import com.teleflow.api.subscribers.responses.CreateBulkSubscriberResponse;
import com.teleflow.api.subscribers.responses.CreateSubscriberResponse;
import com.teleflow.api.subscribers.responses.DeleteCredentialsResponse;
import com.teleflow.api.subscribers.responses.SingleSubscriberPrefResponse;
import com.teleflow.api.subscribers.responses.SingleSubscriberResponse;
import com.teleflow.api.subscribers.responses.SubscriberDeleteResponse;
import com.teleflow.api.subscribers.responses.SubscriberNotificationResponse;
import com.teleflow.api.subscribers.responses.SubscriberPreferenceResponse;
import com.teleflow.api.subscribers.responses.UnseenNotificationsCountResponse;
import com.teleflow.api.tenants.TenantsHandler;
import com.teleflow.api.tenants.requests.GetTenantRequest;
import com.teleflow.api.tenants.requests.TenantRequest;
import com.teleflow.api.tenants.responses.BulkTenantResponse;
import com.teleflow.api.tenants.responses.DeleteTenantResponse;
import com.teleflow.api.tenants.responses.TenantResponse;
import com.teleflow.api.topics.TopicHandler;
import com.teleflow.api.topics.requests.FilterTopicsRequest;
import com.teleflow.api.topics.requests.RenameTopicRequest;
import com.teleflow.api.topics.requests.SubscriberAdditionRequest;
import com.teleflow.api.topics.requests.TopicRequest;
import com.teleflow.api.topics.responses.CheckTopicSubscriberResponse;
import com.teleflow.api.topics.responses.DeleteTopicResponse;
import com.teleflow.api.topics.responses.FilterTopicsResponse;
import com.teleflow.api.topics.responses.SubscriberAdditionResponse;
import com.teleflow.api.topics.responses.SubscriberRemovalResponse;
import com.teleflow.api.topics.responses.TopicResponse;
import com.teleflow.api.workflowgroups.WorkflowGroupHandler;
import com.teleflow.api.workflowgroups.request.WorkflowGroupRequest;
import com.teleflow.api.workflowgroups.responses.DeleteWorkflowGroup;
import com.teleflow.api.workflowgroups.responses.GetWorkflowGroupsResponse;
import com.teleflow.api.workflowgroups.responses.WorkflowGroupResponse;
import com.teleflow.api.workflowoverrides.WorkflowOverrideHandler;
import com.teleflow.api.workflowoverrides.request.CreateWorkflowOverrideRequest;
import com.teleflow.api.workflowoverrides.request.GetWorkflowOverrideRequest;
import com.teleflow.api.workflowoverrides.request.UpdateWorkflowOverrideRequest;
import com.teleflow.api.workflowoverrides.response.BulkWorkflowOverridesResponse;
import com.teleflow.api.workflowoverrides.response.DeleteWorkflowOverrideResponse;
import com.teleflow.api.workflowoverrides.response.WorkflowOverrideResponse;
import com.teleflow.api.workflows.WorkflowHandler;
import com.teleflow.api.workflows.requests.UpdateWorkflowRequest;
import com.teleflow.api.workflows.requests.UpdateWorkflowStatusRequest;
import com.teleflow.api.workflows.requests.WorkflowRequest;
import com.teleflow.api.workflows.responses.BulkWorkflowResponse;
import com.teleflow.api.workflows.responses.DeleteWorkflowResponse;
import com.teleflow.api.workflows.responses.SingleWorkflowResponse;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Teleflow {

    private final TeleflowConfig teleflowConfig;

    private final EventsHandler eventsHandler;

    private final NotificationHandler notificationHandler;

    private final TopicHandler topicHandler;

    private final SubscribersHandler subscribersHandler;

    private final IntegrationsHandler integrationsHandler;

    private final LayoutHandler layoutHandler;

    private final WorkflowHandler workflowHandler;

    private final WorkflowGroupHandler workflowGroupHandler;

    private final ChangeHandler changeHandler;

    private final EnvironmentHandler environmentHandler;

    private final InboundParseHandler inboundParseHandler;

    private final FeedsHandler feedsHandler;

    private final MessageHandler messageHandler;

    private final ExecutiveDetailsHandler executiveDetailsHandler;

    private final BlueprintsHandler blueprintsHandler;

    private final TenantsHandler tenantsHandler;

    private final OrganizationHandler organizationHandler;

    private final WorkflowOverrideHandler workflowOverrideHandler;


    public Teleflow(String apiKey) {
        this(new TeleflowConfig(apiKey));
    }

    public Teleflow(TeleflowConfig teleflowConfig) {
        RestHandler restHandler = new RestHandler(teleflowConfig);
        this.teleflowConfig = teleflowConfig;
        this.eventsHandler = new EventsHandler(restHandler);
        this.notificationHandler = new NotificationHandler(restHandler);
        this.subscribersHandler = new SubscribersHandler(restHandler);
        this.topicHandler = new TopicHandler(restHandler);
        this.integrationsHandler = new IntegrationsHandler(restHandler);
        this.layoutHandler = new LayoutHandler(restHandler);
        this.workflowHandler = new WorkflowHandler(restHandler);
        this.workflowGroupHandler = new WorkflowGroupHandler(restHandler);
        this.changeHandler = new ChangeHandler(restHandler);
        this.environmentHandler = new EnvironmentHandler(restHandler);
        this.inboundParseHandler = new InboundParseHandler(restHandler);
        this.feedsHandler = new FeedsHandler(restHandler);
        this.messageHandler = new MessageHandler(restHandler);
        this.executiveDetailsHandler = new ExecutiveDetailsHandler(restHandler);
        this.blueprintsHandler = new BlueprintsHandler(restHandler);
        this.tenantsHandler = new TenantsHandler(restHandler);
        this.organizationHandler = new OrganizationHandler(restHandler);
        this.workflowOverrideHandler = new WorkflowOverrideHandler(restHandler);
    }

    public TriggerEventResponse triggerEvent(TriggerEventRequest request) throws IOException, TeleflowNetworkException {
        try {
            return eventsHandler.triggerEvent(request);
        } catch (Exception e) {
            logException("Error triggering event", e);
            throw e;
        }
    }

    public BulkTriggerEventResponse bulkTriggerEvent(BulkTriggerEventRequest request) throws IOException, TeleflowNetworkException {
        try {
            return eventsHandler.bulkTriggerEvent(request);
        } catch (Exception e) {
            logException("Error Triggering Event", e);
            throw e;
        }
    }

    public TriggerEventResponse broadcastEvent(TriggerEventRequest request) throws IOException, TeleflowNetworkException {
        try {
            return eventsHandler.broadcastEvent(request);
        } catch (Exception e) {
            logException("Error BroadCasting Event", e);
            throw e;
        }
    }

    public CancelEventResponse cancelTriggeredEvent(String transactionId) throws IOException, TeleflowNetworkException {
        try {
            return eventsHandler.cancelTriggeredEvent(transactionId);
        } catch (Exception e) {
            logException("Error Canceling Event", e);
            throw e;
        }
    }

    public NotificationsResponse getNotifications(NotificationRequest request) throws IOException, TeleflowNetworkException {
        try {
            return notificationHandler.getNotifications(request);
        } catch (Exception e) {
            logException("Error Getting Notification", e);
            throw e;
        }
    }

    public NotificationStatsResponse getNotificationsStats() throws IOException, TeleflowNetworkException {
        try {
            return notificationHandler.getNotificationsStats();
        } catch (Exception e) {
            logException("Error Getting Notifications Stats", e);
            throw e;
        }
    }

    public NotificationGraphStatsResponse getNotificationGraphStats() throws IOException, TeleflowNetworkException {
        try {
            return notificationHandler.getNotificationGraphStats();
        } catch (Exception e) {
            logException("Error Getting Notifications Graph Stats", e);
            throw e;
        }
    }

    public NotificationResponse getNotification(String notificationId) throws IOException, TeleflowNetworkException {
        try {
            return notificationHandler.getNotification(notificationId);
        } catch (Exception e) {
            logException("Error Getting Notification", e);
            throw e;
        }
    }

    public BulkSubscriberResponse getSubscribers(Integer page, Integer limit) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.getSubscribers(page, limit);
        } catch (Exception e) {
            logException("Error getting Subscribers", e);
            throw e;
        }
    }

    public CreateSubscriberResponse createSubscriber(SubscriberRequest request) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.createSubscriber(request);
        } catch (Exception e) {
            logException("Error creating Subscriber", e);
            throw e;
        }
    }

    public CreateBulkSubscriberResponse createSubscriberBulk(BulkSubscriberRequest request) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.createSubscriberBulk(request);
        } catch (Exception e) {
            logException("Error creating bulk Subscribers", e);
            throw e;
        }
    }

    public SingleSubscriberResponse getSubscriber(String subscriberId) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.getSubscriber(subscriberId);
        } catch (Exception e) {
            logException("Error getting Subscriber", e);
            throw e;
        }
    }

    public SingleSubscriberResponse updateSubscriber(UpdateSubscriberRequest request, String subscriberId) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.updateSubscriber(request, subscriberId);
        } catch (Exception e) {
            logException("Error updating Subscriber", e);
            throw e;
        }
    }

    public SubscriberDeleteResponse deleteSubscriber(String subscriberId) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.deleteSubscriber(subscriberId);
        } catch (Exception e) {
            logException("Error deleting Subscriber", e);
            throw e;
        }
    }

    public SingleSubscriberResponse updateSubscriberCredentials(UpdateSubscriberCredentialsRequest request, String subscriberId) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.updateSubscriberCredentials(request, subscriberId);
        } catch (Exception e) {
            logException("Error updating Subscriber Credentials", e);
            throw e;
        }
    }

    public DeleteCredentialsResponse deleteSubscriberCredentials(String subscriberId, String providerId) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.deleteSubscriberCredentials(subscriberId, providerId);
        } catch (Exception e) {
            logException("Error deleting Subscriber Credentials", e);
            throw e;
        }
    }

    public SingleSubscriberResponse updateSubscriberOnlineStatus(UpdateSubscriberOnlineStatusRequest request, String subscriberId) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.updateSubscriberOnlineStatus(request, subscriberId);
        } catch (Exception e) {
            logException("Error updating Subscriber Online Status", e);
            throw e;
        }
    }

    public SubscriberPreferenceResponse getSubscriberPreferences(String subscriberId) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.getSubscriberPreferences(subscriberId);
        } catch (Exception e) {
            logException("Error getting Subscriber Preferences", e);
            throw e;
        }
    }

    public SingleSubscriberPrefResponse updateSubscriberPreferences(UpdateSubscriberPreferenceRequest request, String subscriberId, String templateId) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.updateSubscriberPreferences(request, subscriberId, templateId);
        } catch (Exception e) {
            logException("Error updating Subscriber Preferences", e);
            throw e;
        }
    }

    public SubscriberNotificationResponse getSubscriberNotificationsFeed(String subscriberId) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.getSubscriberNotificationsFeed(subscriberId);
        } catch (Exception e) {
            logException("Error getting Subscriber Notifications Feed", e);
            throw e;
        }
    }

    public UnseenNotificationsCountResponse getSubscriberUnseenNotificationsCount(String subscriberId) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.getSubscriberUnseenNotificationsCount(subscriberId);
        } catch (Exception e) {
            logException("Error getting Subscriber unseen Notifications Count", e);
            throw e;
        }
    }

    public SubscriberNotificationResponse markSubscriberMessageFeedAs(MarkSubscriberFeedAsRequest request, String subscriberId) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.markSubscriberMessageFeedAs(request, subscriberId);
        } catch (Exception e) {
            logException("Error marking Subscriber Message Feed", e);
            throw e;
        }
    }

    public Long markAllSubscriberMessagesFeedAs(MarkAllMessagesRequest request, String subscriberId) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.markAllSubscriberMessagesFeedAs(request, subscriberId);
        } catch (Exception e) {
            logException("Error marking all Subscriber Messages Feed", e);
            throw e;
        }
    }

    public SubscriberNotificationResponse markMessageActionAsSeen(MarkMessageActionAsSeenRequest request, String subscriberId, String messageId, String type) throws IOException, TeleflowNetworkException {
        try {
            return subscribersHandler.markMessageActionAsSeen(request, subscriberId, messageId, type);
        } catch (Exception e) {
            logException("Error marking Message Action as seen", e);
            throw e;
        }
    }

    public TopicResponse createTopic(TopicRequest request) throws IOException, TeleflowNetworkException {
        try {
            return topicHandler.createTopic(request);
        } catch (Exception e) {
            logException("Error Creating Topic", e);
            throw e;
        }
    }

    public FilterTopicsResponse filterTopics(FilterTopicsRequest request) throws IOException, TeleflowNetworkException {
        try {
            return topicHandler.filterTopics(request);
        } catch (Exception e) {
            logException("Error filtering Topic", e);
            throw e;
        }
    }

    public SubscriberAdditionResponse addSubscriberToTopic(SubscriberAdditionRequest request, String topicKey) throws IOException, TeleflowNetworkException {
        try {
            return topicHandler.addSubscriberToTopic(request, topicKey);
        } catch (Exception e) {
            logException("Error adding subscriber to Topic", e);
            throw e;
        }
    }

    public CheckTopicSubscriberResponse checkTopicSubscriber(String topicKey, String externalSubscriberId) throws IOException, TeleflowNetworkException {
        try {
            return topicHandler.checkTopicSubscriber(topicKey, externalSubscriberId);
        } catch (Exception e) {
            logException("Error checking topic subscriber", e);
            throw e;
        }
    }

    public SubscriberRemovalResponse removeSubscriberFromTopic(SubscriberAdditionRequest request, String topicKey) throws IOException, TeleflowNetworkException {
        try {
            return topicHandler.removeSubscriberFromTopic(request, topicKey);
        } catch (Exception e) {
            logException("Error removing subscriber from Topic", e);
            throw e;
        }
    }

    public DeleteTopicResponse deleteTopic(String topicKey) throws IOException, TeleflowNetworkException {
        try {
            return topicHandler.deleteTopic(topicKey);
        } catch (Exception e) {
            logException("Error Deleting Topic", e);
            throw e;
        }
    }

    public TopicResponse getTopic(String topicKey) throws IOException, TeleflowNetworkException {
        try {
            return topicHandler.getTopic(topicKey);
        } catch (Exception e) {
            logException("Error Getting Topic", e);
            throw e;
        }
    }

    public TopicResponse renameTopic(RenameTopicRequest request, String topicKey) throws IOException, TeleflowNetworkException {
        try {
            return topicHandler.renameTopic(request, topicKey);
        } catch (Exception e) {
            logException("Error renaming Topic", e);
            throw e;
        }
    }

    public BulkIntegrationResponse getIntegrations() throws IOException, TeleflowNetworkException {
        try {
            return integrationsHandler.getIntegrations();
        } catch (Exception e) {
            logException("Error getting Integrations", e);
            throw e;
        }
    }

    public SingleIntegrationResponse createIntegration(IntegrationRequest request) throws IOException, TeleflowNetworkException {
        try {
            return integrationsHandler.createIntegration(request);
        } catch (Exception e) {
            logException("Error creating Integrations", e);
            throw e;
        }
    }

    public BulkIntegrationResponse getActiveIntegrations() throws IOException, TeleflowNetworkException {
        try {
            return integrationsHandler.getActiveIntegrations();
        } catch (Exception e) {
            logException("Error getting active Integrations", e);
            throw e;
        }
    }

    public ProviderWebhookStatusResponse getProviderWebhookStatus(String providerId) throws IOException, TeleflowNetworkException {
        try {
            return integrationsHandler.getProviderWebhookStatus(providerId);
        } catch (Exception e) {
            logException("Error getting Provider Webhook Status", e);
            throw e;
        }
    }

    public SingleIntegrationResponse updateIntegration(String integrationId, IntegrationRequest request) throws IOException, TeleflowNetworkException {
        try {
            return integrationsHandler.updateIntegration(integrationId, request);
        } catch (Exception e) {
            logException("Error updating Integration", e);
            throw e;
        }
    }

    public BulkIntegrationResponse deleteIntegration(String integrationId) throws IOException, TeleflowNetworkException {
        try {
            return integrationsHandler.deleteIntegration(integrationId);
        } catch (Exception e) {
            logException("Error deleting Integration", e);
            throw e;
        }
    }

    public SingleIntegrationResponse setIntegrationAsPrimary(String integrationId) throws IOException, TeleflowNetworkException {
        try {
            return integrationsHandler.setIntegrationAsPrimary(integrationId);
        } catch (Exception e) {
            logException("Error setting Integration as primary", e);
            throw e;
        }
    }

    public CreateLayoutResponse createLayout(LayoutRequest request) throws IOException, TeleflowNetworkException {
        try {
            return layoutHandler.createLayout(request);
        } catch (Exception e) {
            logException("Error creating Layout", e);
            throw e;
        }
    }

    public FilterLayoutResponse filterLayout(FilterLayoutRequest request) throws IOException, TeleflowNetworkException {
        try {
            return layoutHandler.filterLayouts(request);
        } catch (Exception e) {
            logException("Error filtering Layout", e);
            throw e;
        }
    }

    public GetLayoutResponse getLayout(String layoutId) throws IOException, TeleflowNetworkException {
        try {
            return layoutHandler.getLayout(layoutId);
        } catch (Exception e) {
            logException("Error getting Layouts", e);
            throw e;
        }
    }

    public DeleteLayoutResponse deleteLayout(String layoutId) throws IOException, TeleflowNetworkException {
        try {
            return layoutHandler.deleteLayout(layoutId);
        } catch (Exception e) {
            logException("Error Deleting Layout", e);
            throw e;
        }
    }

    public GetLayoutResponse updateIntegration(String layoutId, LayoutRequest request) throws IOException, TeleflowNetworkException {
        try {
            return layoutHandler.updateLayout(layoutId, request);
        } catch (Exception e) {
            logException("Error updating Layout", e);
            throw e;
        }
    }

    public SetDefaultLayoutResponse setDefaultLayout(String layoutId) throws IOException, TeleflowNetworkException {
        try {
            return layoutHandler.setDefaultLayout(layoutId);
        } catch (Exception e) {
            logException("Error Setting Default Layout", e);
            throw e;
        }
    }

    public BulkWorkflowResponse getWorkflows(Integer page, Integer limit) throws IOException, TeleflowNetworkException {
        try {
            return workflowHandler.getWorkflows(page, limit);
        } catch (Exception e) {
            logException("Error getting Workflows", e);
            throw e;
        }
    }

    public SingleWorkflowResponse createWorkflow(WorkflowRequest request) throws IOException, TeleflowNetworkException {
        try {
            return workflowHandler.createWorkflow(request);
        } catch (Exception e) {
            logException("Error creating Workflow", e);
            throw e;
        }
    }

    public SingleWorkflowResponse updateWorkflow(String workflowId, UpdateWorkflowRequest request) throws IOException, TeleflowNetworkException {
        try {
            return workflowHandler.updateWorkflow(workflowId, request);
        } catch (Exception e) {
            logException("Error updating Workflow", e);
            throw e;
        }
    }

    public DeleteWorkflowResponse deleteWorkflow(String workflowId) throws IOException, TeleflowNetworkException {
        try {
            return workflowHandler.deleteWorkflow(workflowId);
        } catch (Exception e) {
            logException("Error deleting Workflow", e);
            throw e;
        }
    }

    public SingleWorkflowResponse getWorkflow(String workflowId) throws IOException, TeleflowNetworkException {
        try {
            return workflowHandler.getWorkflow(workflowId);
        } catch (Exception e) {
            logException("Error getting Workflow", e);
            throw e;
        }
    }

    public SingleWorkflowResponse updateWorkflowStatus(String workflowId, UpdateWorkflowStatusRequest request) throws IOException, TeleflowNetworkException {
        try {
            return workflowHandler.updateWorkflowStatus(workflowId, request);
        } catch (Exception e) {
            logException("Error updating Workflow status", e);
            throw e;
        }
    }

    public WorkflowGroupResponse createWorkflowGroup(WorkflowGroupRequest request) throws IOException, TeleflowNetworkException {
        try {
            return workflowGroupHandler.createWorkflowGroup(request);
        } catch (Exception e) {
            logException("Error creating workflow group", e);
            throw e;
        }
    }

    public GetWorkflowGroupsResponse getWorkflowGroups() throws IOException, TeleflowNetworkException {
        try {
            return workflowGroupHandler.getWorkflowGroups();
        } catch (Exception e) {
            logException("Error getting workflow groups", e);
            throw e;
        }
    }

    public WorkflowGroupResponse getWorkflowGroup(String id) throws IOException, TeleflowNetworkException {
        try {
            return workflowGroupHandler.getWorkflowGroup(id);
        } catch (Exception e) {
            logException("Error getting workflow group", e);
            throw e;
        }
    }

    public WorkflowGroupResponse updateWorkflowGroup(String id, WorkflowGroupRequest request) throws IOException, TeleflowNetworkException {
        try {
            return workflowGroupHandler.updateWorkflowGroup(id,request);
        } catch (Exception e) {
            logException("Error updating workflow group", e);
            throw e;
        }
    }

    public DeleteWorkflowGroup deleteWorkflowGroup(String id) throws IOException, TeleflowNetworkException {
        try {
            return workflowGroupHandler.deleteWorkflowGroup(id);
        } catch (Exception e) {
            logException("Error deleting workflow group", e);
            throw e;
        }
    }

    public GetChangesResponse getChanges(GetChangesRequest request) throws IOException, TeleflowNetworkException {
        try {
            return changeHandler.getChanges(request);
        } catch (Exception e) {
            logException("Error getting changes", e);
            throw e;
        }
    }

    public ChangeCountResponse getChangesCount() throws IOException, TeleflowNetworkException {
        try {
            return changeHandler.getChangesCount();
        } catch (Exception e) {
            logException("Error getting changes count", e);
            throw e;
        }
    }

    public ApplyChangesResponse applyChanges(ApplyChangesRequest request) throws IOException, TeleflowNetworkException {
        try {
            return changeHandler.applyChanges(request);
        } catch (Exception e) {
            logException("Error applying changes", e);
            throw e;
        }
    }

    public ApplyChangesResponse applyChange(String changeId) throws IOException, TeleflowNetworkException {
        try {
            return changeHandler.applyChange(changeId);
        } catch (Exception e) {
            logException("Error applying change", e);
            throw e;
        }
    }

    public SingleEnvironmentResponse getCurrentEnvironment() throws IOException, TeleflowNetworkException {
        try {
            return environmentHandler.getCurrentEnvironment();
        } catch (Exception e) {
            logException("Error getting current Environment", e);
            throw e;
        }
    }

    public SingleEnvironmentResponse createEnvironment(CreateEnvironmentRequest request) throws IOException, TeleflowNetworkException {
        try {
            return environmentHandler.createEnvironment(request);
        } catch (Exception e) {
            logException("Error creating Environment", e);
            throw e;
        }
    }

    public BulkEnvironmentResponse getEnvironments() throws IOException, TeleflowNetworkException {
        try {
            return environmentHandler.getEnvironments();
        } catch (Exception e) {
            logException("Error getting Environments", e);
            throw e;
        }
    }

    public SingleEnvironmentResponse updateEnvironmentById(String environmentId, UpdateEnvironmentRequest request) throws IOException, TeleflowNetworkException {
        try {
            return environmentHandler.updateEnvironmentById(environmentId, request);
        } catch (Exception e) {
            logException("Error updating Environment by ID", e);
            throw e;
        }
    }

    public ApiKeyResponse getApiKeys() throws IOException, TeleflowNetworkException {
        try {
            return environmentHandler.getApiKeys();
        } catch (Exception e) {
            logException("Error getting Api Keys", e);
            throw e;
        }
    }

    public ApiKeyResponse regenerateApiKeys() throws IOException, TeleflowNetworkException {
        try {
            return environmentHandler.regenerateApiKeys();
        } catch (Exception e) {
            logException("Error regenerating Api Keys", e);
            throw e;
        }
    }

    public ValidateMxRecordResponse validateMxRecordSetupForInboundParse() throws IOException, TeleflowNetworkException {
        try {
            return inboundParseHandler.validateMxRecordSetupForInboundParse();
        } catch (Exception e) {
            logException("Error validating Mx record setup", e);
            throw e;
        }
    }

    public FeedResponse createFeed(FeedRequest request) throws IOException, TeleflowNetworkException {
        try {
            return feedsHandler.createFeed(request);
        } catch (Exception e) {
            logException("Error creating feed", e);
            throw e;
        }
    }

    public BulkFeedsResponse getFeeds() throws IOException, TeleflowNetworkException {
        try {
            return feedsHandler.getFeeds();
        } catch (Exception e) {
            logException("Error getting feed", e);
            throw e;
        }
    }

    public BulkFeedsResponse deleteFeed(String feedId) throws IOException, TeleflowNetworkException {
        try {
            return feedsHandler.deleteFeed(feedId);
        } catch (Exception e) {
            logException("Error deleting feed", e);
            throw e;
        }
    }

    public MessageResponse getMessages(MessageRequest request) throws IOException, TeleflowNetworkException {
        try {
            return messageHandler.getMessages(request);
        } catch (Exception e) {
            logException("Error getting Messages", e);
            throw e;
        }
    }

    public DeleteMessageResponse deleteMessage(String messageId) throws IOException, TeleflowNetworkException {
        try {
            return messageHandler.deleteMessage(messageId);
        } catch (Exception e) {
            logException("Error deleting Message", e);
            throw e;
        }
    }

    public ExecutiveDetailsResponse getExecutionDetails(String notificationId, String subscriberId) throws IOException, TeleflowNetworkException {
        try {
            return executiveDetailsHandler.getExecutionDetails(notificationId, subscriberId);
        } catch (Exception e) {
            logException("Error getting Execution Details", e);
            throw e;
        }
    }

    public BlueprintsByCategoryResponse getBlueprintsByCategory() throws IOException, TeleflowNetworkException {
        try {
            return blueprintsHandler.getBlueprintsByCategory();
        } catch (Exception e) {
            logException("Error getting Blueprints by Category", e);
            throw e;
        }
    }

    public Blueprint getBlueprint(String templateId) throws IOException, TeleflowNetworkException {
        try {
            return blueprintsHandler.getBlueprint(templateId);
        } catch (Exception e) {
            logException("Error getting Blueprint", e);
            throw e;
        }
    }

    public BulkTenantResponse getTenants(GetTenantRequest request) throws IOException, TeleflowNetworkException {
        try {
            return tenantsHandler.getTenants(request);
        } catch (Exception e) {
            logException("Error getting Tenants", e);
            throw e;
        }
    }

    public TenantResponse createTenant(TenantRequest request) throws IOException, TeleflowNetworkException {
        try {
            return tenantsHandler.createTenant(request);
        } catch (Exception e) {
            logException("Error creating Tenant", e);
            throw e;
        }
    }

    public TenantResponse getTenant(String identifier) throws IOException, TeleflowNetworkException {
        try {
            return tenantsHandler.getTenant(identifier);
        } catch (Exception e) {
            logException("Error getting Tenant", e);
            throw e;
        }
    }

    public TenantResponse updateTenant(TenantRequest request, String identifier) throws IOException, TeleflowNetworkException {
        try {
            return tenantsHandler.updateTenant(request, identifier);
        } catch (Exception e) {
            logException("Error updating Tenant", e);
            throw e;
        }
    }

    public DeleteTenantResponse deleteTenant(String identifier) throws IOException, TeleflowNetworkException {
        try {
            return tenantsHandler.deleteTenant(identifier);
        } catch (Exception e) {
            logException("Error deleting Tenant", e);
            throw e;
        }
    }

    public OrganizationResponse createOrganization(CreateOrganizationRequest request) throws IOException, TeleflowNetworkException {
        try {
            return organizationHandler.createOrganization(request);
        } catch (Exception e) {
            logException("Error creating Organization", e);
            throw e;
        }
    }

    public FetchOrganizationResponse fetchAllOrganizations() throws IOException, TeleflowNetworkException {
        try {
            return organizationHandler.fetchAllOrganizations();
        } catch (Exception e) {
            logException("Error fetching Organizations", e);
            throw e;
        }
    }

    public UpdateOrganizationNameResponse updateOrganizationName(UpdateOrganizationNameRequest request) throws IOException, TeleflowNetworkException {
        try {
            return organizationHandler.updateOrganizationName(request);
        } catch (Exception e) {
            logException("Error Updating Organization Name", e);
            throw e;
        }
    }

    public OrganizationResponse fetchCurrentOrganization() throws IOException, TeleflowNetworkException {
        try {
            return organizationHandler.fetchCurrentOrganization();
        } catch (Exception e) {
            logException("Error Fetching Current Organization", e);
            throw e;
        }
    }

    public MemberResponse removeMemberWithId(String memberId) throws IOException, TeleflowNetworkException {
        try {
            return organizationHandler.removeMemberWithId(memberId);
        } catch (Exception e) {
            logException("Error Removing Member With MemberId", e);
            throw e;
        }
    }

    public MemberResponse updateMemberRole(String memberId, UpdateMemberRoleRequest request) throws IOException, TeleflowNetworkException {
        try {
            return organizationHandler.updateMemberRole(memberId, request);
        } catch (Exception e) {
            logException("Error Updating Member Role", e);
            throw e;
        }
    }

    public FetchMembersResponse fetchMembersOfOrganization()throws IOException, TeleflowNetworkException {
        try {
            return organizationHandler.fetchMembersOfOrganization();
        } catch (Exception e) {
            logException("Error Fetching Organization Members", e);
            throw e;
        }
    }

    public UpdateOrganizationBrandResponse updateOrganizationBrand(UpdateOrganizationBrandRequest request)throws IOException, TeleflowNetworkException {
        try {
            return organizationHandler.updateOrganizationBrand(request);
        } catch (Exception e) {
            logException("Error Updating Organization Brand", e);
            throw e;
        }
    }

    public WorkflowOverrideResponse createWorkflowOverride(CreateWorkflowOverrideRequest request) throws IOException, TeleflowNetworkException {
        try {
            return workflowOverrideHandler.createWorkflowOverride(request);
        } catch (Exception e) {
            logException("Error creating Workflow-Override", e);
            throw e;
        }
    }

    public BulkWorkflowOverridesResponse getWorkflowOverrides(GetWorkflowOverrideRequest request) throws IOException, TeleflowNetworkException {
        try {
            return workflowOverrideHandler.getWorkflowOverrides(request);
        } catch (Exception e) {
            logException("Error fetching bulk Workflow-Override", e);
            throw e;
        }
    }

    public WorkflowOverrideResponse getWorkflowOverride(String workflowId, String tenantId) throws IOException, TeleflowNetworkException {
        try {
            return workflowOverrideHandler.getWorkflowOverride(workflowId, tenantId);
        } catch (Exception e) {
            logException("Error fetching Workflow-Override", e);
            throw e;
        }
    }

    public WorkflowOverrideResponse getWorkflowOverrideById(String overrideId) throws IOException, TeleflowNetworkException {
        try {
            return workflowOverrideHandler.getWorkflowOverrideById(overrideId);
        } catch (Exception e) {
            logException("Error fetching Workflow-Override by id", e);
            throw e;
        }
    }

    public WorkflowOverrideResponse updateWorkflowOverrideById(String overrideId, UpdateWorkflowOverrideRequest request) throws IOException, TeleflowNetworkException {
        try {
            return workflowOverrideHandler.updateWorkflowOverrideById(overrideId, request);
        } catch (Exception e) {
            logException("Error updating Workflow-Override by id", e);
            throw e;
        }
    }

    public WorkflowOverrideResponse updateWorkflowOverride(String workflowId, String tenantId, UpdateWorkflowOverrideRequest request) throws IOException, TeleflowNetworkException {
        try {
            return workflowOverrideHandler.updateWorkflowOverride(workflowId, tenantId, request);
        } catch (Exception e) {
            logException("Error updating Workflow-Override", e);
            throw e;
        }
    }

    public DeleteWorkflowOverrideResponse deleteWorkflowOverride(String overrideId) throws IOException, TeleflowNetworkException {
        try {
            return workflowOverrideHandler.deleteWorkflowOverride(overrideId);
        } catch (Exception e) {
            logException("Error deleting Workflow-Override", e);
            throw e;
        }
    }

    private void logException(String message, Exception e) {
        if (!teleflowConfig.isEnableLogging()) {
            return;
        }
        log.error(message, e);
    }
}
