package com.teleflow.api.notifications.pojos;

import lombok.Data;

@Data
public class Subscriber {
    private String id;
    private String subscriberId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String avatar;
}