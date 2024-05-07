package com.teleflow.api.workflows.responses;

import com.teleflow.api.common.Step;
import com.teleflow.api.common.Trigger;
import com.teleflow.api.common.NotificationGroup;
import com.teleflow.api.common.PreferenceSettings;
import com.teleflow.api.workflows.pojos.WorkflowIntegrationStatus;
import lombok.Data;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@Data
public class WorkflowResponse {
	@SerializedName("_id")
    private String id;
    private String description;
    private Boolean active;
    private String name;
    private Boolean draft;
    private PreferenceSettings preferenceSettings;
    private Boolean critical;
    private List<String> tags;
    private List<Step> steps;
    @SerializedName("_organizationId")
    private String organizationId;
    @SerializedName("_creatorId")
    private String creatorId;
    @SerializedName("_environmentId")
    private String environmentId;
    private List<Trigger> triggers;
    private String notificationGroupId;
    private Boolean deleted;
    private String deletedAt;
    private String deletedBy;
    private NotificationGroup notificationGroup;
    private Boolean isBlueprint;
    private WorkflowIntegrationStatus workflowIntegrationStatus;
}