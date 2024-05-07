package com.teleflow.api.tenants.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class GetTenantRequest implements IRequest {
    private Integer page;
    private Integer limit;
}