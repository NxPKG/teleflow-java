package com.teleflow.api.organizations.responses;

import lombok.Data;

import java.util.List;

@Data
public class FetchOrganizationResponse {
    private List<OrganizationResponseData> data;
}
