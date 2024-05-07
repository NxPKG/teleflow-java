package com.teleflow.api.environments.requests;

import com.teleflow.api.environments.pojos.Dns;
import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class UpdateEnvironmentRequest implements IRequest {
    private String name;
    private String identifier;
    private String parentId;
    private Dns dns;
}