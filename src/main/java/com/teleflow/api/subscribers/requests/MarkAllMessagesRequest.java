package com.teleflow.api.subscribers.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class MarkAllMessagesRequest implements IRequest {
    private String feedIdentifier;
    private String markAs;
}