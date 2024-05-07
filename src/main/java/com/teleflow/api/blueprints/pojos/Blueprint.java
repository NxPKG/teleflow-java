package com.teleflow.api.blueprints.pojos;

import com.teleflow.api.common.PreferenceSettings;
import com.teleflow.api.common.Step;
import com.teleflow.api.common.Trigger;
import com.teleflow.api.common.NotificationGroup;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Blueprint {
    @SerializedName("_id")
    private String id;
    private String name;
    private String description;
    private Boolean active;
    private Boolean draft;
    private Boolean critical;
    private Boolean isBlueprint;
    @SerializedName("_notificationGroupId")
    private String notificationGroupId;
    private String blueprintId;
    private List<String> tags;
    private List<Trigger> triggers;
    private List<Step> steps;
    private PreferenceSettings preferenceSettings;
    @SerializedName("_environmentId")
    private String environmentId;
    @SerializedName("_organizationId")
    private String organizationId;
    @SerializedName("_creatorId")
    private String creatorId;
    @SerializedName("_parentId")
    private String parentId;
    private Boolean deleted;
    private String createdAt;
    private String updatedAt;
    @SerializedName("__v")
    private Long version;
    private NotificationGroup notificationGroup;
}