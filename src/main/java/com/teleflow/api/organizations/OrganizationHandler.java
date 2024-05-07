package com.teleflow.api.organizations;

import com.teleflow.api.organizations.requests.CreateOrganizationRequest;
import com.teleflow.api.organizations.requests.UpdateMemberRoleRequest;
import com.teleflow.api.organizations.requests.UpdateOrganizationBrandRequest;
import com.teleflow.api.organizations.requests.UpdateOrganizationNameRequest;
import com.teleflow.api.organizations.responses.FetchMembersResponse;
import com.teleflow.api.organizations.responses.UpdateOrganizationBrandResponse;
import com.teleflow.api.organizations.responses.MemberResponse;
import com.teleflow.api.organizations.responses.OrganizationResponse;
import com.teleflow.api.organizations.responses.FetchOrganizationResponse;
import com.teleflow.api.organizations.responses.UpdateOrganizationNameResponse;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import lombok.RequiredArgsConstructor;
import retrofit2.Response;

import java.io.IOException;

@RequiredArgsConstructor
public class OrganizationHandler {

    private final RestHandler restHandler;

    private final OrganizationApi organizationApi;

    public OrganizationHandler(RestHandler restHandler) {
        this.restHandler = restHandler;
        this.organizationApi = restHandler.buildRetrofit().create(OrganizationApi.class);
    }


    public OrganizationResponse createOrganization(CreateOrganizationRequest request) throws IOException, TeleflowNetworkException {
        Response<OrganizationResponse> response = organizationApi.createOrganization(request).execute();
        return restHandler.extractResponse(response);
    }

    public FetchOrganizationResponse fetchAllOrganizations() throws IOException, TeleflowNetworkException {
        Response<FetchOrganizationResponse> response = organizationApi.fetchAllOrganizations().execute();
        return restHandler.extractResponse(response);
    }

    public UpdateOrganizationNameResponse updateOrganizationName(UpdateOrganizationNameRequest request) throws IOException, TeleflowNetworkException {
        Response<UpdateOrganizationNameResponse> response = organizationApi.updateOrganizationName(request).execute();
        return restHandler.extractResponse(response);
    }

    public OrganizationResponse fetchCurrentOrganization() throws IOException, TeleflowNetworkException{
        Response<OrganizationResponse> response = organizationApi.fetchCurrentOrganization().execute();
        return restHandler.extractResponse(response);
    }

    public MemberResponse removeMemberWithId(String memberId) throws IOException, TeleflowNetworkException {
        Response<MemberResponse> response = organizationApi.removeMemberWithId(memberId).execute();
        return restHandler.extractResponse(response);
    }

    public MemberResponse updateMemberRole(String memberId, UpdateMemberRoleRequest request) throws IOException, TeleflowNetworkException {
        Response<MemberResponse> response = organizationApi.updateMemberRole(memberId,request).execute();
        return restHandler.extractResponse(response);
    }

    public FetchMembersResponse fetchMembersOfOrganization() throws IOException, TeleflowNetworkException {
        Response<FetchMembersResponse> response = organizationApi.fetchMembersOfOrganization().execute();
        return restHandler.extractResponse(response);
    }

    public UpdateOrganizationBrandResponse updateOrganizationBrand(UpdateOrganizationBrandRequest request) throws IOException, TeleflowNetworkException {
        Response<UpdateOrganizationBrandResponse> response = organizationApi.updateOrganizationBrand(request).execute();
        return restHandler.extractResponse(response);
    }


}
