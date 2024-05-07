package com.teleflow.api.subscribers;

import com.teleflow.api.common.SubscriberRequest;

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
import com.teleflow.api.subscribers.responses.SingleSubscriberResponse;
import com.teleflow.api.subscribers.responses.SingleSubscriberPrefResponse;
import com.teleflow.api.subscribers.responses.SubscriberDeleteResponse;
import com.teleflow.api.subscribers.responses.UnseenNotificationsCountResponse;
import com.teleflow.api.subscribers.responses.SubscriberNotificationResponse;
import com.teleflow.api.subscribers.responses.SubscriberPreferenceResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface SubscribersApi {
    String ENDPOINT = "subscribers";

    @GET(ENDPOINT)
    Call<BulkSubscriberResponse> getSubscribers(@QueryMap Map<String, Object> options);

    @POST(ENDPOINT)
    Call<CreateSubscriberResponse> createSubscriber(@Body SubscriberRequest request);

    @POST(ENDPOINT + "/bulk")
    Call<CreateBulkSubscriberResponse> createBulkSubscriber(@Body BulkSubscriberRequest request);

    @GET(ENDPOINT + "/{subscriberId}")
    Call<SingleSubscriberResponse> getSubscriber(@Path("subscriberId") String subscriberId);

    @PUT(ENDPOINT + "/{subscriberId}")
    Call<SingleSubscriberResponse> updateSubscriber(@Body UpdateSubscriberRequest request, @Path("subscriberId") String subscriberId);

    @DELETE(ENDPOINT + "/{subscriberId}")
    Call<SubscriberDeleteResponse> deleteSubscriber(@Path("subscriberId") String subscriberId);

    @PUT(ENDPOINT + "/{subscriberId}/credentials")
    Call<SingleSubscriberResponse> updateSubscriberCredentials(@Body UpdateSubscriberCredentialsRequest request, @Path("subscriberId") String subscriberId);

    @DELETE(ENDPOINT + "/{subscriberId}/credentials/{providerId}")
    Call<Void> deleteSubscriberCredentials(@Path("subscriberId") String subscriberId, @Path("providerId") String providerId);

    @PATCH(ENDPOINT + "/{subscriberId}/online-status")
    Call<SingleSubscriberResponse> updateSubscriberOnlineStatus(@Body UpdateSubscriberOnlineStatusRequest request, @Path("subscriberId") String subscriberId);

    @GET(ENDPOINT + "/{subscriberId}/preferences")
    Call<SubscriberPreferenceResponse> getSubscriberPreferences(@Path("subscriberId") String subscriberId);

    @PATCH(ENDPOINT + "/{subscriberId}/preferences/{templateId}")
    Call<SingleSubscriberPrefResponse> updateSubscriberPreferences(@Body UpdateSubscriberPreferenceRequest request, @Path("subscriberId") String subscriberId, @Path("templateId") String templateId);

    @GET(ENDPOINT + "/{subscriberId}/notifications/feed")
    Call<SubscriberNotificationResponse> getSubscriberNotificationsFeed(@Path("subscriberId") String subscriberId);

    @GET(ENDPOINT + "/{subscriberId}/notifications/unseen")
    Call<UnseenNotificationsCountResponse> getSubscriberUnseenNotificationsCount(@Path("subscriberId") String subscriberId);

    @POST(ENDPOINT + "/{subscriberId}/messages/markAs")
    Call<SubscriberNotificationResponse> markSubscriberMessageFeedAs(@Body MarkSubscriberFeedAsRequest request, @Path("subscriberId") String subscriberId);

    @POST(ENDPOINT + "/{subscriberId}/messages/mark-all")
    Call<Long> markAllSubscriberMessagesFeedAs(@Body MarkAllMessagesRequest request, @Path("subscriberId") String subscriberId);

    @POST(ENDPOINT + "/{subscriberId}/messages/{messageId}/actions/{type}")
    Call<SubscriberNotificationResponse> markMessageActionAsSeen(@Body MarkMessageActionAsSeenRequest request, @Path("subscriberId") String subscriberId, @Path("messageId") String messageId, @Path("type") String type);
}
