package com.teleflow.api.workflows.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class UpdateWorkflowStatusRequest implements IRequest {
    private Boolean active;
}