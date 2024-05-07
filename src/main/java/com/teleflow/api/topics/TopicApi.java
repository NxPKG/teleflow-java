package com.teleflow.api.topics;

import com.teleflow.api.topics.requests.RenameTopicRequest;
import com.teleflow.api.topics.requests.SubscriberAdditionRequest;
import com.teleflow.api.topics.requests.TopicRequest;
import com.teleflow.api.topics.responses.CheckTopicSubscriberResponse;
import com.teleflow.api.topics.responses.DeleteTopicResponse;
import com.teleflow.api.topics.responses.FilterTopicsResponse;
import com.teleflow.api.topics.responses.SubscriberAdditionResponse;
import com.teleflow.api.topics.responses.SubscriberRemovalResponse;
import com.teleflow.api.topics.responses.TopicResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface TopicApi {

    String ENDPOINT = "topics";

    @POST(ENDPOINT)
    Call<TopicResponse> createTopic(@Body TopicRequest topicRequest);

    @GET(ENDPOINT)
    Call<FilterTopicsResponse> filterTopics(@QueryMap Map<String, Object> options);

    @POST(ENDPOINT + "/{topicKey}/subscribers")
    Call<SubscriberAdditionResponse> addSubscriberToTopic(@Path("topicKey") String topicKey, @Body SubscriberAdditionRequest subscriberAdditionRequest);

    @GET(ENDPOINT + "/{topicKey}/subscribers/{externalSubscriberId}")
    Call<CheckTopicSubscriberResponse> checkTopicSubscriber(@Path("topicKey") String topicKey, @Path("externalSubscriberId") String externalSubscriberId);

    @POST(ENDPOINT + "/{topicKey}/subscribers/removal")
    Call<Void> removeSubscriberFromTopic(@Path("topicKey") String topicKey, @Body SubscriberAdditionRequest subscriberAdditionRequest);

    @DELETE(ENDPOINT + "/{topicKey}")
    Call<Void> deleteTopic(@Path("topicKey") String topicKey);

    @GET(ENDPOINT + "/{topicKey}")
    Call<TopicResponse> getTopic(@Path("topicKey") String topicKey);

    @PATCH(ENDPOINT + "/{topicKey}")
    Call<TopicResponse> renameTopic(@Path("topicKey") String topicKey, @Body RenameTopicRequest request);
}
