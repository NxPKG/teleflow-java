package com.teleflow.api.changes.request;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class GetChangesRequest implements IRequest {
    private Integer page;
    private Integer limit;
    private String promoted;
}
