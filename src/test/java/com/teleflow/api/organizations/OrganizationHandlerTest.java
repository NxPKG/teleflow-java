package com.teleflow.api.organizations;



import com.teleflow.api.organizations.pojos.Branding;
import com.teleflow.api.organizations.pojos.InviteDetails;
import com.teleflow.api.organizations.pojos.PartnerConfigurations;
import com.teleflow.api.organizations.pojos.UserDetails;
import com.teleflow.api.organizations.requests.CreateOrganizationRequest;
import com.teleflow.api.organizations.requests.UpdateMemberRoleRequest;
import com.teleflow.api.organizations.requests.UpdateOrganizationBrandRequest;
import com.teleflow.api.organizations.requests.UpdateOrganizationNameRequest;
import com.teleflow.api.organizations.responses.FetchMembersResponse;
import com.teleflow.api.organizations.responses.UpdateOrganizationBrandResponse;
import com.teleflow.api.organizations.responses.MemberResponse;
import com.teleflow.api.organizations.responses.OrganizationResponseData;
import com.teleflow.api.organizations.responses.UpdateOrganizationNameResponseData;
import com.teleflow.api.organizations.responses.MemberResponseData;
import com.teleflow.api.organizations.responses.OrganizationResponse;
import com.teleflow.api.organizations.responses.FetchOrganizationResponse;
import com.teleflow.api.organizations.responses.UpdateOrganizationNameResponse;
import com.teleflow.common.base.TeleflowConfig;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import com.google.gson.Gson;
import junit.framework.TestCase;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import java.io.IOException;
import java.util.List;

public class OrganizationHandlerTest extends TestCase {


    private OrganizationHandler organizationHandler;

    private MockWebServer mockWebServer;

    private final Gson gson = new Gson();


    @Override
    protected void setUp() {
        mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        organizationHandler = new OrganizationHandler(restHandler);    }

