package com.teleflow.api.topics.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class FilterTopicsRequest implements IRequest {
    private Integer page;
    private Integer pageSize;
    private String key;
}
