package com.teleflow.api.organizations.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class CreateOrganizationRequest implements IRequest {
    private String name;
    private String logo;
}
