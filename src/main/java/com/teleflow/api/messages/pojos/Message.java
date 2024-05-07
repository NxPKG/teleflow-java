package com.teleflow.api.messages.pojos;

import java.util.List;


import com.teleflow.api.common.Template;
import com.teleflow.api.notifications.pojos.Job;
import com.teleflow.api.notifications.pojos.Subscriber;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Message {
	@SerializedName("_id")
    private String id;
	@SerializedName("_environmentId")
    private String environmentId;
	@SerializedName("_organizationId")
    private String organizationId;
    private String transactionId;
    private String createdAt;
    private String channels;
    private Subscriber subscriber;
    private Template template;
    private List<Job> jobs;
}