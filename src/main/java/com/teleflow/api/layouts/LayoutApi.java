package com.teleflow.api.layouts;

import com.teleflow.api.layouts.requests.LayoutRequest;
import com.teleflow.api.layouts.responses.CreateLayoutResponse;
import com.teleflow.api.layouts.responses.DeleteLayoutResponse;
import com.teleflow.api.layouts.responses.FilterLayoutResponse;
import com.teleflow.api.layouts.responses.GetLayoutResponse;
import com.teleflow.api.layouts.responses.SetDefaultLayoutResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface LayoutApi {

    String ENDPOINT = "layouts";

    @POST(ENDPOINT)
    Call<CreateLayoutResponse> createLayout(@Body LayoutRequest request);

    @GET(ENDPOINT)
    Call<FilterLayoutResponse> filterLayouts(@QueryMap Map<String, Object> options);

    @GET(ENDPOINT + "/{layoutId}")
    Call<GetLayoutResponse> getLayout(@Path("layoutId") String layoutId);

    @DELETE(ENDPOINT + "/{layoutId}")
    Call<Void> deleteLayout(@Path("layoutId") String layoutId);

    @PATCH(ENDPOINT + "/{layoutId}")
    Call<GetLayoutResponse> updateLayout(@Path("layoutId") String layoutId, @Body LayoutRequest request);

    @POST(ENDPOINT + "/{layoutId}/default")
    Call<Void> setDefaultLayout(@Path("layoutId") String layoutId);
}
