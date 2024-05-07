package com.teleflow.api.workflows.pojos;

import lombok.Data;

@Data
public class Email {
    private Boolean hasActiveIntegrations;
    private Boolean hasPrimaryIntegrations;
}
