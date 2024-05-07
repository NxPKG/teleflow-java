package com.teleflow.common.rest;

import com.teleflow.common.base.TeleflowConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@Slf4j
public class RestHandler {

    private final TeleflowConfig teleflowConfig;

    private Retrofit retrofit;

    public Retrofit buildRetrofit() {
        if (retrofit != null) {
            return retrofit;
        }

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Authorization", "ApiKey " + teleflowConfig.getApiKey())
                            .addHeader("User-Agent", "teleflow/Java@" + loadSdkVersionFromPom())
                            .build();
                    return chain.proceed(request);
                });
        if (teleflowConfig.isEnableLogging()) {
            clientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(teleflowConfig.getApiLogLevel()));
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(teleflowConfig.isEnableEuVersion() ? teleflowConfig.getEuBaseUrl() : teleflowConfig.getBaseUrl())
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    public <T> T extractResponse(Response<T> response) throws TeleflowNetworkException, IOException {
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new TeleflowNetworkException(response.errorBody() != null ? response.errorBody().string() : "Error connecting to Teleflow API");
        }
    }

    public <T, R> R extractResponse(Response<T> response, R body) throws TeleflowNetworkException, IOException {
        if (response.isSuccessful()) {
            return body;
        } else {
            throw new TeleflowNetworkException(response.errorBody() != null ? response.errorBody().string() : "Error connecting to Teleflow API");
        }
    }

    private String loadSdkVersionFromPom() {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/META-INF/maven/com.teleflow/teleflow-java/pom.xml");
            if (inputStream == null) {
                return "";
            }
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new InputStreamReader(inputStream));
            return model.getVersion();
        } catch (Exception e) {
            if (teleflowConfig.isEnableLogging()) {
                log.error("Could not retrieve the sdk version", e);
            }
        }
        return "";
    }
}