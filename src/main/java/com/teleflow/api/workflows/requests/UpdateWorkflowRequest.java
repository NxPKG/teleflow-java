package com.teleflow.api.workflows.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class UpdateWorkflowRequest extends BaseWorkflowRequest implements IRequest {
    private String identifier;
}