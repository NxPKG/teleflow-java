package com.teleflow.api.subscribers.requests;

import com.teleflow.api.common.SubscriberRequest;
import com.teleflow.common.contracts.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class BulkSubscriberRequest implements IRequest {
    private List<SubscriberRequest> subscribers;
}