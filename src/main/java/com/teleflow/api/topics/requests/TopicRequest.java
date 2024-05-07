package com.teleflow.api.topics.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class TopicRequest implements IRequest {
    private String key;
    private String name;
}
