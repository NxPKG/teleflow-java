package com.teleflow.api.messages.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class MessageRequest implements IRequest {
    private String channel;
    private String subscriberId;
    private List<Object> transactionId;
    private Integer page;
    private Integer limit;
}