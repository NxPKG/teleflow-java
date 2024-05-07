package com.teleflow.api.workflowgroups.request;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class WorkflowGroupRequest implements IRequest {
    private String name;
}
