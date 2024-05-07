package com.teleflow.api.integrations.responses;

import com.teleflow.api.integrations.pojo.Integration;
import lombok.Data;

@Data
public class SingleIntegrationResponse {
    private Integration data;
}