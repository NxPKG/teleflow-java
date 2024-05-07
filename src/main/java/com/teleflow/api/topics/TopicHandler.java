package com.teleflow.api.topics;

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
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Response;

public class TopicHandler {

    private final RestHandler restHandler;
    private final TopicApi topicApi;

    public TopicHandler(RestHandler restHandler) {
        this.restHandler = restHandler;
        this.topicApi = restHandler.buildRetrofit().create(TopicApi.class);
    }

    public TopicResponse createTopic(TopicRequest request) throws IOException, TeleflowNetworkException {
        Response<TopicResponse> response = topicApi.createTopic(request).execute();
        return restHandler.extractResponse(response);
    }

    public FilterTopicsResponse filterTopics(FilterTopicsRequest request) throws IOException, TeleflowNetworkException {
        Map<String, Object> params = new HashMap<>();
        if (request.getPage() != null) params.put("page", request.getPage());
        if (request.getPageSize() != null) params.put("pageSize", request.getPageSize());
        if (request.getKey() != null) params.put("key", request.getKey());
        Response<FilterTopicsResponse> response = topicApi.filterTopics(params).execute();
        return restHandler.extractResponse(response);
    }

    public SubscriberAdditionResponse addSubscriberToTopic(SubscriberAdditionRequest request, String topicKey) throws IOException, TeleflowNetworkException {
        Response<SubscriberAdditionResponse> response = topicApi.addSubscriberToTopic(topicKey, request).execute();
        return restHandler.extractResponse(response);
    }

    public CheckTopicSubscriberResponse checkTopicSubscriber(String topicKey, String externalSubscriberId) throws IOException, TeleflowNetworkException {
        Response<CheckTopicSubscriberResponse> response = topicApi.checkTopicSubscriber(topicKey, externalSubscriberId).execute();
        return restHandler.extractResponse(response);
    }

    public SubscriberRemovalResponse removeSubscriberFromTopic(SubscriberAdditionRequest request, String topicKey) throws IOException, TeleflowNetworkException {
        Response<Void> response = topicApi.removeSubscriberFromTopic(topicKey, request).execute();
        return restHandler.extractResponse(response, new SubscriberRemovalResponse());
    }

    public DeleteTopicResponse deleteTopic(String topicKey) throws IOException, TeleflowNetworkException {
        Response<Void> response = topicApi.deleteTopic(topicKey).execute();
        return restHandler.extractResponse(response, new DeleteTopicResponse());
    }

    public TopicResponse getTopic(String topicKey) throws IOException, TeleflowNetworkException {
        Response<TopicResponse> response = topicApi.getTopic(topicKey).execute();
        return restHandler.extractResponse(response);
    }

    public TopicResponse renameTopic(RenameTopicRequest request, String topicKey) throws IOException, TeleflowNetworkException {
        Response<TopicResponse> response = topicApi.renameTopic(topicKey, request).execute();
        return restHandler.extractResponse(response);
    }
}