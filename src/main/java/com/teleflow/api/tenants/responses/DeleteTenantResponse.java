package com.teleflow.api.tenants.responses;

import lombok.Data;

@Data
public class DeleteTenantResponse {

    private Boolean acknowledged = true;
    private String status = "Done";
}