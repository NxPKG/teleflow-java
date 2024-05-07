package com.teleflow.api.organizations.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class UpdateOrganizationNameRequest implements IRequest {
    private String name;
}