    public void test_createOrganization() throws IOException, TeleflowNetworkException, InterruptedException {
        CreateOrganizationRequest request = new CreateOrganizationRequest();
        request.setName("name");
        request.setLogo("logo");


        OrganizationResponse organizationResponse = new OrganizationResponse();
        OrganizationResponseData data = new OrganizationResponseData();
        data.setId("id");
        data.setName("name");
        data.setLogo("logo");
        Branding branding = new Branding();
        branding.setDirection("direction");
        branding.setLogo("logo");
        branding.setColor("color");
        branding.setFontColor("fontColor");
        branding.setContentBackground("contentBackground");
        branding.setFontFamily("fontFamily");
        data.setBranding(branding);
        PartnerConfigurations partnerConfigurations = new PartnerConfigurations();
        partnerConfigurations.setProjectIds(List.of());
        partnerConfigurations.setAccessToken("accessToken");
        partnerConfigurations.setConfigurationId("configurationId");
        partnerConfigurations.setTeamId("teamId");
        partnerConfigurations.setPartnerType("partnerType");
        data.setPartnerConfigurations(List.of(partnerConfigurations));
        data.setCreatedAt("createdAt");
        data.setUpdatedAt("updatedAt");
        data.setVersion(1L);
        organizationResponse.setData(data);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(organizationResponse)));

        OrganizationResponse response = organizationHandler.createOrganization(request);
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/organizations", recordedRequest.getPath());
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals(organizationResponse, response);
    }

    public void test_fetchAllOrganizations() throws IOException, TeleflowNetworkException, InterruptedException {
        FetchOrganizationResponse fetchOrganizationResponse = new FetchOrganizationResponse();
        OrganizationResponseData data = new OrganizationResponseData();
        data.setId("id");
        data.setName("name");
        data.setLogo("logo");
        Branding branding = new Branding();
        branding.setDirection("direction");
        branding.setLogo("logo");
        branding.setColor("color");
        branding.setFontColor("fontColor");
        branding.setContentBackground("contentBackground");
        branding.setFontFamily("fontFamily");
        data.setBranding(branding);
        PartnerConfigurations partnerConfigurations = new PartnerConfigurations();
        partnerConfigurations.setProjectIds(List.of());
        partnerConfigurations.setAccessToken("accessToken");
        partnerConfigurations.setConfigurationId("configurationId");
        partnerConfigurations.setTeamId("teamId");
        partnerConfigurations.setPartnerType("partnerType");
        data.setPartnerConfigurations(List.of(partnerConfigurations));
        data.setCreatedAt("createdAt");
        data.setUpdatedAt("updatedAt");
        data.setVersion(1L);
        fetchOrganizationResponse.setData(List.of(data));
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(fetchOrganizationResponse)));

        FetchOrganizationResponse response = organizationHandler.fetchAllOrganizations();
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/organizations", recordedRequest.getPath());
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals(fetchOrganizationResponse, response);
    }

    public void test_updateOrganizationName() throws IOException, TeleflowNetworkException, InterruptedException {
        UpdateOrganizationNameRequest updateOrganizationNameRequest = new UpdateOrganizationNameRequest();
        updateOrganizationNameRequest.setName("name");

        UpdateOrganizationNameResponse updateOrganizationNameResponse = new UpdateOrganizationNameResponse();
        UpdateOrganizationNameResponseData data = new UpdateOrganizationNameResponseData();
        data.setName("name");
        updateOrganizationNameResponse.setData(data);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(updateOrganizationNameResponse)));

        UpdateOrganizationNameResponse response = organizationHandler.updateOrganizationName(updateOrganizationNameRequest);
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/organizations", recordedRequest.getPath());
        assertEquals("PATCH", recordedRequest.getMethod());
        assertEquals(updateOrganizationNameResponse, response);
    }

    public void test_fetchCurrentOrganization() throws IOException, TeleflowNetworkException, InterruptedException {
        OrganizationResponse organizationResponse = new OrganizationResponse();
        OrganizationResponseData data = new OrganizationResponseData();
        data.setId("id");
        data.setName("name");
        data.setLogo("logo");
        Branding branding = new Branding();
        branding.setDirection("direction");
        branding.setLogo("logo");
        branding.setColor("color");
        branding.setFontColor("fontColor");
        branding.setContentBackground("contentBackground");
        branding.setFontFamily("fontFamily");
        data.setBranding(branding);
        PartnerConfigurations partnerConfigurations = new PartnerConfigurations();
        partnerConfigurations.setProjectIds(List.of());
        partnerConfigurations.setAccessToken("accessToken");
        partnerConfigurations.setConfigurationId("configurationId");
        partnerConfigurations.setTeamId("teamId");
        partnerConfigurations.setPartnerType("partnerType");
        data.setPartnerConfigurations(List.of(partnerConfigurations));
        data.setCreatedAt("createdAt");
        data.setUpdatedAt("updatedAt");
        data.setVersion(1L);
        organizationResponse.setData(data);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(organizationResponse)));

        OrganizationResponse response = organizationHandler.fetchCurrentOrganization();
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/organizations/me", recordedRequest.getPath());
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals(organizationResponse, response);
    }

    public void test_removeMemberWithId() throws IOException, TeleflowNetworkException, InterruptedException{
        MemberResponse memberResponse = new MemberResponse();
        MemberResponseData data = new MemberResponseData();
        data.setId("id");
        data.setUserId("userId");
        UserDetails user = new UserDetails();
        user.setId("id");
        user.setFirstName("firstName");
        user.setLastName("lastname");
        user.setEmail("email");
        data.setUser(user);
        data.setRoles(List.of());
        InviteDetails invite = new InviteDetails();
        invite.setEmail("email");
        invite.setToken("token");
        invite.setInvitationDate("invitationDate");
        invite.setAnswerDate("answerDate");
        invite.setInviterId("InviterId");
        data.setInvite(invite);
        data.setMemberStatus("memberStatus");
        data.setOrganizationId("organizationId");
        memberResponse.setData(data);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(memberResponse)));

        MemberResponse response = organizationHandler.removeMemberWithId("memberId");
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/organizations/members/memberId", recordedRequest.getPath());
        assertEquals("DELETE", recordedRequest.getMethod());
        assertEquals(memberResponse, response);
    }

    public void test_updateMemberRole() throws IOException, TeleflowNetworkException, InterruptedException {
        UpdateMemberRoleRequest memberRoleRequest = new UpdateMemberRoleRequest();
        memberRoleRequest.setRole("admin");

        MemberResponse memberResponse = new MemberResponse();
        MemberResponseData data = new MemberResponseData();
        data.setId("id");
        data.setUserId("userId");
        UserDetails user = new UserDetails();
        user.setId("id");
        user.setFirstName("firstName");
        user.setLastName("lastname");
        user.setEmail("email");
        data.setUser(user);
        data.setRoles(List.of());
        InviteDetails invite = new InviteDetails();
        invite.setEmail("email");
        invite.setToken("token");
        invite.setInvitationDate("invitationDate");
        invite.setAnswerDate("answerDate");
        invite.setInviterId("InviterId");
        data.setInvite(invite);
        data.setMemberStatus("memberStatus");
        data.setOrganizationId("organizationId");
        memberResponse.setData(data);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(memberResponse)));

        MemberResponse response = organizationHandler.updateMemberRole("memberId", memberRoleRequest);
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/organizations/members/memberId/roles", recordedRequest.getPath());
        assertEquals("PUT", recordedRequest.getMethod());
        assertEquals(memberResponse, response);
    }

    public void test_fetchMembersOfOrganization()throws IOException, TeleflowNetworkException, InterruptedException {
        FetchMembersResponse membersResponse = new FetchMembersResponse();
        MemberResponseData data = new MemberResponseData();
        data.setId("id");
        data.setUserId("userId");
        UserDetails user = new UserDetails();
        user.setId("id");
        user.setFirstName("firstName");
        user.setLastName("lastname");
        user.setEmail("email");
        data.setUser(user);
        data.setRoles(List.of());
        InviteDetails invite = new InviteDetails();
        invite.setEmail("email");
        invite.setToken("token");
        invite.setInvitationDate("invitationDate");
        invite.setAnswerDate("answerDate");
        invite.setInviterId("InviterId");
        data.setInvite(invite);
        data.setMemberStatus("memberStatus");
        data.setOrganizationId("organizationId");
        membersResponse.setData(List.of(data));

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(membersResponse)));

        FetchMembersResponse response = organizationHandler.fetchMembersOfOrganization();
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/organizations/members", recordedRequest.getPath());
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals(membersResponse, response);
    }

    public void test_updateOrganizationBrand() throws IOException, TeleflowNetworkException, InterruptedException{
        UpdateOrganizationBrandRequest brandRequest = new UpdateOrganizationBrandRequest();
        brandRequest.setLogo("logo");
        brandRequest.setColor("color");
        brandRequest.setFontColor("fontColor");
        brandRequest.setContentBackground("contentBackground");
        brandRequest.setFontFamily("fontFamily");

        UpdateOrganizationBrandResponse brandResponse = new UpdateOrganizationBrandResponse();
        Branding data = new Branding();
        data.setDirection("direction");
        data.setLogo("logo");
        data.setColor("color");
        data.setFontColor("fontColor");
        data.setContentBackground("contentBackground");
        data.setFontFamily("fontFamily");
        brandResponse.setData(data);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(brandResponse)));

        UpdateOrganizationBrandResponse response = organizationHandler.updateOrganizationBrand(brandRequest);
        assertNotNull(response);
        final RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("/organizations/branding", recordedRequest.getPath());
        assertEquals("PUT", recordedRequest.getMethod());
        assertEquals(brandResponse, response);
    }





}
