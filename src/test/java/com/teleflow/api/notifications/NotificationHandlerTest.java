package com.teleflow.api.notifications;

import com.teleflow.api.notifications.pojos.Notification;
import com.teleflow.api.notifications.pojos.NotificationGraphStats;
import com.teleflow.api.notifications.pojos.NotificationStats;
import com.teleflow.api.notifications.requests.NotificationRequest;
import com.teleflow.api.notifications.responses.NotificationGraphStatsResponse;
import com.teleflow.api.notifications.responses.NotificationResponse;
import com.teleflow.api.notifications.responses.NotificationStatsResponse;
import com.teleflow.api.notifications.responses.NotificationsResponse;
import com.teleflow.common.base.TeleflowConfig;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import com.google.gson.Gson;
import junit.framework.TestCase;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

public class NotificationHandlerTest extends TestCase {

    private NotificationHandler notificationHandler;

    private MockWebServer mockWebServer;

    private final Gson gson = new Gson();

    @Override
    protected void setUp() {
        mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        notificationHandler = new NotificationHandler(restHandler);
    }

    public void test_getNotifications() throws IOException, TeleflowNetworkException, InterruptedException {
        NotificationRequest request = new NotificationRequest();
        request.setPage(1);
        request.setTransactionId("tId");
        request.setSearch("query");

        NotificationsResponse notificationsResponse = new NotificationsResponse();
        notificationsResponse.setPage(1L);
        notificationsResponse.setPageSize(10L);
        notificationsResponse.setTotalCount(100L);
        Notification notification = new Notification();
        notification.setTransactionId("tId");
        notificationsResponse.setData(List.of(notification));

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(notificationsResponse)));

        NotificationsResponse response = notificationHandler.getNotifications(request);
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/notifications?search=query&page=1&transactionId=tId", recordedRequest.getPath());
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals(notificationsResponse, response);
    }

    public void test_getNotificationsStats() throws IOException, TeleflowNetworkException, InterruptedException {
        NotificationStatsResponse notificationsResponse = new NotificationStatsResponse();
        NotificationStats notificationStats = new NotificationStats();
        notificationStats.setWeeklySent(20L);
        notificationStats.setMonthlySent(200L);
        notificationsResponse.setData(notificationStats);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(notificationsResponse)));

        NotificationStatsResponse response = notificationHandler.getNotificationsStats();
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/notifications/stats", recordedRequest.getPath());
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals(notificationsResponse, response);
    }

    public void test_getNotificationGraphStats() throws IOException, TeleflowNetworkException, InterruptedException {
        NotificationGraphStatsResponse notificationsResponse = new NotificationGraphStatsResponse();
        NotificationGraphStats notificationGraphStats = new NotificationGraphStats();
        notificationGraphStats.setCount(200L);
        notificationsResponse.setData(List.of(notificationGraphStats));

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(notificationsResponse)));

        NotificationGraphStatsResponse response = notificationHandler.getNotificationGraphStats();
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/notifications/graph/stats", recordedRequest.getPath());
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals(notificationsResponse, response);
    }

    public void test_getNotification() throws IOException, TeleflowNetworkException, InterruptedException {
        NotificationResponse notificationsResponse = new NotificationResponse();
        Notification notification = new Notification();
        notification.setTransactionId("tid");
        notificationsResponse.setData(notification);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(notificationsResponse)));

        NotificationResponse response = notificationHandler.getNotification("id");
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/notifications/id", recordedRequest.getPath());
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals(notificationsResponse, response);
    }
}