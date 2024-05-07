package com.teleflow.api.layouts.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class LayoutRequest implements IRequest {
    private String name;
    private String description;
    private String content;
    private List<?> variables;
    private Boolean isDefault;
}
