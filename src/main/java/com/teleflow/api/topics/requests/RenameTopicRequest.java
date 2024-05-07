package com.teleflow.api.topics.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class RenameTopicRequest implements IRequest {
    private String name;
}
