package com.teleflow.api.tenants.responses;

import com.teleflow.api.tenants.pojos.Tenant;
import lombok.Data;

@Data
public class TenantResponse {
    private Tenant data;
}