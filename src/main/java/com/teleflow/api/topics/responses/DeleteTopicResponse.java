package com.teleflow.api.topics.responses;

import lombok.Data;

@Data
public class DeleteTopicResponse {

    private Boolean acknowledged = true;
    private String status = "Done";
}