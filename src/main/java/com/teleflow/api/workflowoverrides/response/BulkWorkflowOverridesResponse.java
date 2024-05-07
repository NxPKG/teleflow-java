package com.teleflow.api.workflowoverrides.response;

import com.teleflow.api.workflowoverrides.pojos.WorkflowOverride;

import java.util.List;

import lombok.Data;

@Data
public class BulkWorkflowOverridesResponse {
    private List<WorkflowOverride> data;
    private Boolean hasMore;
    private Long page;
    private Long pageSize;
}


