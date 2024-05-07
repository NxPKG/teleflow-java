package com.teleflow.api.subscribers;

import com.teleflow.api.common.SubscriberRequest;
import com.teleflow.api.subscribers.pojos.Mark;
import com.teleflow.api.subscribers.pojos.Preference;
import com.teleflow.api.subscribers.pojos.SubscriberNotification;
import com.teleflow.api.subscribers.pojos.SubscriberPreference;
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
import com.teleflow.api.subscribers.responses.DeleteResponse;
import com.teleflow.api.subscribers.responses.SingleSubscriberPrefResponse;
import com.teleflow.api.subscribers.responses.SingleSubscriberResponse;
import com.teleflow.api.subscribers.responses.SubscriberDeleteResponse;
import com.teleflow.api.subscribers.responses.SubscriberNotificationResponse;
import com.teleflow.api.subscribers.responses.SubscriberPreferenceResponse;
import com.teleflow.api.subscribers.responses.SubscriberResponse;
import com.teleflow.api.subscribers.responses.UnseenNotificationsCountResponse;
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

import static org.junit.Assert.assertThrows;

public class SubscribersHandlerTest extends TestCase {

    private SubscribersHandler subscribersHandler;

    private MockWebServer mockWebServer;
    Gson gson = new Gson();

    @Override
    protected void setUp() {
        mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        subscribersHandler = new SubscribersHandler(restHandler);
    }

