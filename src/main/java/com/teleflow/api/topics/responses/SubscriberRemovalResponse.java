package com.teleflow.api.topics.responses;

import lombok.Data;

@Data
public class SubscriberRemovalResponse {

    private Boolean acknowledged = true;
    private String status = "Done";
}