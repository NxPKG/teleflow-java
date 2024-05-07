package com.teleflow.api.tenants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.teleflow.api.tenants.requests.GetTenantRequest;
import com.teleflow.api.tenants.requests.TenantRequest;
import com.teleflow.api.tenants.responses.BulkTenantResponse;
import com.teleflow.api.tenants.responses.DeleteTenantResponse;
import com.teleflow.api.tenants.responses.TenantResponse;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import retrofit2.Response;

public class TenantsHandler {

    private final RestHandler restHandler;
    private final TenantsApi tenantsApi;

    public TenantsHandler(RestHandler restHandler) {
        this.restHandler = restHandler;
        this.tenantsApi = restHandler.buildRetrofit().create(TenantsApi.class);
    }

    public BulkTenantResponse getTenants(GetTenantRequest request) throws IOException, TeleflowNetworkException {
        Map<String, Object> params = new HashMap<>();
        if (request.getPage() != null) params.put("page", request.getPage());
        if (request.getLimit() != null) params.put("limit", request.getLimit());
        Response<BulkTenantResponse> response = tenantsApi.getTenants(params).execute();
        return restHandler.extractResponse(response);
    }

    public TenantResponse createTenant(TenantRequest request) throws IOException, TeleflowNetworkException {
        Response<TenantResponse> response = tenantsApi.createTenant(request).execute();
        return restHandler.extractResponse(response);
    }

    public TenantResponse getTenant(String tenantId) throws IOException, TeleflowNetworkException {
        Response<TenantResponse> response = tenantsApi.getTenant(tenantId).execute();
        return restHandler.extractResponse(response);
    }

    public TenantResponse updateTenant(TenantRequest request, String tenantId) throws IOException, TeleflowNetworkException {
        Response<TenantResponse> response = tenantsApi.updateTenant(request, tenantId).execute();
        return restHandler.extractResponse(response);
    }

    public DeleteTenantResponse deleteTenant(String tenantId) throws IOException, TeleflowNetworkException {
        Response<Void> response = tenantsApi.deleteTenant(tenantId).execute();
        return restHandler.extractResponse(response, new DeleteTenantResponse());
    }

}
