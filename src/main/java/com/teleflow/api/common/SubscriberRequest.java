package com.teleflow.api.common;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class SubscriberRequest implements IRequest {
    private String subscriberId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String avatar;
    private String locale;
    private Object data;
}