package com.teleflow.api.messages;

import com.teleflow.api.messages.pojos.Message;
import com.teleflow.api.messages.requests.MessageRequest;
import com.teleflow.api.messages.responses.DeleteMessageResponse;
import com.teleflow.api.messages.responses.MessageResponse;
import com.teleflow.common.base.TeleflowConfig;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import com.google.gson.Gson;
import junit.framework.TestCase;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import java.io.IOException;
import java.util.Collections;

public class MessageHandlerTest extends TestCase {

    private MessageHandler messageHandler;

    private MockWebServer mockWebServer;

    @Override
    protected void setUp() {
    	mockWebServer = new MockWebServer();
    	TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        messageHandler = new MessageHandler(restHandler);
    }

    public void test_getMessagesWithNoParam() throws InterruptedException, TeleflowNetworkException, IOException {
    	Gson gson = new Gson();
    	mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody("{}"));

        MessageResponse response = messageHandler.getMessages(new MessageRequest());
        
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/messages", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals("{}", gson.toJson(response));
    }

    public void test_getMessagesWithParams() throws TeleflowNetworkException, IOException, InterruptedException {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setPage(2L);
        messageResponse.setPageSize(20L);
        messageResponse.setTotalCount(200L);
        messageResponse.setData(Collections.singletonList(new Message()));
        
        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(messageResponse)));
        
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setPage(2);
        messageRequest.setLimit(20);

        MessageResponse response = messageHandler.getMessages(messageRequest);
        
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/messages?limit=20&page=2", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(gson.toJson(messageResponse), gson.toJson(response));
    }

    public void test_deleteMessage() throws IOException, TeleflowNetworkException, InterruptedException {
        DeleteMessageResponse deleteMessageResponse = new DeleteMessageResponse();
        deleteMessageResponse.setAcknowledged(true);
        deleteMessageResponse.setStatus("done");

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(deleteMessageResponse)));

        DeleteMessageResponse response = messageHandler.deleteMessage("id");
        
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/messages/id", request.getPath());
        assertEquals("DELETE", request.getMethod());
        assertEquals(gson.toJson(deleteMessageResponse), gson.toJson(response));
    }
}