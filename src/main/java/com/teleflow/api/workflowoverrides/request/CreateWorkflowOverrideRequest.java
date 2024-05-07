package com.teleflow.api.workflowoverrides.request;

import com.teleflow.api.common.PreferenceSettings;
import com.teleflow.common.contracts.IRequest;

import lombok.Data;

@Data
public class CreateWorkflowOverrideRequest implements IRequest {
    private PreferenceSettings preferenceSettings;
    private Boolean active;
    private String tenantId;
    private String workflowId;
}
