package com.teleflow.api.messages;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.teleflow.api.messages.requests.MessageRequest;
import com.teleflow.api.messages.responses.DeleteMessageResponse;
import com.teleflow.api.messages.responses.MessageResponse;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import retrofit2.Response;

public class MessageHandler {

    private final RestHandler restHandler;

    private final MessageApi messageApi;

    public MessageHandler(RestHandler restHandler) {
    	this.restHandler = restHandler;
    	this.messageApi = restHandler.buildRetrofit().create(MessageApi.class);
    }

    public MessageResponse getMessages(MessageRequest request) throws TeleflowNetworkException, IOException {
        Map<String, Object> params = new HashMap<>();
        if (request.getChannel() != null) params.put("channel", request.getChannel());
        if (request.getSubscriberId() != null) params.put("subscriberId", request.getSubscriberId());
        if (request.getTransactionId() != null) params.put("transactionId", request.getTransactionId());
        if (request.getPage() != null) params.put("page", request.getPage());
        if (request.getLimit() != null) params.put("limit", request.getLimit());

        Response<MessageResponse> response = messageApi.getMessages(params).execute();
        return restHandler.extractResponse(response);
    }

    public DeleteMessageResponse deleteMessage(String messageId) throws IOException, TeleflowNetworkException {
    	Response<DeleteMessageResponse> response = messageApi.deleteMessage(messageId).execute();
        return restHandler.extractResponse(response);
    }
}