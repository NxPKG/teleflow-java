package com.teleflow.api.subscribers.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class UpdateSubscriberOnlineStatusRequest implements IRequest {
    private Boolean isOnline;
}