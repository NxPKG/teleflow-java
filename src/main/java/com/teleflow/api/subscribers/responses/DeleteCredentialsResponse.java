package com.teleflow.api.subscribers.responses;

import lombok.Data;

@Data
public class DeleteCredentialsResponse {

    private Boolean acknowledged = true;
    private String status = "Done";
}