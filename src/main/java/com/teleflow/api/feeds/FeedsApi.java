package com.teleflow.api.feeds;

import com.teleflow.api.feeds.request.FeedRequest;
import com.teleflow.api.feeds.response.BulkFeedsResponse;
import com.teleflow.api.feeds.response.FeedResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FeedsApi {

	String ENDPOINT = "feeds";
	
	@POST(ENDPOINT)
	Call<FeedResponse> createFeed(@Body FeedRequest request);
	
	@GET(ENDPOINT)
	Call<BulkFeedsResponse> getFeeds();
	
	@DELETE(ENDPOINT + "/{feedId}")
	Call<BulkFeedsResponse> deleteFeed(@Path("feedId") String feedId);
}
