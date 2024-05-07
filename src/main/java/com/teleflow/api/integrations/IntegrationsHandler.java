package com.teleflow.api.integrations;

import java.io.IOException;

import com.teleflow.api.integrations.requests.IntegrationRequest;
import com.teleflow.api.integrations.responses.BulkIntegrationResponse;
import com.teleflow.api.integrations.responses.ProviderWebhookStatusResponse;
import com.teleflow.api.integrations.responses.SingleIntegrationResponse;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IntegrationsHandler {

    private final RestHandler restHandler;

    private final IntegrationsApi integrationsApi;

     public IntegrationsHandler(RestHandler restHandler) {
        this.restHandler = restHandler;
        this.integrationsApi = restHandler.buildRetrofit().create(IntegrationsApi.class);
    }

    public BulkIntegrationResponse getIntegrations() throws TeleflowNetworkException, IOException {
        return restHandler.extractResponse(this.integrationsApi.getIntegrations().execute());
    }
    
    public SingleIntegrationResponse createIntegration(IntegrationRequest request) throws TeleflowNetworkException, IOException {
        return restHandler.extractResponse(this.integrationsApi.createIntegration(request).execute());
    }

    public BulkIntegrationResponse getActiveIntegrations() throws TeleflowNetworkException, IOException {
        return restHandler.extractResponse(this.integrationsApi.getActiveIntegrations().execute());
    }

    public ProviderWebhookStatusResponse getProviderWebhookStatus(String providerId) throws TeleflowNetworkException, IOException {
        return restHandler.extractResponse(this.integrationsApi.getProviderWebhookStatus(providerId).execute());
    }

    public SingleIntegrationResponse updateIntegration(String integrationId, IntegrationRequest request) throws TeleflowNetworkException, IOException {
        return restHandler.extractResponse(this.integrationsApi.updateIntegration(integrationId, request).execute());
    }

    public BulkIntegrationResponse deleteIntegration(String integrationId) throws TeleflowNetworkException, IOException {
        return restHandler.extractResponse(this.integrationsApi.deleteIntegration(integrationId).execute());
    }

    public SingleIntegrationResponse setIntegrationAsPrimary(String integrationId) throws TeleflowNetworkException, IOException {
        return restHandler.extractResponse(this.integrationsApi.setIntegrationAsPrimary(integrationId).execute());
    }
}