    public void test_getSubscribersWithValidParams() throws IOException, TeleflowNetworkException, InterruptedException {
        BulkSubscriberResponse bulkSubscriberResponse = new BulkSubscriberResponse();
        bulkSubscriberResponse.setPage(1L);
        bulkSubscriberResponse.setPageSize(10L);
        bulkSubscriberResponse.setTotalCount(100L);
        bulkSubscriberResponse.setData(List.of(new SubscriberResponse()));


        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(bulkSubscriberResponse)));
        BulkSubscriberResponse response = subscribersHandler.getSubscribers(1, 10);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/subscribers?limit=10&page=1", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(bulkSubscriberResponse, response);
    }

    public void test_getSubscribersWithNullParams() throws IOException, TeleflowNetworkException, InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(new SubscriberResponse())));
        subscribersHandler.getSubscribers(null, null);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/subscribers", request.getPath());
        assertEquals("GET", request.getMethod());
    }

    public void test_createSubscriber() throws IOException, TeleflowNetworkException, InterruptedException {
        SubscriberRequest subscriberRequest = new SubscriberRequest();
        subscriberRequest.setFirstName("fName");
        subscriberRequest.setLastName("lName");
        subscriberRequest.setEmail("email@sample.com");

        CreateSubscriberResponse createSubscriberResponse = new CreateSubscriberResponse();
        SubscriberResponse subscriberResponse = new SubscriberResponse();
        subscriberResponse.setSubscriberId("12345");
        subscriberResponse.setFirstName("fName");
        subscriberResponse.setLastName("lName");
        subscriberResponse.setIsOnline(true);
        createSubscriberResponse.setData(subscriberResponse);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(createSubscriberResponse)));
        CreateSubscriberResponse response = subscribersHandler.createSubscriber(subscriberRequest);
        assertNotNull(response);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/subscribers", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(createSubscriberResponse, response);
    }

    public void test_createSubscriberBulk() throws IOException, TeleflowNetworkException, InterruptedException {
        SubscriberRequest subscriberRequest = new SubscriberRequest();
        subscriberRequest.setFirstName("fName");
        subscriberRequest.setLastName("lName");
        subscriberRequest.setEmail("email@sample.com");
        subscriberRequest.setSubscriberId("sId");

        BulkSubscriberRequest bulkSubscriberRequest = new BulkSubscriberRequest();
        bulkSubscriberRequest.setSubscribers(List.of(subscriberRequest));

        CreateBulkSubscriberResponse createSubscriberResponse = new CreateBulkSubscriberResponse();
        createSubscriberResponse.setCreated(List.of("sId"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(createSubscriberResponse)));

        CreateBulkSubscriberResponse response = subscribersHandler.createSubscriberBulk(bulkSubscriberRequest);
        assertNotNull(response);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/subscribers/bulk", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(createSubscriberResponse, response);
        assertFalse(response.getCreated().isEmpty());
    }

    public void test_getSubscriber() throws IOException, TeleflowNetworkException, InterruptedException {
        SingleSubscriberResponse singleSubscriberResponse = new SingleSubscriberResponse();
        SubscriberResponse subscriberResponse = new SubscriberResponse();
        subscriberResponse.setSubscriberId("12345");
        subscriberResponse.setFirstName("fName");
        subscriberResponse.setLastName("lName");
        subscriberResponse.setIsOnline(true);
        singleSubscriberResponse.setData(subscriberResponse);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(singleSubscriberResponse)));

        SingleSubscriberResponse response = subscribersHandler.getSubscriber("id");
        assertNotNull(response);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/subscribers/id", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(singleSubscriberResponse, response);
    }

    public void test_updateSubscriber() throws IOException, TeleflowNetworkException, InterruptedException {
        UpdateSubscriberRequest request = new UpdateSubscriberRequest();
        request.setFirstName("name");
        request.setLastName("lName");

        SingleSubscriberResponse singleSubscriberResponse = new SingleSubscriberResponse();
        SubscriberResponse subscriberResponse = new SubscriberResponse();
        subscriberResponse.setSubscriberId("12345");
        subscriberResponse.setFirstName("name");
        subscriberResponse.setLastName("lName");
        subscriberResponse.setIsOnline(true);
        singleSubscriberResponse.setData(subscriberResponse);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(singleSubscriberResponse)));

        SingleSubscriberResponse response = subscribersHandler.updateSubscriber(request, "up-id");
        assertNotNull(response);
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/subscribers/up-id", recordedRequest.getPath());
        assertEquals("PUT", recordedRequest.getMethod());
        assertEquals(singleSubscriberResponse, response);
    }

    public void test_deleteSubscriber() throws IOException, TeleflowNetworkException, InterruptedException {
        SubscriberDeleteResponse subscriberDeleteResponse = new SubscriberDeleteResponse();
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setAcknowledged(true);
        deleteResponse.setStatus("done");
        subscriberDeleteResponse.setData(deleteResponse);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(subscriberDeleteResponse)));

        SubscriberDeleteResponse response = subscribersHandler.deleteSubscriber("del-id");
        assertNotNull(response);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/subscribers/del-id", recordedRequest.getPath());
        assertEquals("DELETE", recordedRequest.getMethod());
        assertEquals(subscriberDeleteResponse, response);
    }

    public void test_updateSubscriberCredentials() throws IOException, TeleflowNetworkException, InterruptedException {
        UpdateSubscriberCredentialsRequest request = new UpdateSubscriberCredentialsRequest();
        request.setProviderId("pId");

        SingleSubscriberResponse singleSubscriberResponse = new SingleSubscriberResponse();
        SubscriberResponse subscriberResponse = new SubscriberResponse();
        subscriberResponse.setSubscriberId("12345");
        subscriberResponse.setFirstName("name");
        subscriberResponse.setLastName("lName");
        subscriberResponse.setIsOnline(true);
        singleSubscriberResponse.setData(subscriberResponse);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(singleSubscriberResponse)));

        SingleSubscriberResponse response = subscribersHandler.updateSubscriberCredentials(request, "id");
        assertNotNull(response);
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/subscribers/id/credentials", recordedRequest.getPath());
        assertEquals("PUT", recordedRequest.getMethod());
        assertEquals(singleSubscriberResponse, response);
    }

    public void test_deleteSubscriberCredentialsFailure() throws IOException, InterruptedException, TeleflowNetworkException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(400).setBody("{}"));

        assertThrows(TeleflowNetworkException.class,
            () -> subscribersHandler.deleteSubscriberCredentials("sId", "pId"));
        
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/subscribers/sId/credentials/pId", request.getPath());
        assertEquals("DELETE", request.getMethod());
    }

    public void test_deleteSubscriberCredentialsSuccess() throws IOException, InterruptedException, TeleflowNetworkException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody("{}"));

        DeleteCredentialsResponse response = subscribersHandler.deleteSubscriberCredentials("sId", "pId");
        assertNotNull(response);
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/subscribers/sId/credentials/pId", request.getPath());
        assertEquals("DELETE", request.getMethod());
        assertTrue(response.getAcknowledged());
    }

    public void test_updateSubscriberOnlineStatus() throws IOException, TeleflowNetworkException, InterruptedException {
        UpdateSubscriberOnlineStatusRequest request = new UpdateSubscriberOnlineStatusRequest();
        request.setIsOnline(true);

        SingleSubscriberResponse singleSubscriberResponse = new SingleSubscriberResponse();
        SubscriberResponse subscriberResponse = new SubscriberResponse();
        subscriberResponse.setSubscriberId("12345");
        subscriberResponse.setFirstName("name");
        subscriberResponse.setLastName("lName");
        subscriberResponse.setIsOnline(true);
        singleSubscriberResponse.setData(subscriberResponse);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(singleSubscriberResponse)));

        SingleSubscriberResponse response = subscribersHandler.updateSubscriberOnlineStatus(request, "id");
        assertNotNull(response);
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/subscribers/id/online-status", recordedRequest.getPath());
        assertEquals("PATCH", recordedRequest.getMethod());
        assertEquals(singleSubscriberResponse, response);
    }

    public void test_getSubscriberPreferences() throws IOException, TeleflowNetworkException, InterruptedException {
        SubscriberPreferenceResponse preferenceResponse = new SubscriberPreferenceResponse();
        SubscriberPreference subscriberPreference = new SubscriberPreference();
        Preference preference = new Preference();
        preference.setEnabled(true);
        subscriberPreference.setPreference(preference);
        preferenceResponse.setData(List.of(subscriberPreference));

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(preferenceResponse)));

        SubscriberPreferenceResponse response = subscribersHandler.getSubscriberPreferences("id");
        assertNotNull(response);
        final RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/subscribers/id/preferences", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(preferenceResponse, response);
    }

    public void test_updateSubscriberPreferences() throws IOException, TeleflowNetworkException, InterruptedException {
        UpdateSubscriberPreferenceRequest request = new UpdateSubscriberPreferenceRequest();
        request.setEnabled(false);

        SingleSubscriberPrefResponse preferenceResponse = new SingleSubscriberPrefResponse();
        SubscriberPreference subscriberPreference = new SubscriberPreference();
        Preference preference = new Preference();
        preference.setEnabled(false);
        subscriberPreference.setPreference(preference);
        preferenceResponse.setData(subscriberPreference);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(preferenceResponse)));

        SingleSubscriberPrefResponse response = subscribersHandler.updateSubscriberPreferences(request, "sId", "tId");
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/subscribers/sId/preferences/tId", recordedRequest.getPath());
        assertEquals("PATCH", recordedRequest.getMethod());
        assertEquals(preferenceResponse, response);
    }

    public void test_getSubscriberNotificationsFeed() throws InterruptedException, IOException, TeleflowNetworkException {
        SubscriberNotificationResponse notificationResponse = new SubscriberNotificationResponse();
        notificationResponse.setPage(1L);
        notificationResponse.setPageSize(10L);
        notificationResponse.setTotalCount(100L);
        SubscriberNotification subscriberNotification = new SubscriberNotification();
        subscriberNotification.setContent("content");
        subscriberNotification.setChannel("PUSH");
        notificationResponse.setData(List.of(subscriberNotification));

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(notificationResponse)));

        SubscriberNotificationResponse response = subscribersHandler.getSubscriberNotificationsFeed("id");
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/subscribers/id/notifications/feed", recordedRequest.getPath());
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals(notificationResponse, response);
    }

    public void test_getSubscriberUnseenNotificationsCount() throws IOException, TeleflowNetworkException, InterruptedException {
        UnseenNotificationsCountResponse notificationsCountResponse = new UnseenNotificationsCountResponse();
        notificationsCountResponse.setData(20L);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(notificationsCountResponse)));

        UnseenNotificationsCountResponse response = subscribersHandler.getSubscriberUnseenNotificationsCount("id");
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/subscribers/id/notifications/unseen", recordedRequest.getPath());
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals(notificationsCountResponse, response);
    }

    public void test_markSubscriberMessageFeedAs() throws IOException, TeleflowNetworkException, InterruptedException {
        MarkSubscriberFeedAsRequest request = new MarkSubscriberFeedAsRequest();
        Mark mark = new Mark();
        mark.setRead(true);
        mark.setSeen(true);
        request.setMark(mark);

        SubscriberNotificationResponse notificationResponse = new SubscriberNotificationResponse();
        notificationResponse.setPage(1L);
        notificationResponse.setPageSize(10L);
        notificationResponse.setTotalCount(100L);
        SubscriberNotification subscriberNotification = new SubscriberNotification();
        subscriberNotification.setContent("content");
        subscriberNotification.setChannel("PUSH");
        notificationResponse.setData(List.of(subscriberNotification));

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(notificationResponse)));

        SubscriberNotificationResponse response = subscribersHandler.markSubscriberMessageFeedAs(request, "id");
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/subscribers/id/messages/markAs", recordedRequest.getPath());
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals(notificationResponse, response);
    }

    public void test_markAllSubscriberMessagesFeedAs() throws IOException, TeleflowNetworkException, InterruptedException {
        MarkAllMessagesRequest request = new MarkAllMessagesRequest();
        request.setFeedIdentifier("fId");
        request.setMarkAs("read");

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(20L)));

        Long response = subscribersHandler.markAllSubscriberMessagesFeedAs(request, "id");
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/subscribers/id/messages/mark-all", recordedRequest.getPath());
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals((Long) 20L, response);
    }

    public void test_markMessageActionAsSeen() throws IOException, TeleflowNetworkException, InterruptedException {
        MarkMessageActionAsSeenRequest request = new MarkMessageActionAsSeenRequest();
        request.setStatus("read");

        SubscriberNotificationResponse notificationResponse = new SubscriberNotificationResponse();
        notificationResponse.setPage(1L);
        notificationResponse.setPageSize(10L);
        notificationResponse.setTotalCount(100L);
        SubscriberNotification subscriberNotification = new SubscriberNotification();
        subscriberNotification.setContent("content");
        subscriberNotification.setChannel("PUSH");
        notificationResponse.setData(List.of(subscriberNotification));

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(notificationResponse)));

        SubscriberNotificationResponse response = subscribersHandler.markMessageActionAsSeen(new MarkMessageActionAsSeenRequest(), "sId", "mId", "type");
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/subscribers/sId/messages/mId/actions/type", recordedRequest.getPath());
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals(notificationResponse, response);
    }
}
