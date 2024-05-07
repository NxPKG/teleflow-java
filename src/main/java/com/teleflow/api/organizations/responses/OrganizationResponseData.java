package com.teleflow.api.organizations.responses;

import com.teleflow.api.organizations.pojos.Branding;
import com.teleflow.api.organizations.pojos.PartnerConfigurations;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class OrganizationResponseData {
    @SerializedName("_id")
    private String id;
    private String name;
    private String logo;
    private Branding branding;
    private List<PartnerConfigurations> partnerConfigurations;
    private String createdAt;
    private String updatedAt;
    @SerializedName("__v")
    private Long version;


}
