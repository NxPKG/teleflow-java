package com.teleflow.api.subscribers;

import com.teleflow.api.layouts.LayoutApi;
import com.teleflow.api.subscribers.requests.BulkSubscriberRequest;
import com.teleflow.api.subscribers.requests.MarkAllMessagesRequest;
import com.teleflow.api.subscribers.requests.MarkMessageActionAsSeenRequest;
import com.teleflow.api.subscribers.requests.MarkSubscriberFeedAsRequest;
import com.teleflow.api.subscribers.responses.CreateBulkSubscriberResponse;
import com.teleflow.api.subscribers.responses.DeleteCredentialsResponse;
import com.teleflow.api.subscribers.responses.SingleSubscriberPrefResponse;
import com.teleflow.api.subscribers.responses.SubscriberNotificationResponse;
import com.teleflow.api.common.SubscriberRequest;
import com.teleflow.api.subscribers.requests.UpdateSubscriberCredentialsRequest;
import com.teleflow.api.subscribers.requests.UpdateSubscriberOnlineStatusRequest;
import com.teleflow.api.subscribers.requests.UpdateSubscriberPreferenceRequest;
import com.teleflow.api.subscribers.requests.UpdateSubscriberRequest;
import com.teleflow.api.subscribers.responses.BulkSubscriberResponse;
import com.teleflow.api.subscribers.responses.CreateSubscriberResponse;
import com.teleflow.api.subscribers.responses.SingleSubscriberResponse;
import com.teleflow.api.subscribers.responses.SubscriberDeleteResponse;
import com.teleflow.api.subscribers.responses.SubscriberPreferenceResponse;
import com.teleflow.api.subscribers.responses.UnseenNotificationsCountResponse;
import com.teleflow.common.base.TeleflowConfig;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import lombok.RequiredArgsConstructor;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class SubscribersHandler {

    private final RestHandler restHandler;

    private final SubscribersApi subscribersApi;

    public SubscribersHandler(RestHandler restHandler){
        this.restHandler = restHandler;
        this.subscribersApi = restHandler.buildRetrofit().create(SubscribersApi.class);
    }

    public BulkSubscriberResponse getSubscribers(Integer page, Integer limit) throws IOException, TeleflowNetworkException {
        Map<String, Object> params = new HashMap<>();
        if (page != null) params.put("page", page);
        if (limit != null) params.put("limit", limit);

        Response<BulkSubscriberResponse> bulkSubscriberResponse = subscribersApi.getSubscribers(params).execute();
        return restHandler.extractResponse(bulkSubscriberResponse);
    }

    public CreateSubscriberResponse createSubscriber(SubscriberRequest request) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.createSubscriber(request).execute());
    }

    public CreateBulkSubscriberResponse createSubscriberBulk(BulkSubscriberRequest request) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.createBulkSubscriber(request).execute());
    }

    public SingleSubscriberResponse getSubscriber(String subscriberId) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.getSubscriber(subscriberId).execute());
    }

    public SingleSubscriberResponse updateSubscriber(UpdateSubscriberRequest request, String subscriberId) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.updateSubscriber(request, subscriberId).execute());
    }

    public SubscriberDeleteResponse deleteSubscriber(String subscriberId) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.deleteSubscriber(subscriberId).execute());
    }

    public SingleSubscriberResponse updateSubscriberCredentials(UpdateSubscriberCredentialsRequest request, String subscriberId) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.updateSubscriberCredentials(request, subscriberId).execute());
    }

    public DeleteCredentialsResponse deleteSubscriberCredentials(String subscriberId, String providerId) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.deleteSubscriberCredentials(subscriberId, providerId).execute(), new DeleteCredentialsResponse());
    }

    public SingleSubscriberResponse updateSubscriberOnlineStatus(UpdateSubscriberOnlineStatusRequest request, String subscriberId) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.updateSubscriberOnlineStatus(request, subscriberId).execute());
    }

    public SubscriberPreferenceResponse getSubscriberPreferences(String subscriberId) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.getSubscriberPreferences(subscriberId).execute());
    }

    public SingleSubscriberPrefResponse updateSubscriberPreferences(UpdateSubscriberPreferenceRequest request, String subscriberId, String templateId) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.updateSubscriberPreferences(request, subscriberId, templateId).execute());
    }

    public SubscriberNotificationResponse getSubscriberNotificationsFeed(String subscriberId) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.getSubscriberNotificationsFeed(subscriberId).execute());
    }

    public UnseenNotificationsCountResponse getSubscriberUnseenNotificationsCount(String subscriberId) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.getSubscriberUnseenNotificationsCount(subscriberId).execute());
    }

    public SubscriberNotificationResponse markSubscriberMessageFeedAs(MarkSubscriberFeedAsRequest request, String subscriberId) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.markSubscriberMessageFeedAs(request, subscriberId).execute());
    }

    public Long markAllSubscriberMessagesFeedAs(MarkAllMessagesRequest request, String subscriberId) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.markAllSubscriberMessagesFeedAs(request, subscriberId).execute());
    }

    public SubscriberNotificationResponse markMessageActionAsSeen(MarkMessageActionAsSeenRequest request, String subscriberId, String messageId, String type) throws IOException, TeleflowNetworkException {
        return restHandler.extractResponse(subscribersApi.markMessageActionAsSeen(request, subscriberId, messageId, type).execute());
    }
}
