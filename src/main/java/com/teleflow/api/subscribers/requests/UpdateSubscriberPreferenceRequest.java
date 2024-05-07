package com.teleflow.api.subscribers.requests;

import com.teleflow.api.subscribers.pojos.PreferenceChannel;
import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class UpdateSubscriberPreferenceRequest implements IRequest {
    private PreferenceChannel channel;
    private Boolean enabled;
}