package com.teleflow.api.organizations.requests;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class UpdateMemberRoleRequest implements IRequest {
    private String role;
}
