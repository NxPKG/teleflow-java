package com.teleflow.api.integrations.responses;

import com.teleflow.api.integrations.pojo.Integration;
import lombok.Data;

import java.util.List;

@Data
public class BulkIntegrationResponse {
    private List<Integration> data;
}