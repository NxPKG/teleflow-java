package com.teleflow.api.subscribers.requests;

import com.teleflow.api.subscribers.pojos.ChannelCredentials;
import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class UpdateSubscriberCredentialsRequest implements IRequest {
    private String providerId;
    private ChannelCredentials credentials;
}