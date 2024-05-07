package com.teleflow.api.workflows.pojos;

import lombok.Data;

@Data
public class Channels {
    private Email email;
    private Sms sms;
    private Chat chat;
    private Push push;
}