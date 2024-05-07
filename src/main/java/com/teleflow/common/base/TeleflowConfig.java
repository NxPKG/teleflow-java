package com.teleflow.common.base;

import lombok.Getter;
import lombok.Setter;
import okhttp3.logging.HttpLoggingInterceptor;

@Getter
@Setter
public class TeleflowConfig {
  
    public TeleflowConfig(String apiKey) {
        this.apiKey = apiKey;
    }

    private String apiKey;
    private String baseUrl = "https://api-teleflow.khulnasoft.com/v1/";
    private String euBaseUrl = "https://eu.api-teleflow.khulnasoft.com/v1/";
    private boolean enableEuVersion;
    private boolean enableLogging = true;
    private HttpLoggingInterceptor.Level apiLogLevel = HttpLoggingInterceptor.Level.BASIC;

}