package com.teleflow.api.organizations.responses;

import lombok.Data;

import java.util.List;

@Data
public class FetchMembersResponse {
    private List<MemberResponseData> data;
}
