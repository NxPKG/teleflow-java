package com.teleflow.api.changes.request;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class ApplyChangesRequest implements IRequest {
    private List<?> changeIds;
}
