package com.teleflow.api.workflowoverrides.request;

import com.teleflow.common.contracts.IRequest;

import lombok.Data;

@Data
public class GetWorkflowOverrideRequest implements IRequest {
    private Integer page;
    private Integer limit;
}
