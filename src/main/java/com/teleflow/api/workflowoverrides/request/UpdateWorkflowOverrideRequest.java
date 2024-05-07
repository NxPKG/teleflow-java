package com.teleflow.api.workflowoverrides.request;

import com.teleflow.api.common.PreferenceSettings;

import lombok.Data;

@Data
public class UpdateWorkflowOverrideRequest {
    private Boolean active;
    private PreferenceSettings preferenceSettings;
}
