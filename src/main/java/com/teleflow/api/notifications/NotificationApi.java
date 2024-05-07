package com.teleflow.api.notifications;

import com.teleflow.api.notifications.responses.NotificationGraphStatsResponse;
import com.teleflow.api.notifications.responses.NotificationResponse;
import com.teleflow.api.notifications.responses.NotificationStatsResponse;
import com.teleflow.api.notifications.responses.NotificationsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;

/**
 * @author : Debanjan Choudhury
 * @since : 09/10/23
 **/
public interface NotificationApi {

    String ENDPOINT = "notifications";

    @GET(ENDPOINT)
    Call<NotificationsResponse> getNotifications(@QueryMap Map<String, Object> options);

    @GET(ENDPOINT + "/stats")
    Call<NotificationStatsResponse> getNotificationsStats();

    @GET(ENDPOINT + "/graph/stats")
    Call<NotificationGraphStatsResponse> getNotificationGraphStats();

    @GET(ENDPOINT +"/{notificationId}")
    Call<NotificationResponse> getNotification(@Path("notificationId") String notificationId);
}
