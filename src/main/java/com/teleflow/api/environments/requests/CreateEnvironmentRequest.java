package com.teleflow.api.environments.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class CreateEnvironmentRequest implements IRequest {
    private String name;
    private String parentId;
}