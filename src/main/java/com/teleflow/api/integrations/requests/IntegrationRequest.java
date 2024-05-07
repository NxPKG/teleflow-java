package com.teleflow.api.integrations.requests;

import com.teleflow.api.integrations.pojo.Credentials;
import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class IntegrationRequest implements IRequest {
    private String providerId;
    private String channel;
    private Credentials credentials;
    private Boolean active;
    private Boolean check;
}