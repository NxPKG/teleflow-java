package com.teleflow.api.events.requests;

import lombok.Data;

@Data
public class Topic {
    private String type;
    private String topicKey;
}
