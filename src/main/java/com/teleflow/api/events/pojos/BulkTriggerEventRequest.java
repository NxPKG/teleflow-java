package com.teleflow.api.events.pojos;

import com.teleflow.api.events.requests.TriggerEventRequest;
import com.teleflow.common.contracts.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class BulkTriggerEventRequest implements IRequest {
    private List<TriggerEventRequest> events;
}
