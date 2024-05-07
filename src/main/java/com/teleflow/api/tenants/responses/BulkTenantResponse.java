package com.teleflow.api.tenants.responses;

import com.teleflow.api.tenants.pojos.Tenant;
import lombok.Data;

import java.util.List;

@Data
public class BulkTenantResponse {
    private List<Tenant> data;
    private Integer page;
    private Integer pageSize;
    private Boolean hasMore;
}