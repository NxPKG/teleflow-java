package com.teleflow.api.subscribers.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class MarkMessageActionAsSeenRequest implements IRequest {
    private String status;
    private Object payload;
}