package com.teleflow.api.messages;

import java.util.Map;

import com.teleflow.api.messages.responses.DeleteMessageResponse;
import com.teleflow.api.messages.responses.MessageResponse;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MessageApi {

	String ENDPOINT = "messages";
	
	@GET(ENDPOINT)
	Call<MessageResponse> getMessages(@QueryMap Map<String, Object> options);
	
	@DELETE(ENDPOINT + "/{messageId}")
	Call<DeleteMessageResponse> deleteMessage(@Path("messageId") String messageId);
}
